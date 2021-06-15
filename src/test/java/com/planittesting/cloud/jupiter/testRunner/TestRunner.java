package com.planittesting.cloud.jupiter.testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/com/planittesting/cloud/jupiter/features",
        glue = {"com/planittesting/cloud/jupiter/stepDefinitions"},
        plugin= {"pretty",
        "html:target/site/cucumber-report.html",
        "json:target/cucumber.json"},
        monochrome = true,
        tags = "@Regression"
        )

public class TestRunner {

}