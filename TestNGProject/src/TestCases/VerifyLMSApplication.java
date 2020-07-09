package TestCases;

import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VerifyLMSApplication {

	WebDriver driver;
	WebDriverWait driverWait;
	
	@BeforeMethod
	public void openApplication() {
		System.setProperty("webdriver.gecko.driver" , "C:\\Users\\PurushottamChauhan\\Documents\\Software\\SeleniumDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.navigate().to("https://alchemy.hguy.co/lms");
		
		driverWait = new WebDriverWait(driver, 30);
		driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//a[text()='Home']")));
	}
	
	@Test
	public void verifyWebSiteTitle() {
		
		assertEquals(driver.getTitle(),"Alchemy LMS – An LMS Application","Page Title is not correct");
		
	}
	
	@Test
	public void verifyWebSiteHeader() {
		
		String headerText = driver.findElement(By.xpath("//h1[@class='uagb-ifb-title']")).getText();
		
		assertEquals(headerText,"Learn from Industry Experts","Page header is not correct");
		
	}
	
	@Test
	public void verifyTextOfFirstInfoBox() {
		
		List<WebElement> infoBox = driver.findElements(By.xpath(".//div/h3[@class='uagb-ifb-title']"));
		
		assertEquals(infoBox.get(0).getText(),"Actionable Training","First info box Text is not correct");
		
	}
	
	@Test
	public void verifyTextOfSecondInfoBox() {
		
		List<WebElement> infoBox = driver.findElements(By.xpath(".//div/h3[@class='entry-title']"));
		
		assertEquals(infoBox.get(1).getText(),"Email Marketing Strategies","Second most popular course title is not correct");
		
	}
	
	@Test
	public void clickOnMyAccount() {
		
		WebElement infoBox = driver.findElement(By.xpath(".//a[text()='My Account']"));
		infoBox.click();
		
		assertEquals(driver.getTitle(),"My Account – Alchemy LMS", "My Account title is not correct");
		
	}
	
	@Test
	public void loginToApplication() {
		
		WebElement infoBox = driver.findElement(By.xpath(".//a[text()='My Account']"));
		infoBox.click();
		
		driverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//a[text()='Login']")));
		
		driver.findElement(By.xpath(".//a[text()='Login']")).click();
		
		driverWait.until(ExpectedConditions.elementToBeClickable(By.id("user_login")));
		
		driver.findElement(By.id("wp-submit")).click();
		
		driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ld-alert-content")));
		
		String errorMessage = driver.findElement(By.className("ld-alert-content")).getText();
		assertEquals(errorMessage, "Incorrect username or password. Please try again", "Login error message verification failed");
		
		driver.findElement(By.id("user_login")).sendKeys("root");
		driver.findElement(By.id("user_pass")).sendKeys("pa$$w0rd");
		driver.findElement(By.id("wp-submit")).click();
		
		assertEquals(driver.getTitle(),"My Account – Alchemy LMS", "Page Title after login is not correct");
		
		driverWait.until(ExpectedConditions.elementToBeClickable(By.id("wp-admin-bar-my-account")));
		
	    String loggedInUser = driver.findElement(By.id("wp-admin-bar-my-account")).getText();
		assertEquals(loggedInUser, "Howdy, root", "Logged on user is not correctly displayed");
	}
	
	@Test
	public void verifyAllCourses() {
		
		driver.findElement(By.xpath(".//a[text()='All Courses']")).click();
		driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='ld-course-list-items row']")));
		
		WebElement cousesTable = driver.findElement(By.xpath("//div[@class='ld-course-list-items row']"));
		List<WebElement> courses = cousesTable.findElements(By.xpath("./div"));
		
		System.out.println("Total no of courses on the Page - "+courses.size());
		Reporter.log("Total no of courses on the Page - "+courses.size());
	}

    @Test
	public void verifyContactUs() {
		
		driver.findElement(By.xpath(".//a[text()='Contact']")).click();
		driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("wpforms-8-field_0")));
		
		driver.findElement(By.id("wpforms-8-field_0")).sendKeys("Test Name");
		driver.findElement(By.id("wpforms-8-field_1")).sendKeys("test@gmail.com");
		driver.findElement(By.id("wpforms-8-field_3")).sendKeys("Sample Subject");
		driver.findElement(By.id("wpforms-8-field_2")).sendKeys("Sample Comment\nMultiple Lines");
		
		driver.findElement(By.id("wpforms-submit-8")).click();
		
		driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("wpforms-confirmation-8")));
		
		String confirmationMessage = driver.findElement(By.id("wpforms-confirmation-8")).getText();
		
		System.out.println("Message displayed after submission - "+confirmationMessage);
		Reporter.log("Message displayed after submission - "+confirmationMessage);
	}

	@Test
	public void completeSimpleLesson() {
		WebElement infoBox = driver.findElement(By.xpath(".//a[text()='My Account']"));
		infoBox.click();
		
		driverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//a[text()='Login']")));
		
		driver.findElement(By.xpath(".//a[text()='Login']")).click();
		
		driverWait.until(ExpectedConditions.elementToBeClickable(By.id("user_login")));
		
		driver.findElement(By.id("user_login")).sendKeys("root");
		driver.findElement(By.id("user_pass")).sendKeys("pa$$w0rd");
		driver.findElement(By.id("wp-submit")).click();
		
		driver.findElement(By.xpath(".//a[text()='All Courses']")).click();
		driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='ld-course-list-items row']")));
		
		WebElement cousesTable = driver.findElement(By.xpath("//div[@class='ld-course-list-items row']"));
		cousesTable.findElements(By.xpath("./div//img")).get(0).click();
		
		driverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//span[text()='Expand All']")));
		driver.findElement(By.xpath(".//span[text()='Expand All']")).click();
		
		driver.findElements(By.xpath(".//span[@class='ld-topic-title']")).get(0).click();
		
		driverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//a[text()='Back to Lesson']")));
		
		List<WebElement> elements = driver.findElements(By.xpath(".//input[@value='Mark Complete']"));
		
		if(elements.size()>0)
		{
			System.out.println("Mark Complete button found clicking on it");
			Reporter.log("Mark Complete button found clicking on it");
			elements.get(0).click();
		} 
		else
		{
			System.out.println("Mark Complete not found skipping");
			Reporter.log("Mark Complete not found skipping");
		}
		
		driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[text()='Complete']")));
		String text = driver.findElement(By.xpath(".//div[text()='Complete']")).getText();
		
		assertEquals(text, "COMPLETE", "Status of course is not complete");
	}
	
	@AfterMethod
	public void CloseApplication() throws IOException {
		driver.close();
		Runtime.getRuntime().exec("C:\\Windows\\System32\\taskkill.exe /F /IM firefox.exe");
	}

}
