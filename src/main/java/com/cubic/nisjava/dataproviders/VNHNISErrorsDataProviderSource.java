package com.cubic.nisjava.dataproviders;

import java.lang.reflect.Method;
import org.testng.annotations.DataProvider;
import com.cubic.datadriven.TestDataUtil;
import com.cubic.nisjava.constants.*;

public class VNHNISErrorsDataProviderSource {

    @DataProvider(name = AppConstants.DATA_PROVIDER)
    public static Object[][] dataProvider(Method method) throws Throwable {
    	
        String parentElement = method.getName();
        String testDatafilePath = AppConstants.VNH_NIS_TEST_DATA_FOLDER+"/"+method.getDeclaringClass().getSimpleName()+"_"+parentElement+".json";
        return TestDataUtil.getTestDataFromJson(testDatafilePath, parentElement);
        
    }
    
}