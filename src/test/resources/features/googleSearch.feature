Feature: Google search

    Scenario: first result page title contains searched word
        Given I am on MainSearchPage
        When I am searching "automation"
        And I am clicking on first result
        Then I see title contains searched word

#  Scenario: page with domain found on one of five first search pages
#    Given I am on MainSearchPage
#    When I am searching "automation"
#    Then I see at least one result with domain contains searched word (first 5 pages)

