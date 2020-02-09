package com.bnkbl;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        glue = {"com.bnkbl.stepdefs"},
        features = {"classpath:features"}
)
public class RunTests {
}
