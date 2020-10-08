package br.com.transire.service;

import java.util.List;

public interface BaseService<T, ID> {

	T findById(ID id);

	List<T> findAll();

	void deleteById(ID id);

	void delete(T entity);

	void deleteAll();

	long count();

	boolean existsById(ID id);

	T save(T entity);

	Iterable<T> saveAll(List<T> list);

	Iterable<T> findAllById(Iterable<ID> ids);
}