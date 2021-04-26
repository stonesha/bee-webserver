package com.Bee.BeeWebserver;

public class Events{

    public String severity;
    public String type;
    public String instructions;

    public Events(String severity, String type, String instructions){
        this.severity = severity;
        this.type = type;
        this.instructions = instructions;
    }
}