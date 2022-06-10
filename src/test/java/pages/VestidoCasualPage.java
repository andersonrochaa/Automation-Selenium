package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class VestidoCasualPage {

    WebDriver driver;
    Actions actions;
	
	public VestidoCasualPage(WebDriver driver) {
		this.driver = driver;
		actions = new Actions(driver);
		
	}
	
	//Declaração dos elementos
	WebElement card;
	WebElement closeCard;
	WebElement butonAdd;
		
	//metodos
	
	public void irParaCard() throws InterruptedException {
		card = driver.findElement(By.xpath("//div[@class='product-container']"));
		Thread.sleep(1000);
		actions.moveToElement(card);
		actions.perform();
		Thread.sleep(1000);
	}

	public void adicionarVestidos(int number) throws InterruptedException {
		butonAdd = driver.findElement(By.xpath("//a[@title='Add to cart']"));
		closeCard  = driver.findElement(By.xpath("//span[@title='Close window']"));
		for(int i = 0; i < number; i++) {
			butonAdd.click();
			closeCard.click();
			Thread.sleep(200);
		}	
	}

	public void validarCarrinho(int number) throws InterruptedException {
		WebElement carrinho = driver.findElement(By.xpath("//a[@title='View my shopping cart']"));
		actions.moveToElement(carrinho);
		carrinho.click();
		Thread.sleep(3000);
		actions.moveToElement(driver.findElement(By.id("total_price")));
		System.out.println("$"+((number*26)+2)+".00" +"|"+number);
		System.out.println(driver.findElement(By.id("total_price")).getText());
		Assert.assertEquals("Adicionei "+number+" vestidos no carrinho:", "$"+((number*26)+2)+".00", driver.findElement(By.id("total_price")).getText());
		//actions.perform();
		
	}
	
	
}
