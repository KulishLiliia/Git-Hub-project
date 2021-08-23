package com.git_hub.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		
		
		tags= {"@git_hub_api"},
		
		
		
		features="src/test/resources/Features" 
		, glue= "com/git_hub/step_defenition" 
		,strict=true
//		,dryRun=true
		,monochrome=true
		,stepNotifications=true
		
		
		)



public class TestRunner {

}
