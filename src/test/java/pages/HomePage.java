package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class HomePage {
	
	WebDriver driver; 
	
	final String CAMPO_BUSCA = "//input";
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void realizaBusca(String produto) {
		
		driver.findElement(By.xpath(CAMPO_BUSCA)).sendKeys(produto);
		driver.findElement(By.xpath(CAMPO_BUSCA)).sendKeys(Keys.ENTER);
		

	}
	

}