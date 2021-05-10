package net.janaite.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.janaite.cursomc.domain.Cliente;
import net.janaite.cursomc.repositories.ClienteRepository;
import net.janaite.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				String.format("Object not found! Id: %d, type: %s", id, Cliente.class.getName())));
	}

}
