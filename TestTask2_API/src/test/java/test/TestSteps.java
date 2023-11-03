package test;

import api.WeatherApi;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.BeforeClass;
import pojo.ErrorResponse;
import pojo.WeatherResponse;

import static org.junit.Assert.fail;

public class TestSteps {
    
    @BeforeClass
    public static void init(){
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @When("city name is {string} then UTC must be {string} and TimeZone must be {string}")
    public void cityNameIsThenUTCMustBeAndTimeZoneMustBe(String city, String utc, String timeZone) throws Exception {
        WeatherApi api = new WeatherApi();
        WeatherResponse response = api.getWeatherResponse(city);
        if (!response.getLocation().utc_offset.equals(utc)) {
            fail("Expected UTC offset = " + utc + ", actual UTC offset = " + response.getLocation().utc_offset);
        }
        if (!response.getLocation().timezone_id.equals(timeZone)) {
            fail("Expected TimeZone = " + timeZone + ", actual TimeZone = " + response.getLocation().timezone_id);
        }
    }

    @When("city name is {string} then ErrorCode must be {string} and ErrorType must be {string}")
    public void cityNameIsThenErrorCodeMustBeAndErrorTypeMustBe(String city, String code, String type) throws Exception {
        WeatherApi api = new WeatherApi();
        ErrorResponse response = api.getErrorResponse(city);
        if (response.getError().getCode() != Integer.parseInt(code)) {
            fail("Expected Error code = " + code + ", actual Error code = " + response.getError().getCode());
        }
        if (!response.getError().getType().equals(type)) {
            fail("Expected Error type = " + type + ", actual ErrorType = " + response.getError().getType());
        }
    }
}