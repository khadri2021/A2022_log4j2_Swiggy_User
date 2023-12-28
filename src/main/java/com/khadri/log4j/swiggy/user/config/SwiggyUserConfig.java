package com.khadri.log4j.swiggy.user.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.web.client.RestTemplate;

import com.khadri.log4j.swiggy.user.mapper.PlaceOrderRequestMapper;
import com.khadri.log4j.swiggy.user.request.OrderRequest;

@Configuration
public class SwiggyUserConfig {

	@Bean
	public ExecutorService executorService() {
		return Executors.newFixedThreadPool(10);
	}

	@Bean
	public PlaceOrderRequestMapper placeOrderRequestMapper() {
		return new PlaceOrderRequestMapper();
	}

	@Bean
	public OrderRequest orderRequest() {
		return new OrderRequest();
	}

	@Bean(name = "expressionParser")
	@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public ExpressionParser expressionParser() {
		return new SpelExpressionParser();
	}

	@Bean(name = "evalutionContext")
	@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public StandardEvaluationContext standardEvaluationContext(ApplicationContext applicationContext) {
		StandardEvaluationContext standardEvaluationContext = new StandardEvaluationContext();
		standardEvaluationContext.setBeanResolver(new BeanFactoryResolver(applicationContext));
		return standardEvaluationContext;
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
