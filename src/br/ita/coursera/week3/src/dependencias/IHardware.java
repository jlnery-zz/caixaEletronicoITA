package br.ita.coursera.week3.src.dependencias;

import br.ita.coursera.week3.src.excecoes.ContaInvalidaServicoException;
import br.ita.coursera.week3.src.excecoes.HardwareException;

/**
 * Classe responsável pelo Hardware implementado no Caixa Eletrônico
 * 
 * Deve ser comunicar com o servicoRemoto
 * 
 * @author JoaoDantas
 *
 */
public interface IHardware {
	
	public String pegarNumeroDaContaCartao(String numeroConta) throws ContaInvalidaServicoException, HardwareException;
	
	public void entregarDinheiro() throws HardwareException;
	
	public void lerEnvelope() throws HardwareException;
	
	public IServicoRemoto getServicoRemoto() throws HardwareException;
	
	public void setServicoRemoto(IServicoRemoto servicoRemoto) throws HardwareException;
}
