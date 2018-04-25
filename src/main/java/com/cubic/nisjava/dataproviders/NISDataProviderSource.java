package com.cubic.nisjava.dataproviders;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

import com.cubic.datadriven.TestDataUtil;
import com.cubic.nisjava.constants.*;

public class NISDataProviderSource {
	
	static String testDatafilePath;
	static String parentElement;
	
	@DataProvider(name = AppConstants.DATA_PROVIDER)
    public static Object[][] dataProvider(Method method) throws Throwable {
        generateData();
        return TestDataUtil.getTestDataFromJson(testDatafilePath, parentElement);
    }
	
	// ADD DATA PROVIDER TO RETURN JSON WITH ARRAYS AND/OR WITHOUT TRIMMING INPUT DATA
	@DataProvider(name = AppConstants.DATA_PROVIDER_RETURN_ARRAYS_NO_TRIM)
	public static Object[][] dataProviderReturnArraysNoTrim (Method method) throws Throwable {
		generateData();
		return TestDataUtil.getTestDataFromJsonFile(testDatafilePath, parentElement, true, true);
	}
	
	@DataProvider(name = AppConstants.DATA_PROVIDER_RETURN_ARRAYS)
	public static Object[][] dataProviderReturnArrays (Method method) throws Throwable {
		generateData();
        return TestDataUtil.getTestDataFromJsonFile(testDatafilePath, parentElement, true, false);
	}
	
	@DataProvider(name = AppConstants.DATA_PROVIDER_NO_TRIM)
	public static Object[][] dataProviderNoTrim (Method method) throws Throwable {
		generateData();
        return TestDataUtil.getTestDataFromJsonFile(testDatafilePath, parentElement, false, true);
	}
	
	public static void generateData() {
		testDatafilePath = AppConstants.NIS_TEST_DATA_FOLDER + "/" + method.getDeclaringClass().getSimpleName() + ".json";
        parentElement = method.getName();
	}
	
}
