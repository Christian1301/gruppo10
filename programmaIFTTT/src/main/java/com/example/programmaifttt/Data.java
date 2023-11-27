package com.example.programmaifttt;

import com.example.programmaifttt.BackEnd.Rule;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Data {
    private List<Rule> rules;

    // Constructor
    public Data(List<Rule> rules) {
        this.rules = rules;
    }

    public Data() {
        this.rules = new ArrayList<>();
    }

    // Getters for rules
    public List<Rule> getRules() {
        return rules;
    }

    public void addRule(Rule rule) {
        rules.add(rule);
    }

    public JSONObject toJson() {
        JSONArray ruleArray = new JSONArray();
        for (Rule rule : rules) {
            ruleArray.put(rule.toJson());
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("rules", ruleArray);

        return jsonObject;
    }

    public static Data fromJson(String json) {
        JSONObject jsonObject = new JSONObject(json);

        JSONArray ruleArray = jsonObject.getJSONArray("rules");
        List<Rule> rules = new ArrayList<>();
        for (int i = 0; i < ruleArray.length(); i++) {
            rules.add(Rule.fromJson(ruleArray.getJSONObject(i).toString()));
        }

        return new Data(rules);
    }

    public void saveDatas(String fileName) {
        try {
            ClassLoader classLoader = IFTTTApplication.class.getClassLoader();
            File file = new File(classLoader.getResource(fileName).getFile());

            // Utilizza una PrintWriter per scrivere il JSON nel file
            try (PrintWriter writer = new PrintWriter(file)) {
                writer.println(this.toJson());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Data loadDatas() {
        try {
            ClassLoader classLoader = IFTTTApplication.class.getClassLoader();
            File file = new File(classLoader.getResource("data.txt").getFile());
            String content = new String(Files.readAllBytes(file.toPath()));

            if (content.isEmpty()) {
                return new Data();
            }

            return Data.fromJson(content);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
