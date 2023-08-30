package WebSites;

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
import io.github.bonigarcia.wdm.WebDriverManager;

public class Amazon_Site {

	public static void main(String[] args) throws IOException, InterruptedException {

//		Pseudocode:
//			Amazon:
//			1.Load the URL https://www.amazon.in/
//			2.search as oneplus 9 pro 
//			3.Get the price of the first product
//			4. Print the number of customer ratings for the first displayed product
//			5. click on the stars 
//			6. Get the percentage of ratings for the 5 star.
//			7. Click the first text link of the first image
//			8. Take a screen shot of the product displayed
//			9. Click 'Add to Cart' button
//			10. Get the cart subtotal and verify if it is correct.

		// Pseudocode: Amazon:
//		   1.Load the URL https://www.amazon.in/ 

		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");

		System.setProperty("webdriver.chrome.driver",
				"C:\\Program Files\\Google\\Chrome\\Application\\chromedriver-win64\\chromedriver.exe");

		ChromeDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("https://www.amazon.in/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));

//		 * 2.search as oneplus 9 pro 
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("OnePlus 9 pro Mobile");
//		click on search button
		driver.findElement(By.id("nav-search-submit-button")).click();

//		 * 3.Get the price of the first product 		
		String firstProdPrice = driver.findElement(By.className("a-price-whole")).getText();
		System.out.println("First Product Price: " + firstProdPrice);

		String firstProductOnlydigits = firstProdPrice.replaceAll("\\D", "");
		System.out.println("First Product without any commas: " + firstProductOnlydigits);

//		 * 4. Print the number of customer ratings for the first displayed product 
		String customerRating = driver.findElement(By.xpath("//span[@class='a-size-base s-underline-text']")).getText();
		System.out.println("First Product customer Rating: " + customerRating);

//		 * 5. click on the stars 

		driver.findElement(By.xpath("//i[@class='a-icon a-icon-popover']")).click();

//		 * 6. Get the percentage of ratings for the 5 star. 
		String startPercentage = driver.findElement(By.xpath("(//span[@class='a-size-base']/a)[2]")).getText();
		System.out.println("First Product Percentage of Ratings: " + startPercentage);

//		 * 7. Click the first text link of the first image 
		driver.findElement(By.xpath("//h2[@class='a-size-mini a-spacing-none a-color-base s-line-clamp-2']")).click();

//		 * 8. Take a screen shot of the product displayed 
		Set<String> windowHandles = driver.getWindowHandles();
		int windowSize = windowHandles.size();
		System.out.println("No.Of Windows: " + windowSize);
		List<String> allWindow = new ArrayList<String>(windowHandles);
		String secondWindow = allWindow.get(1);
		driver.switchTo().window(secondWindow);
		System.out.println("Switched to second Window");

		Thread.sleep(4000);
		driver.findElement(By.className("imgTagWrapper")).click();
		Set<String> windowHandles2 = driver.getWindowHandles();
		int windowSize2 = windowHandles2.size();
		System.out.println("No.Of Windows: " + windowSize2);
		List<String> allWindow2 = new ArrayList<String>(windowHandles2);
		String thirdWindow = allWindow2.get(1);
		driver.switchTo().window(thirdWindow);
		System.out.println("Switched to Third Window");

		Thread.sleep(2000);
		WebElement productImage = driver.findElement(By.id("ivLargeImage"));
		File source = productImage.getScreenshotAs(OutputType.FILE);
		File destination = new File("Snaps/Snap.png");
		FileUtils.copyFile(source, destination);
		System.out.println("Screenshot has been taken");

//		 * 9. Click 'Add to Cart' button 
		driver.findElement(By.xpath("(//i[@class='a-icon a-icon-close'])")).click();
		driver.switchTo().window(secondWindow);
		driver.findElement(By.id("add-to-cart-button")).click();

		Thread.sleep(2000);
//		 * 10. Get the cart subtotal and verify if it is correct.  

		Set<String> windowHandles3 = driver.getWindowHandles();
		int windowSize3 = windowHandles3.size();
		System.out.println("No.Of Windows: " + windowSize3);
		List<String> allWindow3 = new ArrayList<String>(windowHandles3);
		String ForthWindow = allWindow3.get(1);
		driver.switchTo().window(ForthWindow);
		System.out.println("Switched to Forth Window");

		Thread.sleep(2000);
		WebElement addToCart = driver.findElement(By.id("attach-accessory-cart-subtotal"));
		String cartSubtotal = addToCart.getText();
		System.out.println("Cart Subtotal: " + cartSubtotal);
//		int onlyRupees= (int) cartSubtotal;
		String onlyDigit = cartSubtotal.replaceAll("\\D", "");
		int Length = onlyDigit.length();
		String onlyRupees = onlyDigit.substring(0, (Length - 2));

//		String onlyRupees= onlyDigit.substring("5");
		System.out.println("Printing only Rupees Value: " + onlyRupees);

		if (onlyRupees.equals(firstProductOnlydigits)) {
			System.out.println("YES the product is Added in Cart and price is SAME");
		} else {
			System.out.println("The product is NOT Added in Cart and price is DIFFERENT");
		}
	}

}
