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

## Other questions. 
1. What other possible scenario’s would you suggest for testing the Jupiter Toys application?  
      a. Test user login with valid and invalid data and with customer level. 
      b. Go to shop page using 'Start Shopping' button. 
      c. On Contact page click on the Labels of mandatory fields and check the error messages. 
      d. Test with invalid email address and validate the error message. 
      e. On the cart page, try increasing or reducing the quantity and validate. 
      f. Remove items from the cart and validate (Clicking Yes and No on confirmation). 
      g. Empty cart and validate (Clicking Yes and No on confirmation). 
      h. Validate Checkout button. 
      i. From cart go back to shop page, add more items and validate. 

2. Jupiter Toys is expected to grow and expand its offering into books, tech, and modern art.   
   We are expecting the of tests will grow to a very large number.  
    - What approaches could you used to reduce overall execution time?  
    - How will your framework cater for this?  
      a. I would use selenium grid to have parallel test execution. 
      b. Use dynamic timeouts (fluent wait, explicit wait etc) to control the test run time. 
      c. I would use Headless test execution where possible. 
      d. I would use API to create data or any pre-requisite where possible instead of using UI for faster execution. 
      e. Use CI tools like Jenkins to manage regression test execution. 
      f. Use docker for test execution using tools like Zalenium. 
      This framework can be enhanced to accommodate all the above said recommendations, while some of them are already there.  
    
3. Describe when to use a BDD approach to automation and when NOT to use BDD. 
   I would use BDD approach for the following reasons,  
    a. BDD will be useful to collaborate business users, developers and testers.  
    b. To improve communication, drive consistency in specification and avoid misunderstandings. 
    c. Helps to maintain a living documentation and no need to maintain separate test case. 
    d. When we want to write tests/automation scripts before the code is developed. 
    e. When we want the testers to get involved very early in the project lifecycle. 
   I won't use BDD for the below reasons,  
    a. If the project is not on agile. 
    b. If the project is small and one-off projects. 
    c. If it's an existing project and requires a lot of effort to switch. 
    d. When it's difficult to work with business users. 
    e. When we develop the code first  
