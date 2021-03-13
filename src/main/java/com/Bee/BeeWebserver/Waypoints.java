package com.Bee.BeeWebserver;

public class Waypoints{

    public String waypoint_id;
    public String checkpoint;
    public String route_id;
    public String location;
    public Integer ordinal;

    public Waypoints(String waypoint_id, String checkpoint, String route_id, String location, Integer ordinal){
        this.waypoint_id = waypoint_id;
        this.checkpoint = checkpoint;
        this.route_id = route_id;
        this.location = location;
        this.ordinal = ordinal;
    }
}