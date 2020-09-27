package com.example.demo.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Change;

public interface ChangeRepository extends CrudRepository<Change, Long> {

	public List<Change> findAll();

	public List<Change> findByDenominationLessThanEqualOrderByDenominationDesc(Integer denomination);

}
