package br.com.bancointer.resource;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.bancointer.DTO.DigitoUnicoDTO;
import br.com.bancointer.DTO.UsuarioDTO;
import br.com.bancointer.exceptionHandler.AppExceptionHandler.Erro;
import br.com.bancointer.service.DigitoService;
import br.com.bancointer.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioResource {

	@Autowired
	public UsuarioService usuarioService;
	@Autowired
	public DigitoService digitoService;

	List<DigitoUnicoDTO> digitosCache = new ArrayList<>();

	@GetMapping()
	public ResponseEntity<?> buscarTodos() {

		List<UsuarioDTO> usuarios = usuarioService.pesquisarTodos();
		if (!CollectionUtils.isEmpty(usuarios)) {
			return ResponseEntity.ok(usuarios);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> apagarUsuario(@PathVariable Integer id) {
		try {
			usuarioService.remover(id);
			return ResponseEntity.ok().build();

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new Erro("Erro ao Excluir usuario", e.getLocalizedMessage()));
		}
	}

	@PostMapping
	public ResponseEntity<UsuarioDTO> adicionarUsuario(@RequestBody UsuarioDTO usuario) {
		try {
			UsuarioDTO u = usuarioService.pesquisar(usuario);
			if (u == null) {
				u = usuario;
			}

			u = usuarioService.salvarUsuario(u);
			return ResponseEntity.created(new URI("/usuario/" + u.getId())).body(u);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
		}

	}

	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {

		UsuarioDTO u = buscarUsuarioPorID(id);
		if (u != null) {
			return ResponseEntity.ok(u);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/digito/{digito}")
	public Integer calcularDigitoUnico(@PathVariable Integer digito, @RequestParam Integer idUsuario) {
		Integer digitoCalculado = 0;

		if (!isDigitoEmCache(digito)) {
			digitoCalculado = digitoService.calcularDigitoUnico(digito);
			DigitoUnicoDTO digitoUnico = adicionarDigitoEmCache(digito, digitoCalculado);
			if (idUsuario != null) {
				UsuarioDTO usuario = buscarUsuarioPorID(idUsuario);
				digitoUnico.setUsuario(usuario);
				if (usuario.getDigitos() == null) {
					usuario.setDigitos(new ArrayList<>());
				}
				usuario.getDigitos().add(digitoUnico);
				usuarioService.salvarUsuario(usuario);

			}
		}

		return digitoCalculado;
	}

	@GetMapping("/digito")
	public ResponseEntity<?> pesquisarDigitosEmChache() {
		try {
			return ResponseEntity.ok(digitosCache);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}

	}

	@GetMapping("/{idUsuario}/digito")
	public ResponseEntity<?> buscarDigitoUsuario(@PathVariable Integer idUsuario) {

		UsuarioDTO u = buscarUsuarioPorID(idUsuario);
		if (u != null) {
			return ResponseEntity.ok(u.getDigitos());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	private DigitoUnicoDTO adicionarDigitoEmCache(Integer digito, Integer digitoCalculado) {
		DigitoUnicoDTO digitoUnico = new DigitoUnicoDTO();

		digitoUnico.setDigitoEntrada(digito);
		digitoUnico.setDigitoCalculado(digitoCalculado);
		digitosCache.add(digitoUnico);

		if (digitosCache.size() == 11) {
			digitosCache.remove(0);
		}

		return digitoUnico;

	}

	private boolean isDigitoEmCache(Integer digito) {

		return digitosCache.stream().anyMatch(d -> {
			return d.getDigitoEntrada().equals(digito);
		});
	}

	private UsuarioDTO buscarUsuarioPorID(Integer id) {
		UsuarioDTO usuario = new UsuarioDTO();
		usuario.setId(id);
		UsuarioDTO u = usuarioService.pesquisar(usuario);
		return u;
	}

}
