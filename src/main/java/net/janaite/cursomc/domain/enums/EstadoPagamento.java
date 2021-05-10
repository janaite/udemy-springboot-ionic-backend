package net.janaite.cursomc.domain.enums;

import java.util.Arrays;

public enum EstadoPagamento {
	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");
	
	private int cod;
	private String descricao;

	private EstadoPagamento(int cod, String desc) {
		this.cod = cod;
		this.descricao = desc;
	}

	public int getCod() {
		return this.cod;
	}

	public String getDescricao() {
		return this.descricao;
	}
	
	public static EstadoPagamento toEnum(Integer id) {
		if (id == null) {
			return null;
		}

		return Arrays.stream(EstadoPagamento.values()).filter(e -> e.cod == id.intValue()).findFirst()
				.orElseThrow(() -> new IllegalArgumentException(String.format("Invalid Id: %d", id)));
	}
}
