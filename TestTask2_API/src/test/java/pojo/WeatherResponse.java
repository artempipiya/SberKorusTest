package pojo;

import lombok.Data;

@Data
public class WeatherResponse {
    private Current current;
    private Request request;
    private Location location;
}