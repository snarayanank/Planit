# PLANIT
BDD scenarios for Jupiter Toys portal

## Requirements
JDK version is 11 or higher  
Maven
Cucumber
Gherkin
Selenium
junit
log4j
Firefox browser 70 or higher  
Chrome browser 80 or higher  
Safari browser  
Microsoft Edge browser & mgsedgedriver 80.xx (same version)  

## Test structure

Properties file - src/main/resources/config.<envname>.properties 
Properties file contains all the URLs, environment, default browser type and other configs  

Page Objects - src/main/java/com/planittesting/cloud/jupiter/pageObjects/*.*  
This folder contains all the page object files which is an object repository for storing all web elements 

Base - src/main/java/com/planittesting/cloud/jupiter/utilities/Base.java
This is the base file and it initializes the driver and all other settings 

Feature files - src/test/java/com/planittesting/cloud/jupiter/features/*.*
This folder contains all the feature files to be run

Step Definitions - src/test/java/com/planittesting/cloud/jupiter/stepDefinitions/*.*
This folder contains all the step definitions(Glue) for the feature files

Test Runner - src/test/java/com/planittesting/cloud/jupiter/testRunner/TestRunner.java
This test runner class file executes all the feature files using junit annotation

## Running Test

We can run the tests from maven command line or from Intellij

#### 1. To run from maven you can use the below command:  

mvn clean test -Dcucumber.options="--tags @Regression" -Dbrowser="Firefox" -Denvname="sit"

Parameters:  
envname = sit | uat
browser = Chrome | Firefox | Safari | Edge | Opera
tags = Regression, Contact, Shop, TC_001, TC_002, TC_003, TC_004

#### 2. To run from Intellij  

You can right click on the Test Runner class file or the pom.xml file and select ‘Run’ (using junit run configuration). If cucumber is installed, we can also right click on the feature files and run them directly.

## Reports

Reports will be generated in the below location after the test run is completed:  
/target/surefire-reports/

/target/cucumber-report-maven/

/target/site/cucumber-report.html

## Logs
You can check the run log at
Planit/planit.log

## Other questions
1. What other possible scenario’s would you suggest for testing the Jupiter Toys application?


2. Jupiter Toys is expected to grow and expand its offering into books, tech, and modern art. 
   We are expecting the of tests will grow to a very large number.
    - What approaches could you used to reduce overall execution time?
    - How will your framework cater for this?
    
3. Describe when to use a BDD approach to automation and when NOT to use BDD 