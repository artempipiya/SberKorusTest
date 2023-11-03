package pojo;

import lombok.Data;

@Data
public class ErrorResponse {

    public boolean success;
    public Error error;
}