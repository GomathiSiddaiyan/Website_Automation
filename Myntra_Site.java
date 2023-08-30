package WebSites;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Myntra_Site {

	public static void main(String[] args) throws InterruptedException {
//		Pseudocode:
//		1) Open https://www.myntra.com/
//			2) Mouse hover on MeN 
//			3) Click Jackets 
//			4) Find the total count of item 
//			5) Validate the sum of categories count matches
//			6) Check jackets
//			7) Click + More option under BRAND
//			8) Type Duke and click checkbox
//			9) Close the pop-up x
//			10) Confirm all the Coats are of brand Duke
//			    Hint : use List 
//			11) Sort by Better Discount
//			12) Find the price of first displayed item
//			 13) Click on the first product	
//			14) Click on WishList Now
//			14) Close Browser(driver.close())

//		Pseudocode:
//			1) Open https://www.myntra.com/
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		options.addArguments("--disable-notifications");

		System.setProperty("webdriver.chrome.driver",
				"C:\\Program Files\\Google\\Chrome\\Application\\chromedriver-win64\\chromedriver.exe");

		ChromeDriver driver = new ChromeDriver(options);
		driver.get("https://www.myntra.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));

//				2) Mouse hover on MeN 
		Actions builder = new Actions(driver);
		WebElement men = driver.findElement(By.className("desktop-main"));
		builder.moveToElement(men).perform();
		System.out.println("Moved to MEN");

//				3) Click Jackets 

		WebElement product = driver.findElement(By.linkText("Jackets"));
		builder.moveToElement(product).click().perform();
		System.out.println("Clicked on Jackets");

//				4) Find the total count of item 
		String totalCount = driver.findElement(By.className("title-count")).getText();
		System.out.println("Total count of the product: " + totalCount);
		String count = totalCount.replaceAll("\\D", "");
		int countDigit = Integer.parseInt(count);
		System.out.println("Count Digit- Only Digit: " + countDigit);

//				5) Validate the sum of categories count matches
		String firstCategoryCount = driver.findElement(By.className("categories-num")).getText();
		System.out.println("Product 1st category count: " + firstCategoryCount);
		String firstCategoryDigit = firstCategoryCount.replaceAll("\\D", "");
		System.out.println("1st Category- Only Digit: " + firstCategoryDigit);

		String secondCategoryCount = driver.findElement(By.xpath("(//span[@class='categories-num'])[2]")).getText();
		System.out.println("Product 2nd category count: " + secondCategoryCount);
		String secondCategoryDigit = secondCategoryCount.replaceAll("\\D", "");
		System.out.println("1st Category- Only Digit: " + secondCategoryDigit);

		int sumOfCategories = Integer.parseInt(firstCategoryDigit) + Integer.parseInt(secondCategoryDigit);
		System.out.println("Sum of Categories: " + sumOfCategories);
		if (countDigit == sumOfCategories) {
			System.out.println("Sum of Categories count is MATCHING");
		} else {
			System.out.println("Sum of Categories count is NOT MATCHING");
		}

//				6) Check jackets
		driver.findElement(By.xpath("(//div[@class='common-checkboxIndicator'])[1]")).click();

//				7) Click + More option under BRAND
		driver.findElement(By.className("brand-more")).click();
		Thread.sleep(2000);

//				8) Type Duke and click checkbox
		driver.findElement(By.className("FilterDirectory-searchInput")).sendKeys("Duke");
		driver.findElement(By.xpath("(//label[@class=' common-customCheckbox']/div)[1]")).click();
		Thread.sleep(2000);

//				9) Close the pop-up x
		driver.findElement(By.xpath("//div[@class='FilterDirectory-titleBar']/span")).click();
//		  Hint : use List 
//			11) Sort by Better Discount

		driver.findElement(By.xpath("(//div[@class='sort-sortBy']/span)[2]")).click();
		driver.findElement(By.xpath("//input[@value='discount']/parent::label")).click();

//		12) Find the price of first displayed item
		String firstDiscountProduct = driver.findElement(By.className("product-discountedPrice")).getText();
		System.out.println("First product - discount price: " + firstDiscountProduct);

//		 13) Click on the first product
		WebElement firstProduct = driver.findElement(By.className("product-productMetaInfo"));
		firstProduct.click();

//		14) Click on WishList Now
		Set<String> windowHandles = driver.getWindowHandles();
		int windowSize = windowHandles.size();
		System.out.println("No. of Windows: " + windowSize);

		List<String> allWindows = new ArrayList<String>(windowHandles);
		String secondWindow = allWindows.get(1);
		driver.switchTo().window(secondWindow);
		System.out.println("Second Window: " + secondWindow);
		String title = driver.getTitle();
		System.out.println("Second Page Title: " + title);

		driver.findElement(By.xpath("//span[text()='WISHLIST']/preceding-sibling::span")).click();
		System.out.println("Clicked on Wishlist");

		// 14) Close Browser
//		driver.quit();
	}

}
