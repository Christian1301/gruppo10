package com.example.programmaifttt;


import com.example.programmaifttt.BackEnd.Rule;
import com.example.programmaifttt.BackEnd.RuleController;

import java.time.Duration;

public class Scheduler {

    private RuleController ruleController;
    private Duration interval;
    private Thread schedulerThread;
    public Scheduler(Duration interval,RuleController ruleController){
        this.interval=interval;
        this.ruleController = ruleController;
    }


    public void start() {
        schedulerThread = new Thread(() -> {
            while (!Thread.interrupted()) {
                try {
                    Thread.sleep(interval.toMillis());
                    checkRules();
                } catch (InterruptedException e) {
                    // Thread interrupted, exit the loop
                    break;
                }
            }
        });
        schedulerThread.start();
    }


    // Method to stop the scheduler thread
    public void stop() {
        if (schedulerThread != null && schedulerThread.isAlive()) {
            schedulerThread.interrupt();
            try {
                schedulerThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void checkRules(){
    for (Rule rule : ruleController.getRules()){
        if(rule.getTrigger().evaluate()){
            rule.getAction().execute();
        }
    }
    }
}