package com.Bee.BeeWebserver;

public class Events{

    public String severity;
    public String instructions;
    public String last_update;
    public String type;
    public String event_id;

    public Events(String severity, String instructions, String last_update, String type, String event_id){
        this.severity = severity;
        this.instructions = instructions;
        this.last_update = last_update;
        this.type = type;
        this.event_id = event_id;
    }
}