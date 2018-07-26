package com.cubic.nisjava.dataproviders;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

import com.cubic.backoffice.baseobjects.BaseDataProvider;
import com.cubic.backoffice.constants.BackOfficeGlobals;
import com.cubic.datadriven.TestDataUtil;

import com.cubic.nisjava.constants.*;

public class NISDataProviderSource extends BaseDataProvider {
	
	@DataProvider(name = AppConstants.DATA_PROVIDER)
    public static Object[][] dataProvider(Method method) throws Throwable {
	    setAndGenerateData(method);
        return TestDataUtil.getTestDataFromJson(testDataFileAbsolutePath, parentElement);
    }
	
	// ADD DATA PROVIDER TO RETURN JSON WITH ARRAYS AND/OR WITHOUT TRIMMING INPUT DATA
	@DataProvider(name = AppConstants.DATA_PROVIDER_RETURN_ARRAYS_NO_TRIM)
	public static Object[][] dataProviderReturnArraysNoTrim (Method method) throws Throwable {
	    setAndGenerateData(method);
		return TestDataUtil.getTestDataFromJsonFile(testDataFileAbsolutePath, parentElement, true, false);
	}
	
	@DataProvider(name = AppConstants.DATA_PROVIDER_RETURN_ARRAYS_WITH_TRIM)
	public static Object[][] dataProviderReturnArrays (Method method) throws Throwable {
	    setAndGenerateData(method);
        return TestDataUtil.getTestDataFromJsonFile(testDataFileAbsolutePath, parentElement, true, true);
	}
	
	@DataProvider(name = AppConstants.DATA_PROVIDER_NO_TRIM)
	public static Object[][] dataProviderNoTrim (Method method) throws Throwable {
	    setAndGenerateData(method);
        return TestDataUtil.getTestDataFromJsonFile(testDataFileAbsolutePath, parentElement, false, false);
	}
	
	public static void setAndGenerateData(Method method) {
        setRootTestDataFolder(AppConstants.NIS_TEST_DATA_FOLDER);
        setFileExtension(BackOfficeGlobals.FILENAME_EXT_JSON);
        generateData(method);
	}

}
