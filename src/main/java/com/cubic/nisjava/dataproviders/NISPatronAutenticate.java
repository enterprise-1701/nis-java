/**
 * 
 */
package com.cubic.nisjava.dataproviders;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

import com.cubic.datadriven.TestDataUtil;
import com.cubic.nisjava.constants.AppConstants;

/**
 * @author VijayaBhaskar
 * Mar 19, 2018
 */
public class NISPatronAutenticate 
{
	 @DataProvider(name = AppConstants.DATA_PROVIDER)
	    public static Object[][] dataProvider(Method method) throws Throwable {

	        String testDatafilePath = AppConstants.NIS_TEST_DATA_FOLDER_PATRON+"/"+method.getDeclaringClass().getSimpleName()+".json";
	        String parentElement = method.getName();
	        return TestDataUtil.getTestDataFromJson(testDatafilePath, parentElement);
	    }
	 
	 
}