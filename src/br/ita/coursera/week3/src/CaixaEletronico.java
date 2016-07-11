package br.ita.coursera.week3.src;

import java.math.BigDecimal;

import br.ita.coursera.week3.src.dependencias.IHardware;
import br.ita.coursera.week3.src.dependencias.MockHardware;
import br.ita.coursera.week3.src.excecoes.ContaInvalidaServicoException;
import br.ita.coursera.week3.src.excecoes.HardwareException;
import br.ita.coursera.week3.src.excecoes.SaldoInsuficienteException;

/**
 * Classe responsável por efetuar operações do Caixa Eletrônico
 * 
 * @author JoaoDantas
 *
 */
public class CaixaEletronico {

	IHardware hardware;

	private String contaLogada;

	public CaixaEletronico(MockHardware mockHardware) {
		this.hardware = mockHardware;
	}

	/**
	 * Efetua login do cliente de acordo com seu cartao
	 * 
	 * @param conta
	 * @return
	 * @throws ContaInvalidaServicoException
	 * @throws HardwareException 
	 */
	public String logar(String numeroConta) throws ContaInvalidaServicoException, HardwareException {
		hardware.pegarNumeroDaContaCartao(numeroConta);
		setContaLogada(numeroConta);
		return "Usuário Autenticado";

	}

	/**
	 * Obtem o saldo da Conta Logada
	 * @return
	 * @throws ContaInvalidaServicoException 
	 * @throws HardwareException 
	 */
	public String saldo() throws ContaInvalidaServicoException, HardwareException {
		ContaCorrente contaCorrente = hardware.getServicoRemoto().recuperarConta(getContaLogada());
		BigDecimal saldo = contaCorrente.getSaldo();
		
		return "O saldo é R$" + saldo.toString();
	}
	

	public String sacar(BigDecimal valorAsacar) throws ContaInvalidaServicoException, SaldoInsuficienteException, HardwareException {
		ContaCorrente contaCorrente = hardware.getServicoRemoto().recuperarConta(getContaLogada());
		BigDecimal saldo = contaCorrente.getSaldo();
		if(saldo.compareTo(valorAsacar) > 0)
		{
			contaCorrente.setSaldo(saldo.subtract(valorAsacar));
			hardware.entregarDinheiro();
			hardware.getServicoRemoto().persistirConta(contaCorrente);
			return "Retire seu dinheiro";
		}
		else
		{
			throw new SaldoInsuficienteException("Saldo insuficiente");
		}
	}
	
	public String depositar(BigDecimal valorDeposito) throws ContaInvalidaServicoException, HardwareException {
		ContaCorrente contaCorrente = hardware.getServicoRemoto().recuperarConta(getContaLogada());
		BigDecimal saldo = contaCorrente.getSaldo();
		hardware.lerEnvelope();
		contaCorrente.setSaldo(saldo.add(valorDeposito));
		hardware.getServicoRemoto().persistirConta(contaCorrente);
		return "Depósito recebido com sucesso";
	}	
	
	public String getContaLogada() {
		return contaLogada;
	}

	public void setContaLogada(String contaLogada) {
		this.contaLogada = contaLogada;
	}

	

}
