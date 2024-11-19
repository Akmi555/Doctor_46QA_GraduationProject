package com.doctor.tests_cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions
        (
                features = "src/test/resources/features",
                glue = "doctor.stepsDefinitions",
                plugin = {"pretty", "html:target/cucumber-reports.html"}
              //  tags = "@HomePage" //"@Login"
        )

public class TestRunner {
}
