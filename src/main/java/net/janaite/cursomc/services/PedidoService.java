package net.janaite.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.janaite.cursomc.domain.Pedido;
import net.janaite.cursomc.repositories.PedidoRepository;
import net.janaite.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;

	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				String.format("Object not found! Id: %d, type: %s", id, Pedido.class.getName())));
	}

}
