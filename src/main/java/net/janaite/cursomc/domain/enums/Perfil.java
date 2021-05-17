package net.janaite.cursomc.domain.enums;

import java.util.Arrays;

public enum Perfil {
	ADMIN(1, "ROLE_ADMIN"),
	CLIENTE(2, "ROLE_CLIENTE");
	
	private int cod;
	private String descricao;

	private Perfil(int cod, String desc) {
		this.cod = cod;
		this.descricao = desc;
	}

	public int getCod() {
		return this.cod;
	}

	public String getDescricao() {
		return this.descricao;
	}
	
	public static Perfil toEnum(Integer id) {
		if (id == null) {
			return null;
		}

		return Arrays.stream(Perfil.values()).filter(e -> e.cod == id.intValue()).findFirst()
				.orElseThrow(() -> new IllegalArgumentException(String.format("Invalid Id: %d", id)));
	}
}
