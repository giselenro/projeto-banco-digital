package br.com.gisele.DigitalBank.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class PropostaPassoDoisDto {

	@NotNull @NotEmpty 
	@Pattern(regexp = "[0-9]{5}-[0-9]{3}", message = "Formato do CEP inválido!" )
	private String cep;
	@NotNull @NotEmpty
	private String rua;
	@NotNull @NotEmpty
	private String bairro;
	@NotNull @NotEmpty
	private String complemento;
	@NotNull @NotEmpty
	private String cidade;
	@NotNull @NotEmpty
	private String estado;
	
	public PropostaPassoDoisDto() {
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}
