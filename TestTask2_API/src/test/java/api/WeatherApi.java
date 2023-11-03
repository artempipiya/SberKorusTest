package api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import pojo.ErrorResponse;
import pojo.WeatherResponse;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.mapper.ObjectMapperType.GSON;

@Slf4j
public class WeatherApi {

    public WeatherResponse getWeatherResponse(String city) throws Exception {
        return getWeatherResponse(getResponse(city));
    }

    public ErrorResponse getErrorResponse(String city) throws Exception {
        return getErrorObject(getResponse(city));
    }

    private Response getResponse(String city) throws Exception {
        Response response = response(city);
        log.info("\n\n-----\n" + response.prettyPrint());
        if (HttpStatus.SC_OK != response.statusCode()) {
            throw new Exception("Response code in not 200 OK");
        }
        return response;
    }

    private WeatherResponse getWeatherResponse(Response response) throws Exception {
        if (checkNotError(response)) {
            return getWeatherResponseObject(response);
        } else {
            throw new Exception("Got an error -- see the log above");
        }
    }

    private WeatherResponse getWeatherResponseObject(Response response) {
        return response.as(WeatherResponse.class, GSON);
    }

    private boolean checkNotError(Response response) {
        ErrorResponse errorResponse = getErrorObject(response);
        if (errorResponse.error != null && !errorResponse.success) {
            log.error("Error code = [" + errorResponse.getError().getCode() +
                    "], error type = [" + errorResponse.getError().getType() +
                    "], error info = [" + errorResponse.getError().getInfo());
            return false;
        }
        return true;
    }

    private ErrorResponse getErrorObject(Response response) {
        return response.as(ErrorResponse.class, GSON);
    }
    
    private Response response(String city) {
        Map<String, String> params = new HashMap<>();
        params.put("access_key", getAccessKey());
        params.put("query", city);
        return given()
                .spec(buildRequestSpecification())
                .params(params)
                .log().all()
                .get();
    }

    private RequestSpecification buildRequestSpecification() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setAccept(JSON).setContentType(JSON);
        requestSpecBuilder.setBaseUri("http://api.weatherstack.com/current");
        return requestSpecBuilder.build().log().all();
    }

    private String getAccessKey() {
        return "f9f19d82a4b6962d666dcc6fdc13de4a";
    }
}