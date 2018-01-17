package com.cubic.nisjava.dataproviders;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;
import com.cubic.nisjava.constants.*;
import com.cubic.datadriven.TestDataUtil;

public class NISDataProviderNWAPI_v1_TooSmall {
    @DataProvider(name = AppConstants.DATA_PROVIDER)
    public static Object[][] dataProvider(Method method) throws Throwable {

    	String className = method.getDeclaringClass().getSimpleName();
        String testDatafilePath = AppConstants.NIS_TEST_DATA_FOLDER_NWAPI_v1_TooSmall+"/"+ className +".json";
        String parentElement = method.getName();
        return TestDataUtil.getTestDataFromJson(testDatafilePath, parentElement);
    }
}
