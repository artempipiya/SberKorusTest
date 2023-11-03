package pojo;

import lombok.Data;

@Data
public class Location {
    public String name;
    public String country;
    public String region;
    public String lat;
    public String lon;
    public String timezone_id;
    public String localtime;
    public int localtime_epoch;
    public String utc_offset;
}