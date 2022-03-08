Feature: Blaze Demo
  Cucumber tests running in BlazeGrid

  Scenario: Some failed test 2
    Given I navigate to blaze demo
    When I check current URL
    Then I should see failed

  Scenario: Some broken test 2
    Given I navigate to blaze demo
    When I check current URL
    Then I should see broken

  Scenario: Some passed test 2
    Given I navigate to blaze demo
    When I check current URL
    Then I should see passed