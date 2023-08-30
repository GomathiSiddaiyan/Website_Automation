package WebSites;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Flipkart_Site {

	public static void main(String[] args) throws IOException, InterruptedException, AWTException {

		
//		Pseudocode:
//		Amazon:
//		1.Load the URL https://www.amazon.in/
//		2.search as oneplus 9 pro 
//		3.Get the price of the first product
//		4. Print the number of customer ratings for the first displayed product
//		5. click on the stars 
//		6. Get the percentage of ratings for the 5 star.
//		7. Click the first text link of the first image
//		8. Take a screen shot of the product displayed
//		9. Click 'Add to Cart' button
//		10. Get the cart subtotal and verify if it is correct.

		
		
//		Pseudocode:
//		Amazon:
//		1.Load the URL https://www.flipkart.com/
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		
		System.setProperty("webdriver.chrome.driver",
				"C:\\Program Files\\Google\\Chrome\\Application\\chromedriver-win64\\chromedriver.exe");

		ChromeDriver driver = new ChromeDriver(options);
		driver.get("https://www.flipkart.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));

		// close the popup window - login with Mobile number
		driver.findElement(By.xpath("//button[@class='_2KpZ6l _2doB4z']")).click();

//		2.search as oneplus 9 pro 		
		driver.findElement(By.name("q")).sendKeys("OnePlus 9 pro Mobile");
		driver.findElement(By.className("L0Z3Pu")).click();

//			3.Get the price of the first product
		String firstProduct = driver.findElement(By.xpath("//div[@class='_4rR01T']")).getText();
		System.out.println("First Product Name: " + firstProduct);

		String firstProductPrice = driver.findElement(By.xpath("//div[@class='_30jeq3 _1_WHN1']/parent::div"))
				.getText();
		System.out.println("First Product Price: " + firstProductPrice);
		String firstProdPriceOnlyInteger = firstProductPrice.replaceAll("\\D", "");
		System.out.println("First Product Price - only digits: " + firstProdPriceOnlyInteger);

//			4. Print the number of customer ratings for the first displayed product
		String customerRating = driver.findElement(By.xpath("(//span[@class='_2_R_DZ']//span)[1]")).getText();
		System.out.println("Customer Rating: " + customerRating);

//			5. click on the stars 
		Actions builder = new Actions(driver);
		WebElement starMouseHover = driver.findElement(By.className("_1wB99o"));
		builder.moveToElement(starMouseHover).perform();
		String startPercentage = driver.findElement(By.className("_1uJVNT")).getText();

//			6. Get the percentage of ratings for the 5 star.
		System.out.println("Percentage of 5 star ratings: " + startPercentage);

//			7. Click the first text link of the first image
		driver.findElement(By.className("_4rR01T")).click();

//			8. Take a screen shot of the product displayed
		Set<String> windowHandling = driver.getWindowHandles();
		int windowSize = windowHandling.size();
		System.out.println("No.of Windows: " + windowSize);

		List<String> allWindows = new ArrayList<String>(windowHandling);
		String secondWindow = allWindows.get(1);
		driver.switchTo().window(secondWindow);
		String pageTitle = driver.getTitle();
		System.out.println("Second Page Title: " + pageTitle);

		Thread.sleep(3000);
		WebElement firstProductImage = driver.findElement(By.xpath("//img[@loading='eager']"));
		File source = firstProductImage.getScreenshotAs(OutputType.FILE);
		File destination = new File("Snaps/snap2.png");
		FileUtils.copyFile(source, destination);

//			9. Click 'Add to Cart' button
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_PAGE_DOWN);
		robot.keyRelease(KeyEvent.VK_PAGE_DOWN);

		driver.findElement(By.xpath("//button[@class='_2KpZ6l _2U9uOA _3v1-ww']")).click();

//			10. Get the cart subtotal and verify if it is correct.

		String verified = driver.findElement(By.xpath("//span[@class='_1YQFQF']")).getText();
		System.out.println("Item price: " + verified);

		String onlyDigits = verified.replaceAll("\\D", "");
		int length = onlyDigits.length();
		String onlyRupees = onlyDigits.substring(0, (length));
		System.out.println("Item Prie only ruppes: " + onlyRupees);

		if (onlyRupees.equals(firstProdPriceOnlyInteger)) {
			String totalPrice = driver.findElement(By.xpath("//div[@class='z4Ha90']")).getText();
			System.out.println("After adding deliver charge: " + totalPrice);
			System.out.println("YES, Product is added to cart");
			
		} 
		else {
			System.out.println("NO, Product is NOT added to cart ");
		}

	}

}
