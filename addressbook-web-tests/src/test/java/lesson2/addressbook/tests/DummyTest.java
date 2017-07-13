package lesson2.addressbook.tests;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.ArrayList;
import org.openqa.selenium.WebDriver;

import static java.awt.SystemColor.window;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Iterator;
import java.util.List;

public class DummyTest extends DummyTestHelper {


    @Test
    public void returnHomePage() {

        fillLoginForm("admin", "secret");

        clickLoginOnHomepage();

        clickLinkOnHomepage("home");






        List<WebElement> elements = selenium.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement setOfElements : elements) {
            String name = setOfElements.getText();
            System.out.println("Имя = " + name);


            //Assert.assertEquals(name, "Alekseev Anton111");
            //Assert.assertTrue(name.startsWith("Alekseev"));

        }

        List<WebElement> elements1 = new ArrayList<WebElement>() {
        //return elements1;
        };




    }
}



