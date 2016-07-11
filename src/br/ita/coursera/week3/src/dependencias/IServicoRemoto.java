package br.ita.coursera.week3.src.dependencias;

import br.ita.coursera.week3.src.ContaCorrente;
import br.ita.coursera.week3.src.excecoes.ContaInvalidaServicoException;

public interface IServicoRemoto {

	/**
	 * 
	 * Recupera uma conta baseada no seu n�mero
	 * 
	 * @param numeroConta
	 * @return ContaCorrente c
	 * @throws ContaInvalidaServicoException
	 */
	public ContaCorrente recuperarConta(String numeroConta) throws ContaInvalidaServicoException;

	/**
	 * Grava altera��es na conta, como uma mudan�a no saldo devido a um saque ou
	 * dep�sito. Deve ser chamado APENAS no caso de ser feito algum saque ou
	 * dep�sito com sucesso.
	 * 
	 * @param contaCorrente
	 */
	public void persistirConta(ContaCorrente contaCorrente);

}
