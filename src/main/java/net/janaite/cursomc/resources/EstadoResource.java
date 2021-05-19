package net.janaite.cursomc.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.janaite.cursomc.domain.Cidade;
import net.janaite.cursomc.domain.Estado;
import net.janaite.cursomc.dto.CidadeDTO;
import net.janaite.cursomc.dto.EstadoDTO;
import net.janaite.cursomc.services.CidadeService;
import net.janaite.cursomc.services.EstadoService;

@RestController
@RequestMapping(value = "/estados")
public class EstadoResource {

	@Autowired
	private EstadoService service;

	@Autowired
	private CidadeService cidadeService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<EstadoDTO>> getAllEstados() {
		List<Estado> obj = service.getAllEstados();
		List<EstadoDTO> listDto = obj.stream().map(x -> new EstadoDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	@RequestMapping(value = "/{id}/cidades", method = RequestMethod.GET)
	public ResponseEntity<List<CidadeDTO>> getAllCidades(@PathVariable Integer id) {
		List<Cidade> lst = cidadeService.findByEstado(id);
		List<CidadeDTO> listDto = lst.stream().map(x -> new CidadeDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
}
