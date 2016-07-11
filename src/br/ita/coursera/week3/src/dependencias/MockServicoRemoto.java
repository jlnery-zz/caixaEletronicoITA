package br.ita.coursera.week3.src.dependencias;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.ita.coursera.week3.src.ContaCorrente;
import br.ita.coursera.week3.src.excecoes.ContaInvalidaServicoException;

public class MockServicoRemoto implements IServicoRemoto {

	private List<ContaCorrente> contasDoBanco;

	public MockServicoRemoto() {
		contasDoBanco = new ArrayList<ContaCorrente>();
		ContaCorrente conta1 = new ContaCorrente("1", new BigDecimal(1000));
		ContaCorrente conta2 = new ContaCorrente("2", new BigDecimal(2000));
		ContaCorrente conta3 = new ContaCorrente("3", new BigDecimal(3000));

		contasDoBanco.add(conta1);
		contasDoBanco.add(conta2);
		contasDoBanco.add(conta3);
	}

	@Override
	public ContaCorrente recuperarConta(String numeroConta) throws ContaInvalidaServicoException {
		for(ContaCorrente c : contasDoBanco)
		{
			if(c.getNumeroConta().equals(numeroConta)) return c;
		}
		throw new ContaInvalidaServicoException("Não há clientes com esta conta");
	}

	@Override
	public void persistirConta(ContaCorrente contaAtualizada) {
		for(ContaCorrente c : contasDoBanco)
		{
			if(c.getNumeroConta().equals(contaAtualizada.getNumeroConta())) c.setSaldo(contaAtualizada.getSaldo());
		}

	}

	public List<ContaCorrente> getContasDoBanco() {
		return contasDoBanco;
	}

	public void setContasDoBanco(List<ContaCorrente> contasDoBanco) {
		this.contasDoBanco = contasDoBanco;
	}

}
