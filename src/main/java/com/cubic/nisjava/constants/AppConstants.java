package com.cubic.nisjava.constants;

import java.util.Hashtable;

import com.cubic.genericutils.PropertiesUtil;

public interface AppConstants {

	Hashtable<String, String> CONFIG_PROPERTIES = PropertiesUtil.getPropertysAsHashtable(System.getProperty("user.dir") + "/resources/Config.properties");
	
	String DATA_PROVIDER = "dataProvider";
	String DATA_PROVIDER_RETURN_ARRAYS_WITH_TRIM = "dataProviderReturnWithArraysWithTrim";
	String DATA_PROVIDER_NO_TRIM = "dataProviderDoNotTrimInputData";
	String DATA_PROVIDER_RETURN_ARRAYS_NO_TRIM = "dataProviderReturnWithArraysNoTrimInputData";
	
	String TEST_DATA_FOLDER = CONFIG_PROPERTIES.get("testDataFolder");
	String NIS_TEST_DATA_FOLDER = CONFIG_PROPERTIES.get("nisTestDataFolder");
	String REST_TEST_DATA_FOLDER = CONFIG_PROPERTIES.get("restTestDataFolder");
    String VNH_NIS_TEST_DATA_FOLDER = CONFIG_PROPERTIES.get("vnhNISTestDataFolder");
    String ABN_NIS_TEST_DATA_FOLDER = CONFIG_PROPERTIES.get("abnNISTestDataFolder");  
    String THD_NIS_TEST_DATA_FOLDER = CONFIG_PROPERTIES.get("thdNISTestDataFolder");
    
    // NIS NWAPI V1
    String NIS_TEST_DATA_FOLDER_NWAPI_v1 = CONFIG_PROPERTIES.get("nisTestDataFoldernwapi_v1");
    String NIS_TEST_DATA_FOLDER_NWAPI_v1_TooSmall = CONFIG_PROPERTIES.get("nisTestDataFoldernwapi_v1_TooSmall");
    String NIS_TEST_DATA_FOLDER_NWAPI_v1_Autoload = CONFIG_PROPERTIES.get("nisTestDataFoldernwapi_v1_Autoload");
    String NIS_TEST_DATA_FOLDER_NWAPI_v1_AutoloadTooSmall = CONFIG_PROPERTIES.get("nisTestDataFoldernwapi_v1_AutoloadTooSmall");
    String NIS_TEST_DATA_FOLDER_NWAPI_v1_AutoLoadInvalidAcct = CONFIG_PROPERTIES.get("nisTestDataFoldernwapi_v1_AutoloadInvalidAcct");
    String NIS_TEST_DATA_FOLDER_NWAPI_v1_InvalidSerialNo = CONFIG_PROPERTIES.get("nisTestDataFoldernwapi_v1_InvalidSerialNo");
    
    // NIS Funding source
    String NIS_TEST_DATA_FOLDER_FUNDING_SOURCE = CONFIG_PROPERTIES.get("nisTestDataFolderFundingSource");
    
    String NIS_TEST_DATA_FOLDER_NEXTLINK_v1 = CONFIG_PROPERTIES.get("nisTestDataFoldernextlink_v1");
   // NIS Account module - 
    
    String TESTDATA_FOLDER_PATH =  CONFIG_PROPERTIES.get("testdata_folder_path");
    
    //NIS Patron module -
    String NIS_TEST_DATA_FOLDER_PATRON = CONFIG_PROPERTIES.get("nisTestDataFolderPatron");

    
    //RetailAPi module
    String NIS_TEST_DATA_FOLDER_RETAILAPI = CONFIG_PROPERTIES.get("nisTestDataFolderRetailApi");

      
}

