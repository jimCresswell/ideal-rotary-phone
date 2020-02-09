Feature: Users

  Scenario: Create user and search by ID
    Given I create users with name and age as follows
      | Name | Age |
      | Toby | 23  |
      | John | 25  |
      | Toby | 23  |
    When I use the returned IDs to search for the users
    Then I will see that the users name is as follows
      | Toby |
      | John |
      | Toby |
