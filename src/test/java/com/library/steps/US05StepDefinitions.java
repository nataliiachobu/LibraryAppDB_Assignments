package com.library.steps;

import com.library.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class US05StepDefinitions {
    String actualResult;
    @When("I execute query to find most popular book genre")
    public void i_execute_query_to_find_most_popular_book_genre() {
        DB_Util.runQuery("select book_categories.name, count(*) from book_categories join books b on book_categories.id = b.book_category_id group by book_category_id\n" +
                "having count(*) = (select max(book_count)\n" +
                "                   from (select count(*) as book_count from books group by book_category_id)\n" +
                "                            as counts)");

         actualResult=DB_Util.getFirstRowFirstColumn();

    }
    @Then("verify {string} is the most popular book genre.")
    public void verify_is_the_most_popular_book_genre(String genreType) {
        String expectedResult=genreType;

        Assert.assertEquals(expectedResult,actualResult);

    }
}
