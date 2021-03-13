package com.Bee.BeeWebserver;

public class Evacuee{

    public String user_id;
    public String evac_id;
    public Boolean notification_token;
    public String notification_sent_at;
    public Boolean acknowledged;
    public String acknowledged_at;
    public Boolean safe;
    public String marked_safe_at;
    public String location;
    public String location_updated_at;
    public String name;

    public Evacuee(String user_id, String evac_id, Boolean notification_token, String notification_sent_at, Boolean acknowledged, String acknowledged_at, Boolean safe, String marked_safe_at, String location, String location_updated_at, String name){
        this.user_id = user_id;
        this.evac_id = evac_id;
        this.notification_token = notification_token;
        this.notification_sent_at = notification_sent_at;
        this.acknowledged = acknowledged;
        this.acknowledged_at = acknowledged_at;
        this.safe = safe;
        this.marked_safe_at = marked_safe_at;
        this.location = location;
        this.location_updated_at = location_updated_at;
        this.name = name;
    }
}