package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager; //Log4j
import org.apache.logging.log4j.Logger; //Log4j
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass_Test {
	
	// Loading config.properties files
	public Properties property;

	public static WebDriver driver;
	public Logger logger; // Log4jr
	public WebDriverWait myWait;

	
	@Parameters({ "os", "browser"})
	@BeforeClass(groups = { "Sanity", "Regression", "Master" })
	public void setup(String os, String browser) throws IOException {
		//System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
		FileReader file = new FileReader(".//src//test//resources//config.properties");
		property = new Properties();
		property.load(file);

		logger = LogManager.getLogger(this.getClass());
		DesiredCapabilities des_cap = new DesiredCapabilities();
		// OS
		if (property.getProperty("execution_env").equalsIgnoreCase("remote")) {

			// des_cap.setPlatform(os);
			// des_cap.setBrowserName(browser);
			if (os.equalsIgnoreCase("windows")) {
				des_cap.setPlatform(Platform.WIN11);
			} else if (os.equalsIgnoreCase("mac")) {
				des_cap.setPlatform(Platform.MAC);
			}else if(os.equalsIgnoreCase("linux")){
				des_cap.setPlatform(Platform.LINUX);
			}
			else {
				System.out.println("OS Not Found.");
				return;
			}
			switch (browser) {
			case "chrome":
				des_cap.setBrowserName("chrome");
				break;
			case "edge":
				des_cap.setBrowserName("MicrosoftEdge");
				break;
			case "firefox":
				des_cap.setBrowserName("firefox");
				break;
			case "safari":
				des_cap.setBrowserName("safari");
				break;
			default:
				System.out.println("---- Invalid Browser ----");
				return;
			}
			/*
			 * ChromeOptions options = new ChromeOptions();
			 * options.addArguments("--no-sandbox");
			 * options.addArguments("--disable-dev-shm-usage");			 * 
			 * WebDriver
			  driver = new RemoteWebDriver(new URL("http://localhost:4444"), options);*/

			driver = new RemoteWebDriver(new URL(" http://192.168.1.185:4444/wd/hub"),des_cap);
		}

		if (property.getProperty("execution_env").equalsIgnoreCase("local")) {
			// Browser

			switch (browser.toLowerCase()) {
			case "chrome":
				driver = new ChromeDriver();
				break;
			case "edge":
				driver = new EdgeDriver();
				break;
			case "firefox":
				driver = new FirefoxDriver();
				break;
			default:
				System.out.println("Invalid Browser Name ...");
				return;
			}
		

		}

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		myWait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.get(property.getProperty("appURL")); // reading URL from config.properties file
		driver.manage().window().maximize();
	}

	@AfterClass(groups = { "Sanity", "Regression", "Master" })
	public void tearDown() {
		driver.quit();
	}

	public String randomString() {
		@SuppressWarnings("deprecation")
		String random_string = RandomStringUtils.randomAlphabetic(5);
		return random_string;
	}

	public String randomNumber() {
		@SuppressWarnings("deprecation")
		String random_number = RandomStringUtils.randomNumeric(10);
		return random_number;
	}

	public String randomAlphaNumeric() {
		@SuppressWarnings("deprecation")
		String ran_string = RandomStringUtils.randomAlphanumeric(3);
		@SuppressWarnings("deprecation")
		String ran_num = RandomStringUtils.randomNumeric(3);
		return (ran_string + "@#" + ran_num);

	}

	public String captureScreen(String tname) throws IOException {
		// is executed at the runtime when a test method fails
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

		TakesScreenshot takes_screenshot = (TakesScreenshot) driver;
		File source_file = takes_screenshot.getScreenshotAs(OutputType.FILE);

		String target_filePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File target_file = new File(target_filePath);

		source_file.renameTo(target_file);

		return target_filePath;
	}
}
