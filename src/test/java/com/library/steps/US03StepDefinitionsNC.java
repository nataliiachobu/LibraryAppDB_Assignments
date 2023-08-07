package com.library.steps;

import com.library.pages.BookPage;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class US03StepDefinitionsNC {
    BookPage bookPage = new BookPage();
    List<String> actualList;

    @When("the user navigates to {string} page")
    public void the_user_navigates_to_page(String moduleType) {
        bookPage.navigateModule(moduleType);
    }

    @When("the user clicks book categories")
    public void the_user_clicks_book_categories() {

       Select select= new Select(bookPage.mainCategoryElement);
       List<WebElement> list=select.getOptions();
       actualList=new ArrayList<>();
      for (WebElement each : list){
          actualList.add(each.getText());
      }
      actualList.remove(0);
        System.out.println(actualList);
    }

    @Then("verify book categories must match book_categories table from db")
    public void verify_book_categories_must_match_book_categories_table_from_db() {
        DB_Util.runQuery("select name from book_categories;");
       List <String> expectedList=DB_Util.getColumnDataAsList(1);
        System.out.println(expectedList+" = "+actualList);
        Assert.assertEquals(expectedList,actualList);

    }
}
