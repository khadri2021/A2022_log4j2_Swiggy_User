package com.khadri.log4j.swiggy.user.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.khadri.log4j.swiggy.model.PlaceOrderRequest;
import com.khadri.log4j.swiggy.model.PlaceOrderResponse;
import com.khadri.log4j.swiggy.user.logger.AppLogger;

@Service
public class SwiggyUserService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ExecutorService executorService;

	public PlaceOrderResponse placeOrder(PlaceOrderRequest placeOrderRequest) {

		PlaceOrderResponse placeOrderResponse = null;

		try {

			Future<PlaceOrderResponse> future = executorService
					.submit(() -> restTemplate.postForEntity("http://localhost:9090/swiggy/v1/order/place",
							placeOrderRequest, PlaceOrderResponse.class).getBody());

			placeOrderResponse = future.get();

		} catch (Exception e) {
			AppLogger.error("Exception occured while future response ", e);
		}

		return placeOrderResponse;

	}

}
