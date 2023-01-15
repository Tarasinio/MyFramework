
Feature: Positive and Negative Logins

  @loginTest @regression
  Scenario: Login as Admin user
    And login as "admin" user

  @login_Logout @regression
  Scenario: Login/Logout as Admin user
    And login as "admin" user
    #Then click on Sign Out from Acc Menu and verify user is signed out

