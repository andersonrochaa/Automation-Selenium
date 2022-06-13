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
			Thread.sleep(500);
			closeCard.click();
			Thread.sleep(500);
		}	
	}

	public void validarCarrinho(int number) throws InterruptedException {
		WebElement carrinho = driver.findElement(By.xpath("//a[@title='View my shopping cart']"));
		actions.moveToElement(carrinho);
		carrinho.click();
		Thread.sleep(3000);
		actions.moveToElement(driver.findElement(By.id("total_price")));
		actions.perform();
		Assert.assertEquals("Adicionei "+number+" vestidos no carrinho:", "$"+((number*26)+2)+".00", driver.findElement(By.id("total_price")).getText());
	}

	public void clicarEmVestido() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(By.xpath("//img[@title='Printed Dress']")).click();
	}

	public void validarTelaMaisInformacoes() throws InterruptedException {
		Thread.sleep(2000);
		Assert.assertEquals("Validar texto do parágrafo na página mais informções",
				"100% cotton double printed dress. Black and white striped top and orange high waisted skater skirt bottom.",
				driver.findElement(By.xpath("//div[@id='short_description_content']/p")).getText());
	}

	public void clicarEmProcedimento() throws InterruptedException {
		driver.findElement(By.xpath("//a[@title='Add to cart']")).click();
		driver.findElement(By.xpath("//a[@title='Proceed to checkout']")).click();
		Thread.sleep(3000);
	}	
}
