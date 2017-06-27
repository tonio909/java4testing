package lesson2.addressbook.tests;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.ArrayList;
import org.openqa.selenium.WebDriver;
import static org.testng.Assert.assertEquals;
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

            Assert.assertEquals(name, "Alekseev");

        }





    }
}



