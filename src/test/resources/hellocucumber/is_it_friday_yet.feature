Feature: Blaze Demo
  Cucumber tests running in BlazeGrid

  Scenario: Some failed test
    Given I navigate to blaze demo
    When I check current URL
    Then I should see failed

  Scenario: Some broken test
    Given I navigate to blaze demo
    When I check current URL
    Then I should see broken

  Scenario: Some passed test
    Given I navigate to blaze demo
    When I check current URL
    Then I should see passed

  Scenario Outline: Some parametrized test
    Then Parametrized "<text>"

    Examples:
      | text  |
      | data  |
      | data2 |