package com.dao.impl;

import com.dao.IDao;

public class Dao implements IDao{

	private static String projectName = "skeleton";

	@Override
	public void insertProjectNameInDB(String data) {
		projectName = data;

	}

	@Override
	public String readProjectNameFromDB() {
		return projectName;
	}

}
