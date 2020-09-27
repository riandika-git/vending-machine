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

import com.example.demo.model.Change;
import com.example.demo.service.ChangeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Endpoint for Change", produces = "application/json")
@RestController
public class ChangeController {

	@Autowired
	private ChangeService changeService;

	@ApiOperation("Get change list")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK", response = Change.class) })
	@GetMapping("/change/list")
	public ResponseEntity<Object> getChangeList() {
		return changeService.getChangeList();
	}

	@ApiOperation("Get change by id")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK", response = Change.class) })
	@GetMapping("/change/{id}")
	public ResponseEntity<Object> getChangeById(@PathVariable("id") Long id) {
		return changeService.getChangeById(id);
	}

	@ApiOperation("Save new change record")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK", response = ResponseEntity.class) })
	@PostMapping(path = "/change/add", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> addChange(@Valid @RequestBody Change change) {
		return changeService.addChange(change);
	}

	@ApiOperation("Update change record")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK", response = ResponseEntity.class) })
	@PutMapping(path = "/change/update", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> updateChange(@Valid @RequestBody Change change) {
		return changeService.updateChange(change);
	}

	@ApiOperation("Delete Change by id")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK", response = ResponseEntity.class) })
	@DeleteMapping("/change/delete/{id}")
	public ResponseEntity<Object> deleteChange(@PathVariable("id") Long id) {
		return changeService.deleteChange(id);
	}

}
