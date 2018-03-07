package com.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biz.impl.Biz;
import com.controller.base.AbstractController;
import com.controller.base.ERROR_CODE;

@Controller
public class WebControllerV1 extends AbstractController{

	@Autowired
	private Biz bizTest;

	@RequestMapping(value = "/v1/healthcheck", method = RequestMethod.GET)
	public @ResponseBody String healthcheck(HttpServletRequest hRequest, HttpServletResponse response)
			throws Exception {
		return "Server is running";
	}

	@RequestMapping(value = "/v1/projectName", method = RequestMethod.GET)
	public @ResponseBody Object getProjectName(HttpServletRequest hRequest, HttpServletResponse response)
			throws Exception {
		response.setHeader("Access-Control-Allow-Origin", "*");
		return prepareResponse(bizTest.getProjectName(), null, null, response,
				HttpStatus.SC_OK);
	}

	@RequestMapping(value = "/v1/projectName", method = RequestMethod.PUT)
	public @ResponseBody Object changeProjectName(@RequestParam Map<String, Object> qParams, HttpServletRequest hRequest, HttpServletResponse response)
			throws Exception {
		if(!qParams.containsKey("q") || qParams.get("q") == null || StringUtils.isEmpty(qParams.get("q").toString())){
			return prepareResponse(null, null, ERROR_CODE.Q_MANDATORY, response, HttpStatus.SC_BAD_REQUEST);
		}
		bizTest.changeProjectName(qParams.get("q").toString());
		return prepareResponse("DONE", null, null, response,
				HttpStatus.SC_OK);
	}

}
