package LamdaTestFiles;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.HashMap;

public class LambdaTestForIEBrowser {
	private WebDriver driver =null;
	
	@BeforeMethod
	public void setup() throws MalformedURLException {
		InternetExplorerOptions browserOptions = new InternetExplorerOptions();
		browserOptions.setPlatformName("Windows 10");
		browserOptions.setBrowserVersion("11");
		HashMap<String, Object> ltOptions = new HashMap<String, Object>();
		ltOptions.put("username", "sayantan_bose");
		ltOptions.put("accessKey", "LT_AfV0RmY8g1UCNs9b526xWqoAihnvaOjCvqMXZlfkQNtIT4V");
		ltOptions.put("visual", true);
		ltOptions.put("video", true);
		ltOptions.put("network", true);
		ltOptions.put("build", "Lambda_Java_TestNG_IE2");
		ltOptions.put("project", "43491_Java_Selenium");
		ltOptions.put("name", "First Sample Test");
		ltOptions.put("console", "true");
		ltOptions.put("w3c", true);
		ltOptions.put("plugin", "java-testNG");
		browserOptions.setCapability("LT:Options", ltOptions);
		  try {
              String username="sayantan_bose";
			String accesskey="LT_AfV0RmY8g1UCNs9b526xWqoAihnvaOjCvqMXZlfkQNtIT4V";
			driver = new RemoteWebDriver(new URL("http://" + username + ":" + accesskey + "@hub.lambdatest.com/wd/hub"), browserOptions);
          } catch (MalformedURLException e) {
              System.out.println("Invalid grid URL");
          } catch (Exception e) {
              System.out.println(e.getMessage());
          }

	}

	@Test(priority = 0)
	public void testScenario_SimpleFromDemoTest() {
		// Step 1: Open Lambda Test's Playground
		driver.get("https://www.lambdatest.com/selenium-playground");
		// Step 2: Click "Simple Form Demo" under Input Forms
		WebElement simpleFormDemoLink = driver.findElement(By.linkText("Simple Form Demo"));
		simpleFormDemoLink.click();
		// Step 3: Validate that the URL contains "simple-form-demo"
		assertTrue(driver.getCurrentUrl().contains("simple-form-demo"));
		// Step 4: Create a variable for a string value
		String message = "Welcome to Lambda Test";
		// Step 5: Use this variable to enter values in the "Enter Message" text box
		WebElement messageInput = driver.findElement(By.id("user-message"));
		messageInput.sendKeys(message);
		// Step 6: Click "Get Checked Value"
		WebElement getCheckedValueButton = driver.findElement(By.id("showInput"));
		getCheckedValueButton.click();

		// Step 7: Validate whether the same text message is displayed
		WebElement outputMessage = driver.findElement(By.id("message"));
		String OutputMsg = outputMessage.getText();
		assertEquals(message, OutputMsg);
	}

	@Test(priority = 1)
	public void testScenario2_DragAndDropSliderTest() {
		// 1. Open the Selenium Playground page
		driver.get("https://www.lambdatest.com/selenium-playground");
		// 2. Click on "Drag & Drop Sliders" under "Progress Bars & Sliders"
		WebElement dragDropSlidersLink = driver.findElement(By.linkText("Drag & Drop Sliders"));
		dragDropSlidersLink.click();
		// 3. Find the slider with default value 15
		WebElement slider = driver.findElement(By.xpath("//input[@value='15']"));
		WebElement sliderValue = driver.findElement(By.id("rangeSuccess"));
		// 4. Drag the slider to 95
		Actions moveSlider = new Actions(driver);
		Action action = moveSlider.dragAndDropBy(slider, 215, 0).build();
		action.perform();
		// Step 5: Validate whether the range value shows 95
		Assert.assertEquals(sliderValue.getText(), "95", "Slider value is not updated to 95!");
		String rangeValue = sliderValue.getText();
		if (rangeValue.equals("95")) {
			System.out.println("Test Passed! Slider value is 95.");
		} else {
			System.out.println("Test Failed");

		}
	}

	@Test(priority = 2)
	public void testScenario3_InputFormSubmitTest() {
		// 1. Open the Selenium Playground page
		driver.get("https://www.lambdatest.com/selenium-playground");
		// 2. Click on "Input Form Submit" under "Input Forms"
		WebElement inputFormSubmitLink = driver.findElement(By.linkText("Input Form Submit"));
		inputFormSubmitLink.click();
		// 3. Click "Submit" without filling in any information in the form
		WebElement submitButton = driver.findElement(By.xpath("//button[text()=\"Submit\"]"));
		submitButton.click();
		// 4. Fill in the Name, Email, and other fields
		driver.findElement(By.name("name")).sendKeys("John Doe");
		driver.findElement(By.id("inputEmail4")).sendKeys("john.doe@example.com");
		driver.findElement(By.name("password")).sendKeys("1234567890");
		driver.findElement(By.name("company")).sendKeys("ABC");
		driver.findElement(By.id("inputAddress1")).sendKeys("123 Main St");
		driver.findElement(By.id("inputAddress2")).sendKeys("city post");
		driver.findElement(By.name("city")).sendKeys("New York");
		// 5. Select "United States" from the Country drop-down
		Select countryDropdown = new Select(driver.findElement(By.name("country")));
		countryDropdown.selectByVisibleText("United States");
		// 6. Fill remaining fields
		driver.findElement(By.id("inputState")).sendKeys("New York");
		driver.findElement(By.name("zip")).sendKeys("10001");
		driver.findElement(By.name("website")).sendKeys("http://example.com");
		// 7. Submit the form
		submitButton.click();
		// 8. Validate the success messages
		String successMsg = driver.findElement(By.xpath("//*[@class=\"success-msg hidden\"]")).getText();
		Assert.assertEquals(successMsg, "Thanks for contacting us, we will get back to you shortly.");
	}

	@AfterMethod

	public void tearDown() {
		driver.quit();
	}

}
