package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class TestBase {

	public static WebDriver driver; //**static- because we created object of test base in extent report utility class to achieve screenshot function.
	public Logger logger; // log4j2
	public Properties properties;

	
	@BeforeClass(groups= {"Sanity","Regression","Master"})
	@Parameters({ "OS", "Browser" })
	public void setup(String os, String br) throws IOException, URISyntaxException {
		
		//properties file
		FileReader file = new FileReader("./src//test//resources//config.properties");
		properties = new Properties();
		properties.load(file);

		logger = LogManager.getLogger(this.getClass());//log4j2
		
		if(properties.getProperty("execution_env").equalsIgnoreCase("remote")) {
			
			logger.info("Using remote execution process..");
			
			DesiredCapabilities capabilities = new DesiredCapabilities();
			//OS remote
			if(os.equalsIgnoreCase("windows")) {
				capabilities.setPlatform(Platform.WIN10);
				logger.info("On windows..");
			}else if (os.equalsIgnoreCase("mac")) {
				capabilities.setPlatform(Platform.MAC);
				logger.info("On MAC..");
			}else if (os.equalsIgnoreCase("linux")) {
				capabilities.setPlatform(Platform.LINUX);
				logger.info("On linux-grid..");
			}
			else {
				
				System.out.println("No OS matching found..");
				logger.info("Invalied OS");
				return;
			}
			
			//browser remote
			if(br.equalsIgnoreCase("chrome")) {
				capabilities.setBrowserName("chrome");
				logger.info("in Chrome");
			}else if (br.equalsIgnoreCase("firefox")) {
				capabilities.setBrowserName("firefox");
				logger.info("in Firefox");
			}else if (br.equalsIgnoreCase("edge")) {
				capabilities.setBrowserName("MicrosoftEdge");
				logger.info("in Edge");
			}else {
				System.out.println("No browser matching found..");
				logger.info("Invalied browser");
				return;
			}
			
			//driver remote
			driver = new RemoteWebDriver(new URI("http://localhost:4444/wd/hub").toURL(), capabilities);
			//java.net.URI(string).toURL()--internet solution
			
		}
		
		if(properties.getProperty("execution_env").equalsIgnoreCase("local")) {
			
			logger.info("Using local execution process..");
				
			switch (br.toLowerCase()) {
			case "chrome":
				driver = new ChromeDriver();
				logger.info("Chrome browser launched..");
				break;

			case "firefox":
				driver = new FirefoxDriver();
				logger.info("Firefox browser launched..");
				break;

			case "edge":
				driver = new EdgeDriver();
				logger.info("Edge browser launched..");

				break;

			default:
				System.out.println("Invalied browser name");
				logger.info("Invalied browser name");

				return;

			}
		}
		
		
	
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(properties.getProperty("appURL"));//reading from properties file
		driver.manage().window().maximize();
	}

	@AfterClass(groups= {"Sanity","Regression","Master"})
	public void tearDown() {
		driver.quit();
	}

	public String randomString() {
		return RandomStringUtils.randomAlphabetic(5);
	}
	
	public String captureScreen(String tName) throws IOException {
		
		String timestamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takeScreenshot = (TakesScreenshot) driver;
		File scrFile = takeScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath = System.getProperty("user.dir")+"\\screenshots\\"+ tName + "_" + timestamp+".png";
		File targetFile = new File(targetFilePath);
		
		scrFile.renameTo(targetFile);
		
		return targetFilePath;
	}

}
