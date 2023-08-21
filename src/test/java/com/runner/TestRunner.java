package com.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/Features",
				glue = "com.step_defintions",
				dryRun= false,
				monochrome = true,
				plugin= {"rerun:src/test/resources/Failed Scenarios/failed_Scenarios.txt",
						"html:target/cucumber-reports.html",
						"json:target/report.json",
						"junit:target/report.xml",
						"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
						"pretty"},
				publish=true)
public class TestRunner {

}
