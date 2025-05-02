import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber-reports.html"}, // Reporting
        glue = {"utility", "stepDefinition" },
        features = {"src/test/resources/features"}
)
public class RunnerTest {
}
