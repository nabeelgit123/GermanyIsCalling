package automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class loginTest {

	WebDriver driver;
	static ExtentReports report;
	static ExtentTest test;

	@BeforeSuite
	public void setupSuite() {
		report = new ExtentReports("D:\\GermanyIsCalling\\automation\\Reports\\report.html", true);
	}

	@BeforeMethod
	public void Setup(){
		//Initialize WebDriver
		System.setProperty("webdriver.chrome.driver", "./chromedriverFolder/chromedriver.exe");
		driver = new ChromeDriver();

		//Navigate to the Germany Is Calling.
		driver.get("https://app.germanyiscalling.com/common/login/");

		//Window Maximize
		driver.manage().window().maximize();

		test =report.startTest("Login Test Started");
	}
	// setting priority of tests so the tests will run according to the set priorities in order
	@Test(priority = 1) 
	public void loginWithValidCreedentials(){
		// Reports short words about the whole test
		test.log(LogStatus.INFO, "Login with valid credentials");

		// Finding username field and entering valid user name
		WebElement username=driver.findElement(By.cssSelector("#username"));
		username.sendKeys("nabeelskh22@gmail.com");


		// Finding password field and entering password
		WebElement password=driver.findElement(By.cssSelector("#password"));
		password.sendKeys("nabeel@2001");


		// Finding Login and clicking on Log In
		WebElement loginButton=driver.findElement(By.xpath("//*[text()='Log In']"));
		loginButton.click();

		//Finding the Logged In user and verifying the username with expected one
		String expectedLoggedInUserName="Nabeel Shaikh";
		WebElement loggedInUser=driver.findElement(By.cssSelector("#dropdownUser1 span"));
		String loggedInUserName=loggedInUser.getText();
		
		if(loggedInUserName.equals(expectedLoggedInUserName)) {
			Assert.assertEquals(loggedInUserName,expectedLoggedInUserName,"User not logged In");
			test.log(LogStatus.PASS, loggedInUserName);
		}
		else {
			test.log(LogStatus.FAIL,"User not logged In");
		}
	}

	@Test(priority = 2)
	public void loginWithInValidUsername(){
		// Reports short words about the whole test
		test.log(LogStatus.INFO, "Login Test With Invalid Username");

		// Finding username field and entering invalid user name
		WebElement username=driver.findElement(By.cssSelector("#username"));
		username.sendKeys("nabeelskh@gmail.com");


		// Finding password field and entering valid password
		WebElement password=driver.findElement(By.cssSelector("#password"));
		password.sendKeys("nabeel@2001");


		// Finding Login and clicking on Log In
		WebElement loginButton=driver.findElement(By.xpath("//*[text()='Log In']"));
		loginButton.click();

		// Verifying if the error message is displayed or not
		String expectedErrorMessage="Please enter a correct username and password. Note that both fields may be case-sensitive.";
		WebElement errorMessage=driver.findElement(By.cssSelector(".alert-danger  li"));
		Assert.assertTrue(errorMessage.isDisplayed(),"Expected Error message to be displayed");
		String errorMessageText=errorMessage.getText();
		
		if(errorMessageText.equals(expectedErrorMessage)) 
		{
			Assert.assertEquals(errorMessageText,expectedErrorMessage,"Error message is not as expected");
			test.log(LogStatus.PASS, errorMessageText);
		}
		else {
			test.log(LogStatus.FAIL, "Test Failed");
			 }
		

	}

	@Test(priority = 3)
	public void loginWithInValidPassword(){
		test.log(LogStatus.INFO, "Login test with Invalid password");
		
		// Finding username field and entering valid user name
		WebElement username=driver.findElement(By.cssSelector("#username"));
		username.sendKeys("nabeelskh22@gmail.com");

		// Finding password field and entering invalid password
		WebElement password=driver.findElement(By.cssSelector("#password"));
		password.sendKeys("nabeel@2");		     

		// Finding Login and clicking on Log In
		WebElement loginButton=driver.findElement(By.xpath("//*[text()='Log In']"));
		loginButton.click();

		// Verifying if the error message is displayed or not
		String expectedErrorMessage="Please enter a correct username and password. Note that both fields may be case-sensitive.";
		WebElement errorMessage=driver.findElement(By.cssSelector(".alert-danger  li"));
		Assert.assertTrue(errorMessage.isDisplayed(),"Expected Error message to be displayed");
		String errorMessageText=errorMessage.getText();
		
		if(errorMessageText.equals(expectedErrorMessage)) {
		Assert.assertEquals(errorMessageText,expectedErrorMessage,"Error message is not as expected");
		test.log(LogStatus.PASS, errorMessageText);
		}
		else {
			test.log(LogStatus.FAIL, "Test Failed");
			}
		
	}

	@Test(priority = 4)
	public void loginWithEmptyFields() {
		test.log(LogStatus.INFO, "Login test with empty username and password");
		
		// Finding Login and clicking on Log In
		WebElement loginButton=driver.findElement(By.xpath("//*[text()='Log In']"));
		loginButton.click();

		//Verifying Validation message 
		String expectedEmailErrorMessage="Email: This field is required.";
		String expectedPasswordErrorMessage="Password: This field is required.";

		WebElement errorMessage=driver.findElement(By.cssSelector(".alert-danger ul"));	
		WebElement emailErrorMessage=driver.findElement(By.cssSelector(".alert-danger li:nth-child(1)"));
		WebElement passwordErrorMessage=driver.findElement(By.cssSelector(".alert-danger li:nth-child(2)"));
		Assert.assertTrue(errorMessage.isDisplayed(),"Validation  Message should be displayed");

		//Verifying the message Text should be as expected 
		String emailErroeMessageText=emailErrorMessage.getText();
		String passwordErrorMessageText = passwordErrorMessage.getText();
		
		if(emailErroeMessageText.equals(expectedEmailErrorMessage) && passwordErrorMessageText.equals(expectedPasswordErrorMessage)) {
		Assert.assertEquals(emailErroeMessageText,expectedEmailErrorMessage,"Email Validation message not as expected");
		Assert.assertEquals(passwordErrorMessageText,expectedPasswordErrorMessage,"Email Validation message not as expected");
		test.log(LogStatus.PASS, passwordErrorMessageText);
		}
		else {
			test.log(LogStatus.FAIL, "Test Failed");
			}

	}


	@Test(priority = 5)
	public void loginWithSpecialCharaters() {
		test.log(LogStatus.INFO, "Login Test with special characters");
		
		// Finding username field and entering invalid user name
		WebElement username=driver.findElement(By.cssSelector("#username"));
		username.sendKeys("@@@@@@@@@@@");


		// Finding password field and entering valid password
		WebElement password=driver.findElement(By.cssSelector("#password"));
		password.sendKeys("nabeel@2001");


		// Finding Login and clicking on Log In
		WebElement loginButton=driver.findElement(By.xpath("//*[text()='Log In']"));
		loginButton.click();

		// Verifying if the error message is displayed or not
		String expectedErrorMessage="Please enter a correct username and password. Note that both fields may be case-sensitive.";
		WebElement errorMessage=driver.findElement(By.cssSelector(".alert-danger  li"));
		Assert.assertTrue(errorMessage.isDisplayed(),"Expected Error message to be displayed");
		String errorMessageText=errorMessage.getText();
		if(errorMessageText.equals(expectedErrorMessage)) {
		Assert.assertEquals(errorMessageText,expectedErrorMessage,"Error message is not as expected");
		test.log(LogStatus.PASS, errorMessageText);
		}
		else {
			test.log(LogStatus.FAIL, "Test Failed");
			}
	}



	@AfterMethod
	public void tearDown() {
		// Close the browser after each test
		driver.quit();
		report.endTest(test);
	}

	@AfterSuite
	public void tearDownSuite() {
		// Flush the report after all tests
		report.flush();
		report.close();
	}
}
