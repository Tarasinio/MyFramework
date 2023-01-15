Feature:All tests at Qualifications user page

  Background:
    When login as "admin" user

  @addLicense @regression
  Scenario: Add license as Admin
    And navigate to UsersQualifications page
    And add user license "LHunter"
    #Then verify skill "Communication skills" is added