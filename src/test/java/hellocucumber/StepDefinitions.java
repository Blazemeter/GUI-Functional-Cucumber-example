package hellocucumber;

import io.cucumber.java.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;

import static org.testng.Assert.*;
import static hellocucumber.BaseTest.*;

public class StepDefinitions {

    @Before()
    public void setUp() {
        getDriver();
    }

    @After
    public void tearDown() {
        quitDriver();
    }

    private String actualUrl;

    @Given("I navigate to blaze demo")
    public void i_navigate_to_blaze_demo() {
        getDriver().get("https://blazedemo.com/");
    }

    @When("I check current URL")
    public void i_check_current_url() {
        actualUrl = getDriver().getCurrentUrl();
    }

    @Then("I should see failed")
    public void i_should_see_failed() {
        assertEquals("Some other URL", actualUrl);
    }

    @Then("I should see broken")
    public void i_should_see_broken() {
        getDriver().findElement(By.xpath("//some-invalid-locator"));
    }

    @Then("I should see passed")
    public void i_should_see_passed() {
        assertEquals("https://blazedemo.com/", actualUrl);
    }

    @Then("Parametrized {string}")
    public void parametrized(String param) {
        assertEquals("data", param);
    }

}
