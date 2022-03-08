# This is demo project with Cucumber Selenium tests and integration with Blazemeter for parallel execution.

## Prerequisites:
- Java.
- Maven.

## Installation steps:
- Open the Maven project in your IDE.
- Set Blazemeter credentials in the BzmConfig file.
- From project folder in terminal run `mvn clean test` to start tests.

## Additional info:
- By default, all the scenarios across feature files run in 10 parallel threads.
- You can change the number of parallel threads in pom.xml by changing value for the `dataproviderthreadcount` property.
