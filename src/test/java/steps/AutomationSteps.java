package steps;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CompraPage;
import pages.VestidoCasualPage;

public class AutomationSteps {

	WebDriver driver;
	VestidoCasualPage vestidoCasualPage;
	CompraPage compraPage;
	Random random = new Random();
	int number = 0;

	@Before
	public void before() {
		// Download do chrome driver https://chromedriver.chromium.org/downloads
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		compraPage = new CompraPage(driver);
		vestidoCasualPage = new VestidoCasualPage(driver);
	}

	@After
	public void after() {
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	@Given("que estou na pagina de vestidos casuais")
	public void que_estou_na_pagina_de_vestidos_casuais() throws InterruptedException {
		System.out.println("Dado que estou na pagina de vestidos casuais");
		driver.get("http://automationpractice.com/index.php?id_category=9&controller=category");
		vestidoCasualPage.irParaCard();
	}

	@When("adiciono alguns vestidos")
	public void compro_alguns_vestidos() throws InterruptedException {
		System.out.println("Quando adiciono alguns vestidos");
		number = random.nextInt(7) + 1;
		vestidoCasualPage.adicionarVestidos(number);
	}

	@Then("valido o valor do carrinho")
	public void valido_o_valor_do_carrinho() throws InterruptedException {
		System.out.println("Então valido o valor do carrinho");
		vestidoCasualPage.validarCarrinho(number);
	}

	@When("clico no vestido")
	public void clico_no_vestido() throws InterruptedException {
		System.out.println("Quando clico no vestido");
		vestidoCasualPage.clicarEmVestido();
	}

	@Then("valido a tela de mais informacoes")
	public void valido_a_tela_de_mais_informacoes() throws InterruptedException {
		System.out.println("Então valido a tela de mais informacoes");
		vestidoCasualPage.validarTelaMaisInformacoes();
	}

	@Then("valido a compra do vestido com email {string} e senha {string}")
	public void valido_a_compra_do_vestido_com_email_e_senha(String email, String senha) throws InterruptedException {
		System.out.println("Então valido a compra do vestido com email "+email+" e senha "+senha+"");
		vestidoCasualPage.clicarEmProcedimento();
		compraPage.concluirSumario();
		compraPage.concluirLogin(email,senha);
		compraPage.concluirEndereco();
		compraPage.concluirEnvio();
		compraPage.concluirPagamento();
		compraPage.validarMensagemConcluido();
	}
}
