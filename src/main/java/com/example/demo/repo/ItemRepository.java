package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

	public List<Item> findAll();

	public List<Item> findByNameContainsIgnoreCase(String name);

}
