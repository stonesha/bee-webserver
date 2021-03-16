package com.Bee.BeeWebserver;

public class Reports{

    public String reported_at;
    public String type;
    public String info;
    public String report_id;
    public String evac_id;
    public String reporter_id;
    public Float latitude;
    public Float longitude;

    public Reports(String reported_at, String type, String info, String report_id, String evac_id, String reporter_id, Float latitude, Float longitude){
        this.reported_at = reported_at;
        this.type = type;
        this.info = info;
        this.report_id = report_id;
        this.evac_id = evac_id;
        this.reporter_id = reporter_id;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}