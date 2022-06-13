package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CompraPage {
	
	public CompraPage(WebDriver driver) {
		this.driver = driver;
		actions = new Actions(driver);
		
	}
		
	//Declaração dos elementos
    WebDriver driver;
    Actions actions;
		
	//metodos
	public void concluirSumario() throws InterruptedException {
		actions.moveToElement(driver.findElement(By.xpath("//a[@class='button btn btn-default standard-checkout button-medium']")));
		actions.perform();
		driver.findElement(By.xpath("//a[@class='button btn btn-default standard-checkout button-medium']")).click();	
	}

	public void concluirLogin(String email, String senha) throws InterruptedException {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@id='passwd']")).sendKeys(senha);
		driver.findElement(By.xpath("//button[@class='button btn btn-default button-medium']")).click();
	}

	public void concluirEndereco() throws InterruptedException {
		clicarEmProceder();
		Thread.sleep(2000);
		actions.moveToElement(driver.findElement(By.xpath("//button[@class='button btn btn-default standard-checkout button-medium']")));
		actions.perform();
		driver.findElement(By.xpath("//button[@class='button btn btn-default standard-checkout button-medium']")).click();
	}

	public void concluirEnvio() throws InterruptedException {
		Thread.sleep(1000);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@class='checkbox']"))); 
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
		Thread.sleep(10000);
		
		System.out.print("CLICOU NO CHECK");
		//driver.findElement(By.xpath("//a[@title='Close']")).click();
		//actions.moveToElement(driver.findElement(By.xpath("//label[text()='I agree to the terms of service and will adhere to them unconditionally.']"))).click().build().perform();
		clicarEmProceder();
	}

	public void concluirPagamento() throws InterruptedException {
		driver.findElement(By.xpath("//a[@title='Pay by bank wire']")).click();
		clicarEmProceder();
	}
	
	public void clicarEmProceder() throws InterruptedException {
		Thread.sleep(2000);
		actions.moveToElement(driver.findElement(By.xpath("//button[@class='button btn btn-default button-medium']")));
		actions.perform();
		driver.findElement(By.xpath("//button[@class='button btn btn-default button-medium']")).click();
	}
	
	public void validarMensagemConcluido() {
		Assert.assertEquals("validar mensagem concluido", 
				"Your order on My Store is complete.",
				driver.findElement(By.xpath("//strong")).getText());
	}
}
