package com.controller.base;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Error {

	ERROR_CODE errorCode;
	String errorMessage;
}