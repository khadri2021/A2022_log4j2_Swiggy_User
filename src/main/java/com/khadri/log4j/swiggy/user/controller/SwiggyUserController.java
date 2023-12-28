package com.khadri.log4j.swiggy.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.khadri.log4j.swiggy.model.PlaceOrderRequest;
import com.khadri.log4j.swiggy.model.PlaceOrderResponse;
import com.khadri.log4j.swiggy.user.aspect.SwiggyUserInOut;
import com.khadri.log4j.swiggy.user.mapper.PlaceOrderRequestMapper;
import com.khadri.log4j.swiggy.user.request.OrderRequest;
import com.khadri.log4j.swiggy.user.service.SwiggyUserService;

@RestController
@RequestMapping("/swiggy/user/v1")
public class SwiggyUserController {

	@Autowired
	private SwiggyUserService swiggyUserService;

	@Autowired
	private PlaceOrderRequestMapper placeOrderRequestMapper;

	@PostMapping("/order/request")
	@SwiggyUserInOut(request = "#args[0]")
	public ResponseEntity<PlaceOrderResponse> userRequest(@RequestBody OrderRequest orderRequest) {

		PlaceOrderRequest placeOrderReq = placeOrderRequestMapper.map(orderRequest);

		PlaceOrderResponse placeOrderResponse = swiggyUserService.placeOrder(placeOrderReq);

		return new ResponseEntity<PlaceOrderResponse>(placeOrderResponse,HttpStatus.ACCEPTED);
	}

}
