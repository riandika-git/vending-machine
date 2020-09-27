package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Item;
import com.example.demo.service.ItemService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Endpoint for Item", produces = "application/json")
@RestController
public class ItemController {

	@Autowired
	private ItemService itemService;

	@ApiOperation("Get item list")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK", response = Item.class) })
	@GetMapping("/item/list")
	public ResponseEntity<Object> getItemList() {
		return itemService.getItemList();

	}

	@ApiOperation("Get item by id")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK", response = Item.class) })
	@GetMapping("/item/{id}")
	public ResponseEntity<Object> getItemById(@PathVariable("id") Long id) {
		return itemService.getItemById(id);
	}

	@ApiOperation("Save new item record")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK", response = ResponseEntity.class) })
	@PostMapping(path = "/item/add", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> addItem(@Valid @RequestBody Item item) {
		return itemService.addItem(item);
	}

	@ApiOperation("Update item record")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK", response = ResponseEntity.class) })
	@PutMapping(path = "/item/update", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> updateItem(@Valid @RequestBody Item item) {
		return itemService.updateItem(item);
	}

	@ApiOperation("Delete item by id")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK", response = ResponseEntity.class) })
	@DeleteMapping("/item/delete/{id}")
	public ResponseEntity<Object> deleteItem(@PathVariable("id") Long id) {
		return itemService.deleteItem(id);
	}

}
