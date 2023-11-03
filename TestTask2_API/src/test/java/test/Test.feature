Feature: Weather search

  Scenario Outline: parameterized find the weather for particular test city name
    When city name is "<city>" then UTC must be "<UTC>" and TimeZone must be "<TimeZone>"
    Examples:
    
      | city          | UTC  | TimeZone              |
      | New York      | -4.0 | America/New_York      |
      | Santa Barbara | -7.0 | America/Los_Angeles   |
      | Washington    | -4.0 | America/New_York      |
      | Detroit       | -4.0 | America/Detroit       |
  
  Scenario Outline: negative test for non-existent city
    When city name is "<city>" then ErrorCode must be "<code>" and ErrorType must be "<type>"
    Examples:
    
      | city           | code  | type              |
      | Prostokvashino | 615   | request_failed    |