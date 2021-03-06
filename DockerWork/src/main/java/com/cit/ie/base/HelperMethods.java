package com.cit.ie.base;



import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.cit.ie.rest.RESTClient;

public class HelperMethods extends WebDriverManager {

	public WebDriver driver;
	protected int timeOut = 500;
	public WebDriverWait wait;
	private String baseURL="https://10.73.28.71:8443/univmax/restapi/sloprovisioning/symmetrix/0001967XXXXX/storagegroup/";

	//@SuppressWarnings("static-access")
	public HelperMethods(WebDriver adriver){
		driver=adriver;
		//wait = new WebDriverWait(this.driver,timeOut);
		new WebDriverWait(driver, timeOut).until((ExpectedCondition<Boolean>) wd ->
		((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
	}

	public void waitForElementVisiblity(String xpath) throws InterruptedException{
		wait=new WebDriverWait(driver, 120, 4000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		//System.out.println("Element is visible");
		Thread.sleep(8000);
	}

	public void waitForElementClickability(String xpath) throws InterruptedException{
		wait=new WebDriverWait(driver, 120, 4000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
		//System.out.println("Element is clickable");
		Thread.sleep(8000);
	}


	public void waitForLoad() {
		new WebDriverWait(driver, 120).until((ExpectedCondition<Boolean>) wd ->
		((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
	}

	public void waitForElementToDisappear(String text) throws InterruptedException {
		
			wait=new WebDriverWait(driver,800,4000);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(text)));
			Thread.sleep(3500);
	}

	public void haltTest() throws InterruptedException{
		driver.close();
		Thread.sleep(1000);
	}

	public static boolean assertElementNotPresent (WebDriver driver, String text) throws Exception {
		List<WebElement> els = driver.findElements(By.xpath(text));
		if (els.isEmpty() == false) {
			throw new Exception (text + " (element is present)");
		}
		//System.out.println("Element is not present");
		return true;
	}
	
	public static void printTimeStart(String test){
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		System.out.println("*************************************************");
		System.out.println(test +" starting at : " +timeStamp);
		System.out.println("*************************************************");
	}
	
	public static void printTimeFinish(String test){
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		System.out.println("*************************************************");
		System.out.println(test +" has finished at : " +timeStamp);
		System.out.println("*************************************************");
	}
	
	/**
	 * @author gearyk2
	 * @param sgName
	 * @throws InterruptedException
	 * @description verify the response code of the RESTGET for this storage group
	 * and then call a REST DELETE for the storage group
	 */
	public void verifyAndCleanup(String sgName) throws InterruptedException {
		//VERIFY THAT GROUP HAS BEEN CREATED
		//RESTClient.refreshRestDB(baseURL);
		RESTClient.GET(baseURL+sgName);
		Assert.assertEquals(RESTClient.responseStatus,200);
		//CLEANUP
		//RESTClient.DELETE(baseURL+sgName);
		RESTClient.DELETE(baseURL+sgName);
		    
	}
	
	public void getScreenshot(){
		
	}
	


}

