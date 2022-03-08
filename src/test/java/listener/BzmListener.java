package listener;

import hellocucumber.BaseTest;
import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.*;
import org.openqa.selenium.JavascriptExecutor;

import java.util.HashMap;
import java.util.Map;

public class BzmListener implements ConcurrentEventListener {

    @Override
    public void setEventPublisher(EventPublisher eventPublisher) {
        eventPublisher.registerHandlerFor(TestStepStarted.class, this::onTestStepStarted);
        eventPublisher.registerHandlerFor(TestStepFinished.class, this::onTestStepFinished);
    }

    public void onTestStepStarted(TestStepStarted onTestStepStarted) {
        // Excluding Before/After hooks from test report
        if (onTestStepStarted.getTestStep() instanceof PickleStepTestStep) {
            //Sending step and scenario names
            setBzmStartResult(((PickleStepTestStep) onTestStepStarted.getTestStep()).getStep().getText(),
                    onTestStepStarted.getTestCase().getName());
        }
    }

    public void onTestStepFinished(TestStepFinished onTestStepFinished) {
        // Excluding Before/After hooks from test report
        if (onTestStepFinished.getTestStep() instanceof PickleStepTestStep) {
            // Setting success status for a step with PASSED status
            if (onTestStepFinished.getResult().getStatus() == Status.PASSED) {
                setBzmStopResult("success", "");
            // Setting failed status for a step with assertion error
            } else if (onTestStepFinished.getResult().getStatus() == Status.FAILED &&
                    onTestStepFinished.getResult().getError() instanceof AssertionError) {
                System.out.println("DEBUG: " + onTestStepFinished.getResult().getError());
                setBzmStopResult("failed", onTestStepFinished.getResult().getError().toString());
            // Setting broken status for a step without assertion error
            } else if (onTestStepFinished.getResult().getStatus() == Status.FAILED) {
                setBzmStopResult("broken", onTestStepFinished.getResult().getError().toString());
            }
        }
    }

    private void setBzmStartResult(String testCaseName, String testSuiteName) {
        Map<String, String> map = new HashMap<>();
        map.put("testCaseName", testCaseName);
        map.put("testSuiteName", testSuiteName);
        ((JavascriptExecutor) BaseTest.getDriver()).executeAsyncScript("/* FLOW_MARKER test-case-start */", map);
    }

    private void setBzmStopResult(String status, String message) {
        Map<String, String> map = new HashMap<>();
        map.put("status", status);
        map.put("message", message);
        ((JavascriptExecutor) BaseTest.getDriver()).executeAsyncScript("/* FLOW_MARKER test-case-stop */", map);
    }

}
