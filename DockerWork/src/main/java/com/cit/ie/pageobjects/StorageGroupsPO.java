package com.cit.ie.pageobjects;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cit.ie.base.Constants;

public class StorageGroupsPO extends HomeDashboardPO {
	private WebDriver driver;
	public StorageGroupsPO(WebDriver wdriver) {
		super(wdriver);
		driver = wdriver;
		wait = new WebDriverWait(driver, timeOut);
		PageFactory.initElements(driver, this);
	}

	//LOCATORS
	//BUTTONS
	public final String STORAGE_GROUPS_PAGE_TITLE_XPATH = "//u4v-breadcrumbs/div/div[text()='Storage Groups']";
	public final String CREATE_STORAGE_GROUP_BUTTON_XPATH = "//span[text()='Provision']/parent::button/preceding-sibling::button[@aria-label='create']";
	public final String EDIT_STORAGE_GROUP_BUTTON_XPATH=".//button[@aria-label='edit']";
	public final String DELETE_STORAGE_GROUP_BUTTON_XPATH="//button/span[text()='Delete']";
	public final String MOREACTIONS_STORAGE_GROUP_BUTTON_XPATH=".//button/md-icon[text()='more_vert']";
	public final String SEARCH_STORAGE_GROUP_BUTTON_XPATH=".//button/md-icon[text()='search']";
	public final String FILTER_STORAGE_GROUP_BUTTON_XPATH=".//button/md-icon[text()='filter_list']";
	public final String VIEWDETAILS_STORAGE_GROUP_BUTTON_XPATH=".//button[@aria-label='view details']";
	//COLUMN HEADERS
	public final String STORAGEGROUP_HEADER_XPATH=".//span[text()='Storage Group']";
	public final String COMPLIANCE_HEADER_XPATH=".//span[text()='Compliance']";
	public final String SRP_HEADER_XPATH=".//span[text()='SRP']";
	public final String SLO_HEADER_XPATH=".//span[text()='SLO']";
	public final String CAPACITY_HEADER_XPATH=".//span[text()='Capacity (GB)']";
	public final String EMULATION_HEADER_XPATH=".//span[text()='Emulation']";
	public final String COLUMN_FILTER_HEADER_XPATH=".//i[@aria-label='Grid Menu']";
	//SELECT ROW
	public final String ROW_WITG_SG_NAME="//div[text()='']/child::div";
	public final String ROW_WITG_SG_NAME_COMPLIANCE="//div[text()='']/child::div";
	//MOREACTIONS
	public final String CHANGE_SRP_XPATH="//button/span[text()='Change SRP']";
	public final String SET_IO_HOST_LIMITS_XPATH="//button/span[text()='Set Host I/O Limits']";
	//POPUPS
	public final String DELETE_STORAGE_GROUP_POPUP_OK_BUTTON_XPATH="//button[@aria-label='OK Button']";
	public final String SUCCESS_STORAGE_GROUP_DELETED_XPATH="//label[text()='Success']";
	public final String ACKNOWLEDGE_SUCCESS_STORAGE_GROUP_DELETED_XPATH="//button/span[text()='Close']";
	public final String TASK_IN_PROCESS_XPATH="//label[text()='Task in process...']";
	//WEB ELEMENTS
	//BUTTONS/ICONS
	@FindBy(xpath=STORAGE_GROUPS_PAGE_TITLE_XPATH)
	public WebElement storageGroupPageTitle;
	@FindBy(xpath=CREATE_STORAGE_GROUP_BUTTON_XPATH)
	public WebElement createStorageGroupButton;
	@FindBy(xpath=EDIT_STORAGE_GROUP_BUTTON_XPATH)
	public WebElement editStorageGroupButton;
	@FindBy(xpath=DELETE_STORAGE_GROUP_BUTTON_XPATH)
	public WebElement deleteStorageGroupButton;
	@FindBy(xpath=MOREACTIONS_STORAGE_GROUP_BUTTON_XPATH)
	public WebElement moreActionsStorageGroupButton;
	@FindBy(xpath=SEARCH_STORAGE_GROUP_BUTTON_XPATH)
	public WebElement searchStorageGroupButton;
	@FindBy(xpath=FILTER_STORAGE_GROUP_BUTTON_XPATH)
	public WebElement filterStorageGroupButton;
	@FindBy(xpath=VIEWDETAILS_STORAGE_GROUP_BUTTON_XPATH)
	public WebElement viewDetailsStorageGroupButton;
	@FindBy(xpath=CHANGE_SRP_XPATH)
	public WebElement changeSRPButton;
	@FindBy(xpath=SET_IO_HOST_LIMITS_XPATH)
	public WebElement setIOLimitsButton;
	//HEADERS
	@FindBy(xpath=STORAGEGROUP_HEADER_XPATH)
	public WebElement storageGroupHeader;
	@FindBy(xpath=COMPLIANCE_HEADER_XPATH)
	public WebElement complianceHeader;
	@FindBy(xpath=SRP_HEADER_XPATH)
	public WebElement srpHeader;
	@FindBy(xpath=SLO_HEADER_XPATH)
	public WebElement sloHeader;
	@FindBy(xpath=CAPACITY_HEADER_XPATH)
	public WebElement capacityHeader;
	@FindBy(xpath=EMULATION_HEADER_XPATH)
	public WebElement emulationHeader;
	@FindBy(xpath=COLUMN_FILTER_HEADER_XPATH)
	public WebElement columnFilterButton;
	@FindBy(xpath=DELETE_STORAGE_GROUP_POPUP_OK_BUTTON_XPATH)
	public WebElement deleteSGPopupOKButton;
	@FindBy(xpath=SUCCESS_STORAGE_GROUP_DELETED_XPATH)
	public WebElement successStorageGroupDeleted;
	@FindBy(xpath=ACKNOWLEDGE_SUCCESS_STORAGE_GROUP_DELETED_XPATH)
	public WebElement acknowledgeSGDeletedButton;
	@FindBy(xpath=TASK_IN_PROCESS_XPATH)
	public WebElement taskInProgressIcon;


