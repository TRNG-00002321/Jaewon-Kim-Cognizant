Feature: User Authentication
    As a registered user
    I want to log into my account
    So that I can access my account and manage my information

    Background:
        Given the application is running
        And the test database is already seeded with users

    Scenario: Successful login with valid credentials
        Given the user is on the login page
        When the user enters username "tomsmith"
        And the user enters password "SuperSecretPassword!"
        And the user clicks the login button
        Then the user should be redirected to the Secure Area page
        And the user should see the message "You logged into a secure area!"