package com.cubic.nisjava.dataproviders;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;
import com.cubic.nisjava.constants.*;
import com.cubic.datadriven.TestDataUtil;

public class NISDataProviderSourceFundingSource {
    @DataProvider(name = AppConstants.DATA_PROVIDER)
    public static Object[][] dataProvider(Method method) throws Throwable {

    	String className = method.getDeclaringClass().getSimpleName();
        String testDatafilePath = AppConstants.NIS_TEST_DATA_FOLDER_FUNDING_SOURCE+"/"+ className +".json";
        String parentElement = method.getName();
        return TestDataUtil.getTestDataFromJson(testDatafilePath, parentElement);
    }
}
