package com.khadri.log4j.swiggy.user.mapper;

import com.khadri.log4j.swiggy.model.PlaceOrderRequest;
import com.khadri.log4j.swiggy.user.request.OrderRequest;

public class PlaceOrderRequestMapper {

	public PlaceOrderRequest map(OrderRequest orderRequest) {

		PlaceOrderRequest placeOrderRequest = new PlaceOrderRequest();
		placeOrderRequest.setItemName(orderRequest.getOrderName());
		placeOrderRequest.setItemQty(orderRequest.getOrderQty());
		placeOrderRequest.getUser().setDoorNo(orderRequest.getUser().getDoorNo());
		placeOrderRequest.getUser().setStreetName(orderRequest.getUser().getStreetName());
		placeOrderRequest.getUser().setLandMark(orderRequest.getUser().getLandMark());
		placeOrderRequest.getUser().setMobileNo(orderRequest.getUser().getMobileNo());

		return placeOrderRequest;
	}

}
