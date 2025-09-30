package cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)

@CucumberOptions(features="src/test/java/features",glue="stepDefinitions")
/*
 * @CucumberOptions(features="src/test/java/features", glue=
 * {"stepDefinitions","hooks"}, tags="@Regression" ,plugin={ "pretty",
 * "html:target/cucumber-reports.html", "json:target/cucumber.json",
 * "junit:target/cucumber.xml" },dryRun=true, monochrome = true)
 */

public class TestRunner {

	
	
}
