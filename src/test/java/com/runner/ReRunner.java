package com.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "@src/test/resources/Failed Scenarios/failed_Scenarios.txt",
				glue = "com.step_defintions",
				dryRun= false,
				monochrome = true)
public class ReRunner {
	
}
