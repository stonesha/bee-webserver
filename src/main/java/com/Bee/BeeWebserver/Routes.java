package com.Bee.BeeWebserver;

public class Routes{

    public String status;
    public String route_id;
    public String event_id;
    public String last_update;
    
    public Routes(String status, String route_id, String event_id, String last_update){
        this.status = status;
        this.route_id = route_id;
        this.event_id = event_id;
        this.last_update = last_update;
    }
}