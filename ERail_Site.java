package WebSites;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ERail_Site {

	public static void main(String[] args) throws InterruptedException {

//		Pseudocode:
//			  
//			1. Set the system property and Launch the URL
//			2. Click the 'sort on date' checkbox
//			3. clear the existing text from station text field
//			4. type "ms"in the from station text field
//			5. tab in the from station text field
//			6. clear the existing text in the to station text field
//			7. type "mdu" in the to station text field
//			8. tab in the to station text field
//			9. Add a java sleep for 2 seconds
//			10. Store all the train names in a list
//			11. Get the size of list
//			12. Add the list into a new Set
//			13. Get the size of set
//			14. Compare the Size of list and Set to verify the names are unique
//
//			Verify train names are unique in https://erail.in/
//			Note: locator ->  //table[@class='DataTable TrainList TrainListHeader']

//		Pseudocode:
//		  
//		1. Set the system property and Launch the URL
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		

		System.setProperty("webdriver.chrome.driver",
				"C:\\Program Files\\Google\\Chrome\\Application\\chromedriver-win64\\chromedriver.exe");

		ChromeDriver driver = new ChromeDriver(options);
		driver.get("https://erail.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));

//		2. Click the 'sort on date' checkbox
		Thread.sleep(2000);
		driver.findElement(By.id("chkSelectDateOnly")).click();
		Thread.sleep(2000);

//		3. clear the existing text from station text field
//		4. type "ms"in the from station text field
		WebElement fromStation = driver.findElement(By.id("txtStationFrom"));
		fromStation.clear();
		fromStation.sendKeys("ms");
		fromStation.sendKeys(Keys.ENTER);

//		5. tab in the from station text field
//		driver.findElement(By.xpath("//div[@id='AutocompleteContainter_1691408397106']//strong")).click();

//		6. clear the existing text in the to station text field
//		7. type "mdu" in the to station text field
//		8. tab in the to station text field
		WebElement toStation = driver.findElement(By.id("txtStationTo"));
		toStation.clear();
		toStation.sendKeys("mdu");
		toStation.sendKeys(Keys.ENTER);

//		9. Add a java sleep for 2 seconds
		Thread.sleep(2000);

//		10. Store all the train names in a list

		List<WebElement> noOfRows = driver.findElements(
				By.xpath("//table[@class='DataTable TrainList TrainListHeader stickyTrainListHeader']//tr"));
		int rowCount = noOfRows.size();
		System.out.println("No.of Rows in Train Name: " + rowCount);

		List<String> trainRows = new ArrayList<String>();

		for (int i = 2; i < rowCount; i++) {

			String trainName = driver.findElement(By.xpath(
					"//table[@class='DataTable TrainList TrainListHeader stickyTrainListHeader']//tr[" + i + "]/td[2]"))
					.getText();
			trainRows.add(trainName);
			System.out.println((i - 1) + ") " + trainName);
		}

//		11. Get the size of list
		int listSize= trainRows.size();
		System.out.println("List size: "+listSize);

//		12. Add the list into a new Set
		Set<String> trainRowsInSet= new LinkedHashSet<String>(trainRows);
		
//		13. Get the size of set
		int setSize= trainRowsInSet.size();
		System.out.println("Set Size: "+setSize);
		
//		14. Compare the Size of list and Set to verify the names are unique
		if(listSize==setSize)
		{
			System.out.println("List and Set - size is SAME");
		}
		else
		{
			System.out.println("List and Set - Size is NOT SAME");
		}

//		Verify train names are unique in https://erail.in/
//		Note: locator ->  //table[@class='DataTable TrainList TrainListHeader']

	}

}
