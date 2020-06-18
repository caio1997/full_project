package com.myhome.full_project.entities.enums;

public enum TipoCliente {
	
	PESSOAFISICA(1, "Pessoa Física"),
	PESSOAJURIDICA(2, "Pessoa Jurídaca");

	private Integer cod;
	private String pessoa;
	
	private TipoCliente(int cod, String pessoa) {
		this.cod = cod;
		this.pessoa = pessoa;
	}

	public int getCod() {
		return cod;
	}

	public String getPessoa() {
		return pessoa;
	}

	public static TipoCliente toEnum(Integer cod) {
		
		if(cod == null) {
			return null;
		}
		
		for(TipoCliente x : TipoCliente.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: "+cod);
		
	}
	

}
