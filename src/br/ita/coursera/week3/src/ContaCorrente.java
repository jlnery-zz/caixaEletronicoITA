package br.ita.coursera.week3.src;

import java.math.BigDecimal;

public class ContaCorrente {

	private String numeroConta;

	private BigDecimal saldo;

	public ContaCorrente(String numeroConta, BigDecimal saldo) {
		this.numeroConta = numeroConta;
		this.saldo = saldo;
	}

	public String getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

}
