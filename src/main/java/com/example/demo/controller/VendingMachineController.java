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
import com.example.demo.service.VendingMachineService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Endpoint for Vending Machine", produces = "application/json")
@RestController
public class VendingMachineController {

	@Autowired
	private VendingMachineService vendingMachineService;

	@ApiOperation("User select item")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK") })
	@GetMapping("/vendingmachine/select/{id}")
	public ResponseEntity<Object> selectItem(@PathVariable("id") Long id) {
		return vendingMachineService.selectItem(id);

	}

	@ApiOperation("User insert money")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK") })
	@GetMapping("/vendingmachine/insertmoney/{value}")
	public ResponseEntity<Object> insertMoney(@PathVariable("value") Integer value) {
		return vendingMachineService.insertMoney(value);
	}

	@ApiOperation("Refund money")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK") })
	@GetMapping("/vendingmachine/refund")
	public ResponseEntity<Object> refund() {
		return vendingMachineService.refund();
	}


}
