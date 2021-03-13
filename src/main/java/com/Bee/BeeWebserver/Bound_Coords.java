package com.Bee.BeeWebserver;

public class Bound_Coords{

    public String ordinal;
    public String bound_coord_id;
    public String event_id;
    public String location;

    public Bound_Coords(String ordinal, String bound_coord_id, String event_id, String location){
        this.ordinal = ordinal;
        this.bound_coord_id = bound_coord_id;
        this.event_id = event_id;
        this.location = location;
    } 
}