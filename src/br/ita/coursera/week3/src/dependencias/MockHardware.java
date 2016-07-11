package br.ita.coursera.week3.src.dependencias;

import br.ita.coursera.week3.src.ContaCorrente;
import br.ita.coursera.week3.src.excecoes.ContaInvalidaServicoException;
import br.ita.coursera.week3.src.excecoes.HardwareException;

/**
 * Classe respon
 * 
 * @author JoaoDantas
 *
 */
public class MockHardware implements IHardware {

	public IServicoRemoto servicoRemoto;
	
	public Boolean estado;
	
	public MockHardware(MockServicoRemoto servicoRemoto) {
		this.servicoRemoto = servicoRemoto;
		this.estado = true;
	}

	@Override
	public String pegarNumeroDaContaCartao(String numeroConta) throws ContaInvalidaServicoException, HardwareException {
		if(!getEstado()) throw new HardwareException("Nâo foi possível obter o número da conta. Sistema fora do ar");
		ContaCorrente conta = servicoRemoto.recuperarConta(numeroConta);
		return conta.getNumeroConta();
	}

	@Override
	public void entregarDinheiro() throws HardwareException {
		if(!getEstado()) throw new HardwareException("Nâo foi possível entregar o dinheiro. Sistema fora do ar");
	}

	@Override
	public void lerEnvelope() throws HardwareException {
		if(!getEstado()) throw new HardwareException("Nâo foi possível ler o envelope. Sistema fora do ar");
	}

	public void setServicoRemoto(IServicoRemoto servicoRemoto) {
		this.servicoRemoto = servicoRemoto;
	}
	
	public IServicoRemoto getServicoRemoto() {
		return servicoRemoto;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	
}
