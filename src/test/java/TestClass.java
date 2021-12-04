import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TestClass extends DriverManager{
    String url = "https://lmslite47vr.demo.mirapolis.ru/mira/";
    @Test
    public void testMatchTitle() throws InterruptedException {
        driver.navigate().to(url);
        String title = driver.getTitle();
        Assert.assertTrue(title.equals("Авторизация"));
        Thread.sleep(2000);
    }

    @Test
    public void testFindElements() throws InterruptedException {
        driver.navigate().to(url);
        WebElement login = driver.findElement(By.xpath("//input[@name='user']"));
        WebElement password = driver.findElement(By.xpath("//input[@name='password']"));
        WebElement submit = driver.findElement (By.xpath("//button[@id='button_submit_login_form']"));
        Assert.assertEquals(true, login.isDisplayed());
        Assert.assertEquals(true, password.isDisplayed());
        Assert.assertEquals(true, submit.isDisplayed());
        Thread.sleep(2000);
    }

    @Test
    public void testAuthenticationAllTrue() throws InterruptedException {
        driver.navigate().to(url);
        WebElement login = driver.findElement(By.xpath("//input[@name='user']"));
        login.sendKeys("fominaelena");
        WebElement password = driver.findElement(By.xpath("//input[@name='password']"));
        password.sendKeys("1P73BP4Z");
        WebElement submit = driver.findElement (By.xpath("//button[@id='button_submit_login_form']"));
        submit.click();
        Thread.sleep(3000);
        WebElement name = driver.findElement(By.xpath("//div[@class='avatar-full-name']"));
        String username = name.getText();
        Assert.assertEquals(username, "Фомина Елена Сергеевна");
    }
    @Test
    public void testAuthenticationFail() throws InterruptedException {
        driver.navigate().to(url);
        WebElement login = driver.findElement(By.xpath("//input[@name='user']"));
        login.sendKeys("xxx");
        WebElement password = driver.findElement(By.xpath("//input[@name='password']"));
        password.sendKeys("000");
        WebElement submit = driver.findElement (By.xpath("//button[@id='button_submit_login_form']"));
        submit.click();
        Thread.sleep(3000);
        Alert al = driver.switchTo().alert();
        String alert = al.getText();
        Assert.assertEquals(alert, "Неверные данные для авторизации");
    }
    @Test
    public void testCopyPassClosed(){
        driver.navigate().to(url);
        String input = "xxx";
        WebElement password = driver.findElement(By.xpath("//input[@name='password']"));
        password.sendKeys(input);
        password.sendKeys(Keys.COMMAND,"a");
        password.sendKeys(Keys.COMMAND,"c");
        WebElement login = driver.findElement(By.xpath("//input[@name='user']"));
        login.sendKeys(Keys.COMMAND,"v");
        Assert.assertNotEquals(login.getAttribute("value"), input);
    }
    @Test
    public void testCopyPassShowed(){
        driver.navigate().to(url);
        String input = "xxx";
        WebElement password = driver.findElement(By.xpath("//input[@name='password']"));
        password.sendKeys(input);
        WebElement show = driver.findElement (By.xpath("//button[@id='show_password']"));
        show.click();
        password.sendKeys(Keys.COMMAND,"a");
        password.sendKeys(Keys.COMMAND,"c");
        WebElement login = driver.findElement(By.xpath("//input[@name='user']"));
        login.sendKeys(Keys.COMMAND,"v");
        Assert.assertEquals (login.getAttribute("value"), input);
    }
    @Test
    public void forgetPassTrueLogin() throws InterruptedException {
        driver.navigate().to(url);
        WebElement forget = driver.findElement(By.xpath("(//div[text()='Забыли пароль?'])[2]"));
        forget.click();
        Thread.sleep(2000);
        WebElement login = driver.findElement(By.xpath("//input[@name='loginOrEmail']"));
        login.sendKeys("fominaelena");
        Thread.sleep(2000);
        WebElement sent = driver.findElement(By.xpath("//button[@class='mira-page-forgot-password-button']"));
        sent.click();
        Thread.sleep(2000);
        WebElement success = driver.findElement(By.xpath("//div[@class='success']"));
        String successStr = success.getText();
        Assert.assertEquals(successStr, "На ваш электронный адрес отправлена инструкция по восстановлению пароля.");
    }
        @Test
        public void forgetPassFalseLogin() throws InterruptedException {
            driver.navigate().to(url);
            WebElement forget = driver.findElement (By.xpath("(//div[text()='Забыли пароль?'])[2]"));
            forget.click();
            Thread.sleep(2000);
            WebElement login = driver.findElement(By.xpath("//input[@name='loginOrEmail']"));
            login.sendKeys("xxx");
            Thread.sleep(2000);
            WebElement sent = driver.findElement (By.xpath("//button[@class='mira-page-forgot-password-button']"));
            sent.click();
            Thread.sleep(2000);
            WebElement success = driver.findElement(By.xpath("//div[@class='alert']"));
            String successStr = success.getText();
            Assert.assertNotEquals(successStr, "На ваш электронный адрес отправлена инструкция по восстановлению пароля.");
    }
}