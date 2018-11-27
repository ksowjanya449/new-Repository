Feature: Login

@Smoketest
Scenario: Validate site launching
Given launch site using "chrome"
Then title contains "Free SMS"
And close site


@Regression
Scenario Outline: Validate login operation
Given launch site using "chrome"
When Enter mobile number as "<x>"
And enter password as "<y>"
And click login
Then validate output for criteria "<z>"
And close site

Examples:
|     x      |      y    |      z        |
| 8688278137 | sowjanya1 | all_valid     |
| 					 | sowjanya1 | mb_no_blank   |
| 8688278137 | 					 | pwd_blank     |
| 8688278136 | sowjanya1 | mb_no_invalid |
| 8688278137 | sowjanya2 | pwd_invalid   |