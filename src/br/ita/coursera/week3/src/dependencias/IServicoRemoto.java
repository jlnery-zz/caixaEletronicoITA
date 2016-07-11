package br.ita.coursera.week3.src.dependencias;

import br.ita.coursera.week3.src.ContaCorrente;
import br.ita.coursera.week3.src.excecoes.ContaInvalidaServicoException;

public interface IServicoRemoto {

	/**
	 * 
	 * Recupera uma conta baseada no seu número
	 * 
	 * @param numeroConta
	 * @return ContaCorrente c
	 * @throws ContaInvalidaServicoException
	 */
	public ContaCorrente recuperarConta(String numeroConta) throws ContaInvalidaServicoException;

	/**
	 * Grava alterações na conta, como uma mudança no saldo devido a um saque ou
	 * depósito. Deve ser chamado APENAS no caso de ser feito algum saque ou
	 * depósito com sucesso.
	 * 
	 * @param contaCorrente
	 */
	public void persistirConta(ContaCorrente contaCorrente);

}
