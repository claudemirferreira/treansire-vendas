package br.com.transire.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.transire.response.Response;
import br.com.transire.service.BaseService;
import br.com.transire.util.ObjectMapperUtils;

/**
 *
 * @param <T>   source Entity
 * @param <ID>  Entity's id
 * @param <DTO> DTO object to convert
 */

@CrossOrigin(origins = "*")
public abstract class AbstractController<T, ID, DTO> {

	protected abstract BaseService<T, ID> getService();

	protected abstract Class<DTO> getDtoClass();

	protected abstract Class<T> getEntityClass();

	@RequestMapping(value = "", method = GET, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<Response<List<DTO>>> find() {
		Response<List<DTO>> response = new Response<List<DTO>>();
		response.setContent(toDto(getService().findAll()));
		return ResponseEntity.ok(response);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<Response<DTO>> findById(@PathVariable("id") ID id) {
		Response<DTO> response = new Response<DTO>();
		try {
			DTO dto = toDto(getService().findById(id));
			response.setContent(dto);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			response.getErrors().add("Register not found id:" + id);
			return ResponseEntity.badRequest().body(response);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteById(@PathVariable("id") ID id) {
		try {
			getService().deleteById(id);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@RequestMapping(value = "", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<Response<DTO>> save(HttpServletResponse resp, @Valid @RequestBody DTO obj) {

		Response<DTO> response = new Response<DTO>();
		try {
			DTO dto = toDto(getService().save(toEntity(obj)));
			response.setContent(dto);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(response);
		}
	}

	protected List<DTO> toDto(Collection<T> entityList) {
		return ObjectMapperUtils.mapAll(entityList, getDtoClass());
	}

	protected DTO toDto(T entityList) {
		return ObjectMapperUtils.map(entityList, getDtoClass());
	}

	protected T toEntity(DTO dto) {
		return ObjectMapperUtils.map(dto, getEntityClass());
	}

	@SuppressWarnings("unused")
	protected List<T> toEntity(Collection<DTO> list) {
		return ObjectMapperUtils.mapAll(list, getEntityClass());
	}

}