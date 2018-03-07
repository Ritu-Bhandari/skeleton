package com.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.biz.IBiz;
import com.dao.impl.Dao;

public class Biz implements IBiz{
	
	@Autowired
	private Dao daoTest;

	@Override
	public void changeProjectName(String data) {
		daoTest.insertProjectNameInDB(data);

	}

	@Override
	public String getProjectName() {
		return daoTest.readProjectNameFromDB();
	}
}
