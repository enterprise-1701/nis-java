package com.cubic.nisjava.dataproviders;

import java.lang.reflect.Method;
import org.testng.annotations.DataProvider;
import com.cubic.datadriven.TestDataUtil;
import com.cubic.nisjava.constants.AppConstants;

/**
 * @author 203610
 * Jun 4, 2018
 */
public class NISDataProviderRetailAPI 
{	
	@DataProvider(name = AppConstants.DATA_PROVIDER)
	public static Object[][] dataProvider(Method method) throws Throwable
	{

		String testDatafilePath = AppConstants.NIS_TEST_DATA_FOLDER_REATILAPI+"/"+method.getDeclaringClass().getSimpleName()+".json";
		String parentElement = method.getName();
		return TestDataUtil.getTestDataFromJson(testDatafilePath, parentElement);
	}
}
