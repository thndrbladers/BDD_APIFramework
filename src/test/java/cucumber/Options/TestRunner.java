package cucumber.Options;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import resources.CucumberReports;


@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features", plugin = { "json:target/jsonReports/cucumber-report.json",
		"pretty" }, glue = { "stepDefinitions" })
//tags = "@DeletePlaceAPI"
//
public class TestRunner {

	@AfterClass
	public static void tearDown() {
		CucumberReports cr=new CucumberReports();
		cr.generateReports();

	}

}
