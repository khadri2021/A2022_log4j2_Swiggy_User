package com.khadri.log4j.swiggy.user.mapper;

import com.khadri.log4j.swiggy.model.PlaceOrderRequest;
import com.khadri.log4j.swiggy.model.User;
import com.khadri.log4j.swiggy.user.request.OrderRequest;

public class PlaceOrderRequestMapper {

	public PlaceOrderRequest map(OrderRequest orderRequest) {

		PlaceOrderRequest placeOrderRequest = new PlaceOrderRequest();
		placeOrderRequest.setItemName(orderRequest.getOrderName());
		placeOrderRequest.setItemQty(orderRequest.getOrderQty());
		User user = new User();
		user.setDoorNo(orderRequest.getUser().getDoorNo());
		user.setStreetName(orderRequest.getUser().getStreetName());
		user.setLandMark(orderRequest.getUser().getLandMark());
		user.setMobileNo(orderRequest.getUser().getMobileNo());
		
		placeOrderRequest.setUser(user);

		return placeOrderRequest;
	}

}
