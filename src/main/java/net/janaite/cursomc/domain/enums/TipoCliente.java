package net.janaite.cursomc.domain.enums;

import java.util.Arrays;

public enum TipoCliente {
	PESSOAFISICA(1, "Pessoa Física"), PESSOAJURIDICA(2, "Pessoa Jurídica");

	private int cod;
	private String descricao;

	private TipoCliente(int cod, String desc) {
		this.cod = cod;
		this.descricao = desc;
	}

	public int getCod() {
		return this.cod;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public static TipoCliente toEnum(Integer id) {
		if (id == null) {
			return null;
		}

		return Arrays.stream(TipoCliente.values()).filter(e -> e.cod == id.intValue()).findFirst()
				.orElseThrow(() -> new IllegalArgumentException(String.format("Invalid Id: %d", id)));
	}
}
