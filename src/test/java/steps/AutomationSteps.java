package steps;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import pages.VestidoCasualPage;

public class AutomationSteps {

	WebDriver driver;
	HomePage homePage;
	VestidoCasualPage vestidoCasualPage;
	Random random = new Random();
	int number = 0;
	
	@Before
	public void before() {
		// Download do chrome driver https://chromedriver.chromium.org/downloads
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@After
	public void after() {
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	
	@Given("que estou na pagina de vestidos casuais")
	public void que_estou_na_pagina_de_vestidos_casuais() throws InterruptedException {
		driver.get("http://automationpractice.com/index.php?id_category=9&controller=category");
		vestidoCasualPage = new VestidoCasualPage(driver);
		vestidoCasualPage.irParaCard();
	}

	@When("adiciono alguns vestidos")
	public void compro_alguns_vestidos() throws InterruptedException {
		number = random.nextInt(7) + 1;
		vestidoCasualPage.adicionarVestidos(number);
	}

	@Then("valido o valor do carrinho")
	public void valido_o_valor_do_carrinho() throws InterruptedException {
		vestidoCasualPage.validarCarrinho(number);
	}

}
