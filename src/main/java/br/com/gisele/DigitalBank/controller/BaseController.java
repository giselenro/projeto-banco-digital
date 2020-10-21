package br.com.gisele.DigitalBank.controller;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseController {

	private Map<String,String> errors;

	public BaseController() {
		errors = new HashMap<String, String>();
	}
	
	public void adicionarErros(String nomeCampo, String mensagem) {
		errors.put(nomeCampo, mensagem);
	}
	
	public boolean temErros() {
		return !errors.isEmpty();
	}
	
	public Map<String, String> getErros(){
		return errors;
	}
	
}
