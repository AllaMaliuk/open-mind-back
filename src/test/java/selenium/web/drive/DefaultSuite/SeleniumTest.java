package selenium.web.drive.DefaultSuite;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SeleniumTest {
  private WebDriver driver;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeEach
  public void setUp() {
    System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--remote-allow-origins=*");
    driver = new ChromeDriver(options);
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
  }

  @Test
  public void test2ForgotPassword() {
    driver.get("http://localhost:3000/");
    driver.findElement(By.id("forgotPassword")).click();
    driver.findElement(By.id("email")).click();
    driver.findElement(By.id("email")).clear();
    driver.findElement(By.id("email")).sendKeys("test@test");
    driver.findElement(By.id("submitEmail")).click();
    driver.close();
  }

  @Test
  public void test5ProfileInfoModify() {
    driver.get("http://localhost:3000/");
    driver.findElement(By.id("email")).clear();
    driver.findElement(By.id("email")).sendKeys("test@test");
    driver.findElement(By.id("password")).click();
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("test");
    driver.findElement(By.id("loginButton")).click();
    driver.findElement(By.cssSelector("#myProfile > p")).click();

    driver.findElement(By.id("modifyInfoButton")).click();

    driver.findElement(By.id("modifyDataButton")).click();
    driver.close();
  }

  @Test
  public void test4Profile() {
    driver.get("http://localhost:3000/");
    driver.findElement(By.id("email")).clear();
    driver.findElement(By.id("email")).sendKeys("test@test");
    driver.findElement(By.id("password")).click();

    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("test");
    driver.findElement(By.id("loginButton")).click();
    driver.findElement(By.id("myProfile")).click();
    driver.close();
  }

  @Test
  public void test3Login() {
    driver.get("http://localhost:3000/");
    driver.findElement(By.id("email")).clear();
    driver.findElement(By.id("email")).sendKeys("test@test");
    driver.findElement(By.id("password")).click();
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("test");
    driver.findElement(By.id("loginButton")).click();
    driver.close();
  }

  @Test
  public void test6profilePasswordModify() {
    driver.get("http://localhost:3000/");
    driver.findElement(By.id("email")).click();
    driver.findElement(By.id("email")).clear();
    driver.findElement(By.id("email")).sendKeys("test@test");
    driver.findElement(By.id("password")).click();
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("test");
    driver.findElement(By.id("loginButton")).click();
    driver.findElement(By.cssSelector("#myProfile > p")).click();
    driver.findElement(By.id("modifyPasswordButton")).click();
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("test");
    driver.findElement(By.id("confirmPassword")).click();
    driver.findElement(By.id("confirmPassword")).clear();
    driver.findElement(By.id("confirmPassword")).sendKeys("test");
    driver.findElement(By.cssSelector(".css-k008qs > #modifyPasswordButton")).click();
    driver.close();
  }

  @Test
  public void test7NewPost() {
    driver.get("http://localhost:3000/");
    driver.findElement(By.id("email")).click();
    driver.findElement(By.id("email")).click();
    driver.findElement(By.id("email")).clear();
    driver.findElement(By.id("email")).sendKeys("test@test");
    driver.findElement(By.id("password")).click();
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("test");
    driver.findElement(By.id("loginButton")).click();
    driver.findElement(By.id("newPostButton")).click();
    driver.findElement(By.id("postTheme")).clear();
    driver.findElement(By.id("postTheme")).sendKeys("test");
    driver.findElement(By.id("postContent")).click();
    driver.findElement(By.id("postContent")).clear();
    driver.findElement(By.id("postContent")).sendKeys("test");
    driver.findElement(By.id("createPostButton")).click();
    driver.close();
  }

  @Test
  public void test8ViewPostAndComment() {
    driver.get("http://localhost:3000/");
    driver.findElement(By.id("email")).click();
    driver.findElement(By.id("email")).clear();
    driver.findElement(By.id("email")).sendKeys("test@test");
    driver.findElement(By.id("password")).click();
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("test");
    driver.findElement(By.id("loginButton")).click();
    driver.findElement(By.id("detailsButton")).click();
    driver.findElement(By.id("commentary")).click();
    driver.findElement(By.id("commentary")).clear();
    driver.findElement(By.id("commentary")).sendKeys("test");
    driver.findElement(By.id("commentarySubmitButton")).click();
    driver.close();
  }

  @Test
  public void testLogout() {
    driver.get("http://localhost:3000/");
    driver.findElement(By.id("email")).click();
    driver.findElement(By.id("email")).clear();
    driver.findElement(By.id("email")).sendKeys("test@test");
    driver.findElement(By.id("password")).click();
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("test");
    driver.findElement(By.id("loginButton")).click();
    driver.findElement(By.id("header-log-oug-button")).click();
    driver.close();
  }

  @Test
  public void test1Register() throws IOException {
    driver.get("http://localhost:3000/");
    driver.findElement(By.id("register")).click();
    driver.findElement(By.id("email")).click();
    driver.findElement(By.id("email")).clear();
    driver.findElement(By.id("email")).sendKeys("test@test");
    driver.findElement(By.id("username")).click();
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("test");
    screen("register.png");
    driver.findElement(By.id("password")).click();
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("test");
    driver.findElement(By.cssSelector(".css-164r41r")).click();
    driver.findElement(By.id("confirmPassword")).click();
    driver.findElement(By.id("confirmPassword")).clear();
    driver.findElement(By.id("confirmPassword")).sendKeys("test");
    driver.findElement(By.id("registerButton")).click();
    driver.close();
  }

  private void screen(String screenName) throws IOException {
    TakesScreenshot screenshot = ((TakesScreenshot) driver);
    File SrcFile = screenshot.getScreenshotAs(OutputType.FILE);

    File DestFile = new File("src/test/java/selenium/web/drive/screenshots/" + screenName);

    FileUtils.copyFile(SrcFile, DestFile);
  }

  @AfterEach
  public void tearDown() {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }
}
