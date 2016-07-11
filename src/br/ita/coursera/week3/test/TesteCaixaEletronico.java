/**
 * 
 */
package br.ita.coursera.week3.test;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import br.ita.coursera.week3.src.CaixaEletronico;
import br.ita.coursera.week3.src.ContaCorrente;
import br.ita.coursera.week3.src.dependencias.MockHardware;
import br.ita.coursera.week3.src.dependencias.MockServicoRemoto;
import br.ita.coursera.week3.src.excecoes.ContaInvalidaServicoException;
import br.ita.coursera.week3.src.excecoes.HardwareException;
import br.ita.coursera.week3.src.excecoes.SaldoInsuficienteException;

/**
 * @author JoaoDantas
 *
 */
public class TesteCaixaEletronico {

	@Before
	public void instanciaCaixaEServicos() {

	}

	@Test
	public void testeLoginUsuarioValido() throws ContaInvalidaServicoException, HardwareException {
		String contaSolicitada = "1";

		MockServicoRemoto servicoRemoto = new MockServicoRemoto();
		MockHardware mockHardware = new MockHardware(servicoRemoto);

		CaixaEletronico caixa = new CaixaEletronico(mockHardware);

		String retorno = caixa.logar(contaSolicitada);
		assertEquals("Usuário Autenticado", retorno);
		assertEquals(contaSolicitada, caixa.getContaLogada());
	}

	@Test(expected = ContaInvalidaServicoException.class)
	public void testeLoginUsuarioInvalido() throws ContaInvalidaServicoException, HardwareException {
		String contaSolicitada = "4";

		MockServicoRemoto servicoRemoto = new MockServicoRemoto();
		MockHardware mockHardware = new MockHardware(servicoRemoto);

		CaixaEletronico caixa = new CaixaEletronico(mockHardware);
		caixa.logar(contaSolicitada);
	}

	@Test
	public void testeVerificaSaldo() throws ContaInvalidaServicoException, HardwareException {
		String contaSolicitada = "1";
		MockServicoRemoto servicoRemoto = new MockServicoRemoto();
		MockHardware mockHardware = new MockHardware(servicoRemoto);

		CaixaEletronico caixa = new CaixaEletronico(mockHardware);

		caixa.logar(contaSolicitada);

		String retorno = caixa.saldo();
		
		assertEquals("O saldo é R$1000", retorno);
	}
	
	@Test
	public void testeSaqueComSucesso() throws ContaInvalidaServicoException, SaldoInsuficienteException, HardwareException {
		
		BigDecimal valorSacado = new BigDecimal(500);
		String contaSolicitada = "2";
		MockServicoRemoto servicoRemoto = new MockServicoRemoto();
		MockHardware mockHardware = new MockHardware(servicoRemoto);

		CaixaEletronico caixa = new CaixaEletronico(mockHardware);

		caixa.logar(contaSolicitada);

		String retorno = caixa.sacar(valorSacado);
		
		ContaCorrente contaCorrente = mockHardware.getServicoRemoto().recuperarConta(contaSolicitada);
		
		assertEquals("Retire seu dinheiro", retorno);
		assertEquals(new BigDecimal(1500), contaCorrente.getSaldo());
	}
	
	@Test(expected=SaldoInsuficienteException.class)
	public void testeSaqueSemSaldo() throws ContaInvalidaServicoException, SaldoInsuficienteException, HardwareException {
		String contaSolicitada = "2";
		MockServicoRemoto servicoRemoto = new MockServicoRemoto();
		MockHardware mockHardware = new MockHardware(servicoRemoto);

		CaixaEletronico caixa = new CaixaEletronico(mockHardware);

		caixa.logar(contaSolicitada);

		caixa.sacar(new BigDecimal(5000));
	}
	
	@Test
	public void testeDepositoComSucesso() throws ContaInvalidaServicoException, SaldoInsuficienteException, HardwareException {
		
		BigDecimal valorDeposito = new BigDecimal(500);
		String contaSolicitada = "3";
		MockServicoRemoto servicoRemoto = new MockServicoRemoto();
		MockHardware mockHardware = new MockHardware(servicoRemoto);

		CaixaEletronico caixa = new CaixaEletronico(mockHardware);

		caixa.logar(contaSolicitada);

		String retorno = caixa.depositar(valorDeposito);
		
		ContaCorrente contaCorrente = mockHardware.getServicoRemoto().recuperarConta(contaSolicitada);
		
		assertEquals("Depósito recebido com sucesso", retorno);
		assertEquals(new BigDecimal(3500), contaCorrente.getSaldo());
	}
	
	@Test(expected=HardwareException.class)
	public void testeDepositoForaDoAr() throws ContaInvalidaServicoException, SaldoInsuficienteException, HardwareException {
		
		BigDecimal valorDeposito = new BigDecimal(500);
		String contaSolicitada = "3";
		MockServicoRemoto servicoRemoto = new MockServicoRemoto();
		MockHardware mockHardware = new MockHardware(servicoRemoto);
		CaixaEletronico caixa = new CaixaEletronico(mockHardware);
		
		caixa.logar(contaSolicitada);
		
		mockHardware.setEstado(false);
		String retorno = caixa.depositar(valorDeposito);
	}
	
	@Test(expected=HardwareException.class)
	public void testeLoginForaDoAr() throws ContaInvalidaServicoException, HardwareException {
		String contaSolicitada = "1";

		MockServicoRemoto servicoRemoto = new MockServicoRemoto();
		MockHardware mockHardware = new MockHardware(servicoRemoto);

		CaixaEletronico caixa = new CaixaEletronico(mockHardware);

		mockHardware.setEstado(false);
		String retorno = caixa.logar(contaSolicitada);
		
	}
	
	@Test(expected=HardwareException.class)
	public void testeSaqueForaDoAr() throws ContaInvalidaServicoException, SaldoInsuficienteException, HardwareException {
		
		BigDecimal valorSacado = new BigDecimal(500);
		String contaSolicitada = "2";
		MockServicoRemoto servicoRemoto = new MockServicoRemoto();
		MockHardware mockHardware = new MockHardware(servicoRemoto);

		CaixaEletronico caixa = new CaixaEletronico(mockHardware);

		caixa.logar(contaSolicitada);

		mockHardware.setEstado(false);
		String retorno = caixa.sacar(valorSacado);
		
	}
}
