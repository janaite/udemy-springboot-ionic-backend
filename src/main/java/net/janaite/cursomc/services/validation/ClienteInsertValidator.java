package net.janaite.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import net.janaite.cursomc.domain.enums.TipoCliente;
import net.janaite.cursomc.dto.ClienteNewDTO;
import net.janaite.cursomc.resources.exception.FieldMessage;
import net.janaite.cursomc.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		// tests
		if (objDto.getTipo() == null) {
			list.add(new FieldMessage("Tipo", "Tipo cannot be null"));
		} else {

			if (objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
				list.add(new FieldMessage("cpfOuCnpj", "invalid CPF"));
			}
			
			if (objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
				list.add(new FieldMessage("cpfOuCnpj", "invalid CNPJ"));
			}
		}

		// Convert a FiledMessage list into spring framework compatible list of errors
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}

}
