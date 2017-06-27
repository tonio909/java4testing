package lesson2.addressbook.tests;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.List;

public class DummyTestHelper {


    public static WebDriver selenium = new ChromeDriver();

    @AfterMethod

    public static void tearDown() {
            selenium.quit();
    }


    @BeforeMethod

    public void ensurePreconditions() {
        selenium.get("http://localhost/addressbook/");
    }


    public static void fillLoginForm(String username, String password) {

        selenium.findElement(By.xpath("//form[@name='LoginForm']//input[@name='user']")).sendKeys(username);

        selenium.findElement(By.xpath("//form[@name='LoginForm']//input[@name='pass']")).sendKeys(password);
    }


    protected void clickLoginOnHomepage() {
        selenium.findElement(By.xpath("//input[@value='Login']")).click();
    }


    protected void clickLinkOnHomepage(String linkTextName) {
        selenium.findElement(By.linkText(linkTextName)).click();
    }


}