package br.com.gisele.DigitalBank.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.gisele.DigitalBank.dto.PropostaDto;
import br.com.gisele.DigitalBank.dto.PropostaPassoDoisDto;
import br.com.gisele.DigitalBank.model.Proposta;
import br.com.gisele.DigitalBank.repository.PropostaRepository;
import br.com.gisele.DigitalBank.service.PropostaService;

@RestController
@RequestMapping("/proposta")
@Scope("request")
public class PropostaController extends BaseController{
	
	@Autowired
	private PropostaRepository propostaRepository;
	@Autowired
	private PropostaService propostaService;

	@PostMapping("/passoUm")
	public ResponseEntity<?> propostaPassoUm(@Valid @RequestBody PropostaDto propostaRequisicao, UriComponentsBuilder uriBuilder){
		Proposta proposta = new Proposta(propostaRequisicao.getNomeCliente(), propostaRequisicao.getSobrenomeCliente(), propostaRequisicao.getDataNascimento(), propostaRequisicao.getEmail(), propostaRequisicao.getCpf());
		Proposta propostaCpf = propostaService.buscaPropostaPorCpf(propostaRequisicao.getCpf());
		Proposta propostaEmail = propostaService.buscaPropostaPorEmail(propostaRequisicao.getEmail());
		
		if (propostaEmail != null) {
			this.adicionarErros("email", "O email já está cadastrado");
			return ResponseEntity.badRequest().body(this.getErros());
		}
		
		if(propostaCpf != null) {
			this.adicionarErros("cpf", "O cpf já está cadastrado");
			return ResponseEntity.badRequest().body(this.getErros());

		}
		propostaRepository.save(proposta);
		
		URI uri = uriBuilder.path("/proposta/{cpf}").buildAndExpand(proposta.getCpf()).toUri();
		
		return ResponseEntity.created(uri).body(proposta);
		
	}
	
	@PostMapping("/passoDois/{cpf}")
	public ResponseEntity<?> propostaPassoDois(@Valid @RequestBody PropostaPassoDoisDto propostaPassoDoisRequisicao, UriComponentsBuilder uriBuilder, @PathVariable String cpf){
		Proposta propostaClienteCpf = propostaService.buscaPropostaPorCpf(cpf);
		propostaClienteCpf.setCep(propostaPassoDoisRequisicao.getCep());
		propostaClienteCpf.setBairro(propostaPassoDoisRequisicao.getBairro());
		propostaClienteCpf.setCidade(propostaPassoDoisRequisicao.getCidade());
		propostaClienteCpf.setComplemento(propostaPassoDoisRequisicao.getComplemento());
		propostaClienteCpf.setRua(propostaPassoDoisRequisicao.getRua());
		propostaClienteCpf.setEstado(propostaPassoDoisRequisicao.getEstado());
		
		
		URI uri = uriBuilder.path("/proposta/{cpf}").buildAndExpand(propostaClienteCpf.getCpf()).toUri();
		propostaRepository.save(propostaClienteCpf);
		
		return ResponseEntity.created(uri).body(propostaClienteCpf);
	}
}
