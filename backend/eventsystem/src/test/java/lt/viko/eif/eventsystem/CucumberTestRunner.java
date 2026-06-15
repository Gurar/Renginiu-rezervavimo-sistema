package lt.viko.eif.eventsystem;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "lt.viko.eif.eventsystem.steps",
        plugin = { "pretty" }
)
public class CucumberTestRunner {
}