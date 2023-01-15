Feature:All tests at Qualifications user page

  Background:
    When login as "admin" user

  @addSkills @regression
  Scenario: Add skills as Admin
    And navigate to UsersQualifications page
    And add user skill "Communication skills"
    Then verify skill "Communication skills" is added

