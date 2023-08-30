package WebSites;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FaceBook_Site {

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");

		System.setProperty("webdriver.chrome.driver",
				"C:\\Program Files\\Google\\Chrome\\Application\\chromedriver-win64\\chromedriver.exe");

		ChromeDriver driver = new ChromeDriver(options);
		driver.get("https://www.facebook.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));

		driver.findElement(By.id("email")).sendKeys("gomathi@gmail.com");

		driver.findElement(By.id("pass")).sendKeys("09876543");

//		driver.findElement(By.name("login")).click();

		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		driver.findElement(By.name("firstname")).sendKeys("Gomathi");
		driver.findElement(By.name("lastname")).sendKeys("Siddaiyan");
		driver.findElement(By.name("reg_email__")).sendKeys("9876543210");

		driver.findElement(By.id("password_step_input")).sendKeys("098765");

		WebElement dayDropDown = driver.findElement(By.id("day"));
		Select chooseDay = new Select(dayDropDown);
		chooseDay.selectByValue("29");

		WebElement monthDropDown = driver.findElement(By.id("month"));
		Select chooseMonth = new Select(monthDropDown);
		chooseMonth.selectByValue("5");

		WebElement yearDropDown = driver.findElement(By.id("year"));
		Select chooseYear = new Select(yearDropDown);
		chooseYear.selectByValue("1997");

		driver.findElement(By.name("sex")).click();

		System.out.println("Filled all details in FACEBOOK LOGIN PAGE");

	}

}
