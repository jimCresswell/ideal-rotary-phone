#Notes/Comments below here

## Scalability

### Dependency Injection
I used dependency injection (using the Cucumber standard PicoContainer https://github.com/cucumber/cucumber-jvm/tree/master/picocontainer) to enable scalable sharing of state between steps. The data storing class is here [`src\test\java\com\bnkbl\stepdefs\support\UserData.java`](src\test\java\com\bnkbl\stepdefs\support\UserData.java).

Obviously that's overkill in this case, but in larger projects it supports:
  * Easy scaling to more complex data sharing.
  * Sharing state between steps in different StepDefinition classes.
  * Specifying the test data only once (see below) and passing it between steps.

This also allows checks on shared state to guard against the implicit order dependency between steps. For instance trying to access the returned user IDs before they are set will throw a descriptive `IllegalStateException`. This kind of robustness is useful in teams of mixed experience levels.

### Data Handling
I considered converting the age input data to an `int` at the step argument level with a custom DataTableType https://cucumber.io/docs/cucumber/configuration/ and a User class. I decided that in this case the existing code complexity is minimal and by implementing `User` I'd be starting to mimic the original SUT code, potentially testing how well the mimic aligned with the original rather than whether the SUT behaved as expected.

See below for an alternative model using data labels with data stored in code.

### Code Structure
In more complex projects I might 
  * Separate all test data functionality and data into separate classes, if relevant possibly into separate dependencies that can be shared across test projects.
  * Move the SUT interaction code another layer of abstraction down from the step definitions
    * Allowing easier and more declarative composition of new steps.
    * Distancing step definitions from churn necessitated by SUT interface implementation changes.  

## Intention and Clarity
If the tests were to be long-lived or expanded on, then the feature could be rewritten to focus on conveying the intent of the test:

```Gherkin
    Given a list of "typical" users
    When those users are added to the system
    Then the same users can be retrieved
```
Where `"typical"` is an example of an interchangeable label specifying a set of data describing a particular scenario for the SUT.

This approach provides:
  * Human readable descriptions of the intention of the expected SUT behaviour.
  * Data that is specified
    * With a descriptive text identifier, deferring structure, complexity, and potential remoteness to code.
    * Only once, reducing the chances of a specification error (an alternative is to pass the label in multiple steps).
  * Easy modification to a `Scenario Outline` where multiple scenarios (data labels for inputs and outcomes) can be specified concisely.
  * The potential for test reports easily understood by e.g. board members. 
  
If the intent is to check that users with the same data can exist simultaneously in the system with different IDs, then that is worth pulling out into a separate statement of intended behaviour, even though potentially only the data would change.

This style of test specification isn't necessarily suitable for e.g. heavily data driven testing, but those engineering facing scenarios could be covered by tests created in Junit or other tools, with the results conveyed to appropriate audiences, e.g. team developers rather than developers and product managers.

## Additional Scenarios
Other scenarios that could be explored:
  * The SUT supports negative ages
    * Is that intentional?
    * Who is the audience for that information? Where do they expect to see that information?
  * How does the system respond to e.g. UTF-8 strings?
  * Injecting known valid but troublesome strings.

## Dependencies
I updated the dependencies. 