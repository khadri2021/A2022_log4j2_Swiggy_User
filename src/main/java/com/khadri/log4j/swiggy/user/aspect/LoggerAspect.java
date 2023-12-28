package com.khadri.log4j.swiggy.user.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.khadri.log4j.swiggy.user.logger.AppLogger;

@Aspect
@Configuration
public class LoggerAspect {

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private ObjectMapper objectMapper;

	@Around("@annotation(swiggyUserInOut)")
	public Object logSwiggyUserInOutMessage(ProceedingJoinPoint joinPoint, SwiggyUserInOut swiggyUserInOut)
			throws Throwable {

		ExpressionParser expressionParser = applicationContext.getBean("expressionParser", ExpressionParser.class);

		StandardEvaluationContext evaluationContext = applicationContext.getBean("evalutionContext",
				StandardEvaluationContext.class);

		evaluationContext.setVariable("args", joinPoint.getArgs());

		Expression expression = expressionParser.parseExpression(swiggyUserInOut.request());

		Object requestObject = expression.getValue(evaluationContext, joinPoint.getArgs());

		String jsonStringRequest = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestObject);
		AppLogger.swiggyUserInOutMessage(jsonStringRequest);

		Object responseObject = joinPoint.proceed();

		String jsonStringResponse = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseObject);
		AppLogger.swiggyUserInOutMessage(jsonStringResponse);

		return responseObject;
	}

}
