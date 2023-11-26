package com.example.programmaifttt;

import org.json.JSONObject;

public class Data {
    private String campo1;
    private int campo2;

    // Constructor
    public Data(String campo1, int campo2) {
        this.campo1 = campo1;
        this.campo2 = campo2;
    }

    // Getters and Setters
    public String getCampo1() {
        return campo1;
    }

    public void setCampo1(String campo1) {
        this.campo1 = campo1;
    }

    public int getCampo2() {
        return campo2;
    }

    public void setCampo2(int campo2) {
        this.campo2 = campo2;
    }

    public String toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("campo1", campo1);
        jsonObject.put("campo2", campo2);
        return jsonObject.toString();
    }

    public static Data fromJson(String json) {
       JSONObject jsonObject = new JSONObject(json);
        return new Data(jsonObject.getString("campo1"), jsonObject.getInt("campo2"));
    }
}
