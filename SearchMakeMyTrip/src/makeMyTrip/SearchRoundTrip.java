package makeMyTrip;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchRoundTrip {
	public static By customXpath(String xpath, String param) {
		String rawPath = xpath.replaceAll("%replace%", param);
		return By.xpath(rawPath);
	}
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\ssyed7\\eclipse-workspace\\SearchMakeMyTrip\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        // Launch the URL
        driver.get("https://www.makemytrip.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        

        try {
        	Thread.sleep(5000);
			driver.manage().window().maximize();
	        WebDriverWait wait = new WebDriverWait(driver, 10);
//	        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[starts-with(@title, 'notification-frame')]")));
//	        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[@class='wewidgeticon we_close']"))).click();
//	        driver.findElement(By.xpath("//*[@id=\"webklipper-publisher-widget-container-notification-close-div\"]")).click();
//	        
			Thread.sleep(10000);
//			WebElement flightsMenu = driver.findElement(By.xpath("//a[contains(@href,'www.makemytrip.com/flights')]/span[contains(@class,'chFlights')]"));
//			flightsMenu.click();
//			

	        WebElement flightsTab = driver.findElement(By.xpath("//span[contains(text(),'Flights')]"));
	        flightsTab.click();
	        //selecting roundtrip
	        WebElement roundTripOption = driver.findElement(By.xpath("//li[contains(text(),'Round Trip')]"));
	        roundTripOption.click();
	        //selecting from city
	        WebElement fromCityDrop = driver.findElement(By.xpath("//input[@id='fromCity']"));
	        fromCityDrop.click();
	        driver.findElement(By.xpath("//input[@placeholder='From']")).sendKeys("HYD");
	        Thread.sleep(5000);
	        //handling new window
	        ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
	        driver.switchTo().window(tabs.get(1));
	        driver.close();
	        driver.switchTo().window(tabs.get(0));
	        driver.findElement(By.xpath("//li[@class='react-autosuggest__suggestion react-autosuggest__suggestion--first']")).click();
	        //selecting to city
	        Thread.sleep(1000);
	        WebElement toCity = driver.findElement(By.xpath("//input[@id='toCity']"));
	        toCity.click();
	        driver.findElement(By.xpath("//input[@placeholder='To']")).sendKeys("BLR");
	        Thread.sleep(3000);
	        //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@class='react-autosuggest__suggestion react-autosuggest__suggestion--first']"))).click();
	        driver.findElement(By.xpath("//li[@class='react-autosuggest__suggestion react-autosuggest__suggestion--first']")).click();
	        //From date
	        driver.findElement(By.xpath("//div[contains(@class,'dateFiled')][1]")).click();
	        String modifydate = "//div[@aria-label='%replace%' and @aria-disabled='false']";
	        Calendar cal=Calendar.getInstance();
	        cal.add(Calendar.DATE, 6);
	        String[] dateArr=cal.getTime().toString().split(" ");
	        driver.findElement(customXpath(modifydate,dateArr[0]+" "+dateArr[1]+" "+dateArr[2]+" "+dateArr[5])).click();
	        //To Date
	        driver.findElement(By.xpath("//div[contains(@class,'dateFiled')][2]")).click();
	        modifydate = "//div[@aria-label='%replace%' and @aria-disabled='false']";
	        cal.add(Calendar.DATE, 2);
	        String[] dateArrTwo=cal.getTime().toString().split(" ");
	        driver.findElement(customXpath(modifydate,dateArrTwo[0]+" "+dateArrTwo[1]+" "+dateArrTwo[2]+" "+dateArrTwo[5])).click();
	        //search
	        driver.findElement(By.xpath("//a[contains(@class,'widgetSearchBtn') and text()='Search']")).click();
	        Thread.sleep(20000);
	        if (!driver.getTitle().contains("MakeMyTrip")) {
	        	driver.navigate().refresh();
	        	Thread.sleep(10000);
	        }	        
	        //handling pop up
	        driver.findElement(By.xpath("//button[contains(text(),'OKAY, GOT IT!')]")).click();
            // Verify the Search page is displayed
	        WebElement searchPageHeader = driver.findElement(By.cssSelector("p[class='font24 blackFont whiteText appendBottom20 journey-title makeFlex spaceBetween bottom']"));
	        String resultpage = searchPageHeader.getText();
            if (resultpage.contains("Flights from Hyderabad to Bengaluru, and back")) {
                System.out.println("Search page is displayed as expected.");
            } else {
                System.out.println("Search page is NOT displayed.");
            }	
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}