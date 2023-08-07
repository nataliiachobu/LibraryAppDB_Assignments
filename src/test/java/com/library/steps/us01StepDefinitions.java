package com.library.steps;

import com.library.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class us01StepDefinitions {
    String expectedDataCount;
    String actualDataCount;
    List<String> actualDataColumn;
    @Given("Establish the database connection")
    public void establish_the_database_connection() {
       // DB_Util.createConnection(); we added connection in our Hook class in @Before

    }

    @When("Execute query to get all IDs from users")
    public void execute_query_to_get_all_i_ds_from_users() {
        DB_Util.runQuery("select count(id) from users");
        actualDataCount = DB_Util.getFirstRowFirstColumn();
        System.out.println(actualDataCount);


    }

    @Then("verify all users has unique ID")
    public void verify_all_users_has_unique_id() {
        String query = "select count(distinct id) from users";
        DB_Util.runQuery(query);
        expectedDataCount=DB_Util.getFirstRowFirstColumn();
        Assert.assertEquals(expectedDataCount,actualDataCount);

        System.out.println("expectedData = "+expectedDataCount+", actualDataCount = "+actualDataCount);
       // DB_Util.destroy(); connection will be closed in the Hooks class in @After
    }
    @When("Execute query to get all columns")
    public void execute_query_to_get_all_columns() {
        DB_Util.runQuery("select * from users");
        actualDataColumn=DB_Util.getAllColumnNamesAsList();


    }
    @Then("verify the below columns are listed in result")
    public void verify_the_below_columns_are_listed_in_result(List<String> expectedDataColumn) {
     Assert.assertEquals(expectedDataColumn,actualDataColumn);
        System.out.println("actualDataColumn="+actualDataColumn+", "+"expectedDataColumn="+expectedDataColumn);
    }


}
