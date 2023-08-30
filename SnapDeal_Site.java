package WebSites;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SnapDeal_Site {

	public static void main(String[] args) throws AWTException, InterruptedException {

//		Pseudocode:
//			1. Load https://www.snapdeal.com/
//			2. Mouse hover on Men's Fashion and Click Shirts
//			3. Mouse hover on the first product and Click on Quick View
//			4. Close all the browsers

//		Pseudocode:
//		1. Load https://www.snapdeal.com/
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		
		System.setProperty("webdriver.chrome.driver",
				"C:\\Program Files\\Google\\Chrome\\Application\\chromedriver-win64\\chromedriver.exe");

		ChromeDriver driver = new ChromeDriver(options);
		driver.get(" 	https://www.snapdeal.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));

		WebElement notification = driver.findElement(By.id("pushDenied"));
		Actions builder = new Actions(driver);
		builder.moveToElement(notification).click().perform();
		System.out.println("Clicked on Not now button");

//		2. Mouse hover on Men's Fashion and Click Shirts
		driver.findElement(By.xpath("//span[@class='catText']")).click();
		WebElement searchProduct = driver.findElement(By.xpath("//span[text()='Shirts']"));
		builder.moveToElement(searchProduct).click().perform();
		System.out.println("Clicked on Men's Fashion and Shirts Category");

//		3. Mouse hover on the first product and Click on Quick View
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_PAGE_DOWN);
		robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//div[@class='clearfix row-disc']/div)")).click();
		System.out.println("Clicked on Quick View for the first Product list");

		Thread.sleep(2000);
		// Price of the Product
		String priceOfProduct = driver.findElement(By.className("payBlkBig")).getText();
		System.out.println("*Price of the first Product: *" + priceOfProduct);
		String firstProductOnlyDigits = priceOfProduct.replaceAll("\\D", "");
		System.out.println("First Product - Only digits: " + firstProductOnlyDigits);

		// Ratings of the Product
		String ratings = driver.findElement(By.className("rating-count")).getText();
		System.out.println("Rating for the Product: " + ratings);

		// Ratings count of the Product
		String ratingsCount = driver.findElement(By.className("product-rating-count")).getText();
		System.out.println("Ratings Count for the product: " + ratingsCount);

		// Click on view Details
		robot.keyPress(KeyEvent.VK_PAGE_DOWN);
		robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
		driver.findElement(By.xpath("//div[@class='product-attr']/following-sibling::a")).click();
		System.out.println("View Details button is clicked");

		// click on ADD TO CART
		robot.keyPress(KeyEvent.VK_PAGE_DOWN);
		robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
		driver.findElement(By.className("intialtext")).click();

		// Get the Price you want to pay
		String totalPrice = driver.findElement(By.className("you-pay")).getText();
		String onlyDigits = totalPrice.replaceAll("\\D", "");
		System.out.println("*Total price - in digits: *" + onlyDigits);

		if (firstProductOnlyDigits.equals(onlyDigits)) {
			System.out.println("YES, Product has Added to Cart");
		} else {
			System.out.println("NO, Product has NOT added to cart");
		}

//		4. Close all the browsers

	}

}
