package com.bnkbl.stepdefs;

import com.bnkbl.UserRepository;
import com.bnkbl.stepdefs.support.UserData;
import com.bnkbl.sut.SimpleUserRepository;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.datatable.DataTable;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class UsersStepDefs {

    // This is the System Under Test Do NOT modify the SIT.
    private UserRepository repository = new SimpleUserRepository();

    // Helper class to share constants and returned data between steps.
    private UserData data;

    // Inject UserData helper class instantiated with the PicoContainer DI framework.
    public UsersStepDefs(UserData data) {
        this.data = data;
    }

    @Given("I create users with name and age as follows")
    public void i_create_a_user_with_name_and_age_as_follows(DataTable dataTable) {
        List<Map<String, String>> inputUsers = dataTable.asMaps();

        // Add the passed users to the SUT.
        List<String> returnedIds = inputUsers
                                    .stream()
                                    .map(
                                            user -> repository.createUser(
                                                    user.get(UserData.NAME),
                                                    Integer.parseInt(user.get(UserData.AGE))
                                            )
                                    )
                                    .collect(Collectors.toList());

        // Collect the returned IDs.
        data.setReturnedIds(returnedIds);
    }

    @When("I use the returned IDs to search for the users")
    public void i_search_by_their_IDs() {
        // Use the returned IDs to get the user names (in order) from the SUT.
        List<String> returnedNames = data.getReturnedIds()
                                        .stream()
                                        .map(
                                                Id -> repository.getNameById(Id)
                                        )
                                        .collect(Collectors.toList());

        // Collect the returned user names.
        data.setReturnedNames(returnedNames);
    }

    @Then("I will see that the users name is as follows")
    public void i_will_see_that_the_users_name_is_as_follows(DataTable dataTable) {
        List<String> expectedNames = dataTable.asList();

        // Assert that names and name order match.
        assertThat(data.getReturnedNames())
                .as("Names and name order should match")
                .isEqualTo(expectedNames);
    }
}
