package com.pages;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.base.TestBase;
import com.util.TestUtil;

public class MoviePage extends TestBase 
{
	static String ExpectedReleasedate = "17 December 2021";
	static String Expectedcountryorigin = "India";
	

public MoviePage() throws IOException 
	{
		super();
	}
	public static void ValidateDate() throws IOException
	{
		TestUtil.geturl("wiki_url");
		WebElement wifisearchinput = driver.findElement(By.xpath("//input[@id='searchInput']"));
		wifisearchinput.sendKeys("Pushpa: The Rise");
		wifisearchinput.sendKeys(Keys.ENTER);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,600)");
		WebElement wikireleasedate = driver.findElement(By.xpath("//*[@id='mw-content-text']/div[1]/table[1]/tbody/tr[12]/td/div/ul/li"));
		String wikiRD = wikireleasedate.getText();
		Assert.assertEquals(ExpectedReleasedate,wikiRD);
		
		TestUtil.geturl("imbd_url");
		WebElement imbdsearchinput= driver.findElement(By.xpath("//input[@placeholder='Search IMDb']"));
		imbdsearchinput.sendKeys("Pushpa: The Rise - Part 1");
		imbdsearchinput.sendKeys(Keys.ENTER);
		driver.findElement(By.linkText("Pushpa: The Rise - Part 1")).click();
		WebElement imdbreleasedatebtn= driver.findElement(By.xpath("//a[contains(text(),'Release date')]"));
		imdbreleasedatebtn.click();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		WebElement imbdmoviedate= driver.findElement(By.xpath("//a[contains(text(),'India')]//ancestor::td//following-sibling::td[1]"));
		String imdbRD= imbdmoviedate.getText();
		Assert.assertEquals(ExpectedReleasedate,imdbRD);
		Assert.assertEquals(imdbRD,wikiRD);
		System.out.println("Wikipedia release date = "+wikiRD +" is equal to imdb release date = "+imdbRD);
	}
	public static void Validateorigin() throws IOException 
	{
		TestUtil.geturl("wiki_url");
		WebElement wifisearchinput = driver.findElement(By.xpath("//input[@id='searchInput']"));
		wifisearchinput.sendKeys("Pushpa: The Rise");
		wifisearchinput.sendKeys(Keys.ENTER);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,600)");
		WebElement wifiorigin= driver.findElement(By.xpath("//th[contains(text(),'Country')]//following-sibling::td"));
		String wikior = wifiorigin.getText();
		System.out.println("wifi origin : "+wikior);
		Assert.assertEquals(Expectedcountryorigin,wikior);
		
		TestUtil.geturl("imbd_url");
		WebElement imbdsearchinput= driver.findElement(By.xpath("//input[@placeholder='Search IMDb']"));
		imbdsearchinput.sendKeys("Pushpa: The Rise - Part 1");
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		imbdsearchinput.sendKeys(Keys.ENTER);
		driver.findElement(By.linkText("Pushpa: The Rise - Part 1")).click();
		WebElement imdborigin= driver.findElement(By.xpath("//span[contains(text(),'Country of origin')]//ancestor::li/div/ul/li/a"));
		String imdbor =imdborigin.getText();
		Assert.assertEquals(Expectedcountryorigin,imdbor);
		Assert.assertEquals(wikior,imdbor);	
		System.out.println("Wikipedia country origin ="+wikior +"is equal to country origin ="+imdbor);
		
		
	}
}
