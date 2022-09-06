package com.base;



import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.util.TestUtil;
import com.util.WebEventlistener;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop;
	public static EventFiringWebDriver e_driver;
	public static WebEventlistener eventListener;
	
	public TestBase() throws IOException
	{
		 prop = new Properties();
		 String path = System.getProperty("user.dir");
		 FileInputStream ip = new FileInputStream(path+"\\src\\main\\java\\com\\config\\config.properties");
		 prop.load(ip);	
	}
	
	public static void initialization() throws IOException
	{
		String browserName = prop.getProperty("browser");
		if(browserName.equals("chrome"))
		{
			String path = System.getProperty("user.dir");
			System.setProperty("webdriver.chrome.driver",path+"\\Drivers\\chromedriver_103.0.5060.134.exe");
			driver= new ChromeDriver();
		}
		else if(browserName.equals("firefox"))
		{
			String path = System.getProperty("user.dir");
			System.setProperty("webdriver.gecko.driver",path+"\\Drivers\\geckodriver.exe");
			driver= new FirefoxDriver();
		}
		
		e_driver = new EventFiringWebDriver(driver);
		eventListener = new WebEventlistener();
		e_driver.register(eventListener);
		driver = e_driver;
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
	}

}
