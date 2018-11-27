Feature: Logout
@Smoketest

Scenario: Validate logout operation

Given launch site using "chrome"
When do login with valid data
|     mobile no      |    password   |
|     8688278137     |   sowjanya1   |
And do logout
Then home page will be reopened   
And close site