	public WebElement sgRow(String sgname){
		return findByXPath(ROW_WITG_SG_NAME,sgname);	
	}

	public WebElement sgRowCompliance(String sgname){
		return findByXPath(ROW_WITG_SG_NAME_COMPLIANCE,sgname);	
	}

	/**
	 * @author gearyk2
	 * @param xpath
	 * @return web element
	 * @description specialized findByXpath for clicking on Rows
	 */
	public WebElement findByXPath(String xpath,String sgname){
		WebElement element = driver.findElement(By.xpath(xpath.replace("//div[text()='","//div[text()='"+sgname)));
		return element;
	}
	
	public String getElementXPath(WebDriver driver, WebElement element) {
	    return (String)((JavascriptExecutor)driver).executeScript("gPt=function(c){if(c.id!==''){return'id(\"'+c.id+'\")'}if(c===document.body){return c.tagName}var a=0;var e=c.parentNode.childNodes;for(var b=0;b<e.length;b++){var d=e[b];if(d===c){return gPt(c.parentNode)+'/'+c.tagName+'['+(a+1)+']'}if(d.nodeType===1&&d.tagName===c.tagName){a++}}};return gPt(arguments[0]).toLowerCase();", element);
	}

	//Wait for this page to load
	public void waitForStorageGroupsPageObjects() throws InterruptedException{
		try {
			waitForElementToDisappear(Constants.PAGE_LOADING);
			waitForElementClickability(CREATE_STORAGE_GROUP_BUTTON_XPATH);
			waitForLoad();
			int count=0;
			if (driver.findElements( By.xpath("//div[@modal-animation='true' and @window-class='loading-window']") ).size() != 0)
			{	
				count++;
				System.out.println("PAGE LOADING MODAL" +count);	
				
				System.out.println("modal element size "+driver.findElements( By.xpath("//div[@modal-animation='true' and @window-class='loading-window']") ).size());
				
				List<WebElement> allLinks = driver.findElements(By.xpath("//div[@modal-animation='true' and @window-class='loading-window']"));
				Iterator<WebElement> itr = allLinks.iterator();	
				while(itr.hasNext()) {
					WebElement w=itr.next();
					System.out.println("location and size "+w.getRect());
					Thread.sleep(1000);
					System.out.println(getElementXPath(driver,w));
					Thread.sleep(1000);
					System.out.println("location "+w.getLocation());
					Thread.sleep(1000);
					System.out.println("getText "+w.getText());
					Thread.sleep(1000);
					System.out.println("isDisplayed "+w.isDisplayed());
					Thread.sleep(1000);
					System.out.println("isEnabled "+w.isEnabled());
					Thread.sleep(1000);
					File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
					//Retrieve width of element.
					int ImageWidth = w.getSize().getWidth();
					//Retrieve height of element.
					int ImageHeight = w.getSize().getHeight();  

					//Used selenium Point class to get x y coordinates of Image element.
					//get location(x y coordinates) of the element.
					Point point = w.getLocation();
					int xcord = point.getX();
					int ycord = point.getY();

					//Reading full image screenshot.
					BufferedImage img = ImageIO.read(scrFile);

					//cut Image using height, width and x y coordinates parameters.
					BufferedImage dest = img.getSubimage(xcord, ycord, ImageWidth, ImageHeight);
					ImageIO.write(dest, "png", scrFile);
					//Used FileUtils class of apache.commons.io.

					FileUtils.copyFile(scrFile, new File("c:\\screenshot\\screenshot_MODDAL"+count+".png"));
				}
				waitForElementToDisappear("//div[@modal-animation='true' and @window-class='loading-window']");
				System.out.println("PAGE LOADING MODAL GONE");
			}
			
			
			Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
		}
	}


}
