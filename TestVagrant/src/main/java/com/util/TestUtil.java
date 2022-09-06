package com.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.base.TestBase;

public class TestUtil extends TestBase
{

	public TestUtil() throws IOException {
	}


	public static long PAGE_LOAD_TIMEOUT =20;
	public static long IMPLICIT_WAIT = 10;
	public static String url = "";
	public static HttpURLConnection huc = null;
	public static  int respCode = 200;
	
	
	public static void takeScreenshotAtEndOfTest() throws IOException
	{
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis()  + ".png"));
		
	}
	public static void geturl(String url) throws IOException
	{
		prop =new Properties(); 
		String path =System.getProperty("user.dir");
		System.out.println(path); 
		FileInputStream ip= new FileInputStream(path+"\\src\\main\\java\\com\\config\\config.properties");
		prop.load(ip); 
		driver.get(prop.getProperty(url));
	}
	
	public static void checkbrokenlink(String urls)
	{
       
		try
        {
            huc = (HttpURLConnection)(new URL(url).openConnection());
            
            huc.setRequestMethod("HEAD");
            
            huc.connect();
            
            respCode = huc.getResponseCode();
            
            if(respCode >= 400){
                System.out.println(url+" is a broken link");
            }
            else{
                System.out.println(url+" is a valid link");
            }
                
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        }
	}
