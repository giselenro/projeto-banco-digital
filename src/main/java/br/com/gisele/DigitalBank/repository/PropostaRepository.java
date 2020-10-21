package br.com.gisele.DigitalBank.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.gisele.DigitalBank.model.Proposta;

public interface PropostaRepository extends MongoRepository<Proposta, String>{

	public Proposta findByEmail(String email);
	public Proposta findByCpf(String cpf);
	
}
