package pojo;

import lombok.Data;

@Data
public class Request {
    public String type;
    public String query;
    public String language;
    public String unit;
}