package com.testcases;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.base.TestBase;
import com.pages.MoviePage;

public class MoviePageTest extends TestBase 
{
	public MoviePageTest() throws IOException 
	{
		
		super();
	}
	
	@BeforeMethod
	public void setup() throws IOException
	{
		initialization();
		
	}
	@Test(priority=1)
	public void moviedetailsvalidate() throws IOException 
	{
		MoviePage.ValidateDate();
		//MoviePage.ValidateOrigin();
	}
	
	@AfterMethod
	public void closebrowser() 
	{
		driver.quit();
	}
}
