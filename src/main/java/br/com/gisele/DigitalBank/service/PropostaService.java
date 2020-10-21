package br.com.gisele.DigitalBank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gisele.DigitalBank.model.Proposta;
import br.com.gisele.DigitalBank.repository.PropostaRepository;

@Service
public class PropostaService {
	
	@Autowired
	private PropostaRepository propostaRepository;

	public Proposta buscaPropostaPorEmail(String email) {
		
		return propostaRepository.findByEmail(email);
	}
	
	public Proposta buscaPropostaPorCpf(String cpf) {
		
		return propostaRepository.findByCpf(cpf);
	}
}
