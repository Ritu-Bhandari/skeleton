package com.controller.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.EnumUtils;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.springframework.http.converter.json.MappingJacksonValue;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import lombok.AllArgsConstructor;
import lombok.Data;

public abstract class AbstractController {

	private static final Logger logger = Logger
			.getLogger(AbstractController.class);

	protected Object prepareResponse(Object obj, String callBackMethod, ERROR_CODE errorCode,
			HttpServletResponse httpResponse, int status) {

		ApiResponse response = new ApiResponse();
		response.setResponse(obj != null? obj: new HashMap<>());
		if (errorCode != null) {

			Error error = new Error();
			error.setErrorCode(errorCode);

			List<Error> list = new ArrayList<Error>();
			list.add(error);
			response.setErrors(list);
		}

		httpResponse.setStatus(status);

		if (Strings.isNullOrEmpty(callBackMethod))
			return response;
		else {
			MappingJacksonValue value = new MappingJacksonValue(response);
			value.setJsonpFunction(callBackMethod);
			return value;
		}
	}

	protected Object prepareResponseWithMultipleErrors(Object obj,
			String callBackMethod,List<ERROR_CODE> errorList, HttpServletResponse httpResponse,
			int status) {

		ApiResponse response = new ApiResponse();
		response.setResponse(obj);
		if (errorList != null) {
			List<Error> list = new ArrayList<Error>();
			for (ERROR_CODE errorCode : errorList) {
				Error error = new Error();
				error.setErrorCode(errorCode);
				list.add(error);
			}
			if (!list.isEmpty()) {
				response.setErrors(list);
			}
		}

		httpResponse.setStatus(status);

		if (Strings.isNullOrEmpty(callBackMethod))
			return response;
		else {
			MappingJacksonValue value = new MappingJacksonValue(response);
			value.setJsonpFunction(callBackMethod);
			return value;
		}
	}

	protected List<ERROR_CODE> addException(Exception ex, int status,
			List<ERROR_CODE> errorList) {
		logger.error("Error:", ex);
		if(errorList == null){
			errorList = Lists.newArrayList();
		}
		if (ex.getMessage()!= null && EnumUtils.isValidEnum(ERROR_CODE.class, ex.getMessage())) {
			ERROR_CODE error = Enum.valueOf(ERROR_CODE.class, ex.getMessage());
			status = HttpStatus.SC_BAD_REQUEST;
			errorList.add(error);
		} else {
			ERROR_CODE error = ERROR_CODE.INTERNAL_ERROR;
			status = HttpStatus.SC_INTERNAL_SERVER_ERROR;
			errorList.add(error);
		}
		return errorList;
	}

	@Data
	@AllArgsConstructor
	protected class SaveAcknowledgement {
		private boolean acknowledged;
		private String id;
	}

	@Data
	@AllArgsConstructor
	protected class UploadResponse {
		private boolean success;
	}

	@Data
	@AllArgsConstructor
	protected class HttpResponseBody{
		private int statusCode;
		private ERROR_CODE errorCode ;
	}

}
