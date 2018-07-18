/**
 * @author 203610
 * Jun 14, 2018
 */
package com.cubic.nisjava.api;

import java.util.Hashtable;


import java.util.List;

import org.apache.log4j.Logger;
import com.cubic.accelerators.RESTActions;
import com.cubic.accelerators.RESTConstants;
import com.cubic.backoffice.constants.BackOfficeGlobals;
import com.cubic.backoffice.utils.BackOfficeUtils;
import com.cubic.nisjava.apiobjects.WSRetailerTransaction;
import com.cubic.nisjava.apiobjects.WSRetailerTransactionRequest;
import com.cubic.nisjava.apiobjects.WSRetailerTransactionsResponse;
import com.cubic.nisjava.utils.DataUtils;
import com.google.gson.Gson;
import com.sun.jersey.api.client.ClientResponse;

public class RetailAPIDailyTransactionReportPOST 
{
	static 
	{
		BackOfficeGlobals.ENV.setEnvironmentVariables();
	}
	public static final String CLASS_NAME = "RetailAPIDailyTransactionReportPOST";
	private static final Logger LOG = Logger.getLogger(CLASS_NAME);
	static String baseUrl =  "https://" + BackOfficeGlobals.ENV.NIS_HOST+ ":" + BackOfficeGlobals.ENV.NIS_PORT + "/nis/retailapi/v1/customer/";
	static String url;
	static ClientResponse clientResponse;
	static WSRetailerTransactionRequest  jsonObj = null;	
	static WSRetailerTransactionsResponse respObj = null;
	static Class<WSRetailerTransactionsResponse> jsonClass = 	WSRetailerTransactionsResponse.class;
	static String jsonStr = "";
	static String resp = "";


	/**
	 * @param restActions
	 * @param data
	 * @throws Throwable 
	 */
	public static void retailerDailyTransactionReportEmployee(RESTActions restActions, Hashtable<String, String> data) throws Throwable 
	{
		//Send POST request and get Client Response
		sendUrlAndGetClientResponse(restActions, data);

		//Validate Response Field
		if(DataUtils.validateResponseCode(restActions, data.get("ExpectedResponseCode"), clientResponse))
		{
			validateResponseForListOfTransactions(restActions,data);
			
			//Verifying Response returned the data related only to the userId from the request body
			if(Integer.parseInt(data.get("UserId"))==(respObj.getTransactions().getTransactions().get(0).getEmployeeInfo().getEmployeeId()))
			{
				restActions.successReport("Verifying current Employee Data", "Response returned the data related only to EmployeeId passed as a userId in request body");
			}
			else
			{
				restActions.failureReport("Verifying current Employee Data", "Response doesn't returned the data related only to EmployeeId whic is requested as an attribute from the request body");
			}
		}
	}		
	/**
	 * @param restActions
	 * @param data
	 * @throws Throwable 
	 */
	public static void retailerDailyTransactionReportAdmin(RESTActions restActions, Hashtable<String, String> data) throws Throwable 
	{
		//Send POST request and get Client Response
		sendUrlAndGetClientResponse(restActions, data);

		//Validate Response Field
		if(DataUtils.validateResponseCode(restActions, data.get("ExpectedResponseCode"), clientResponse))
		{
			restActions.successReport("Verifying All User's Transaction data", "Verifying All user's data under customerId "+data.get("CustomerId"));
			validateAllUsersTransactionsForACustomerAdmin(restActions,data);
			restActions.successReport("Verifyied All User's Transaction data", "Verified All user's data under customerId "+data.get("CustomerId"));
			validateResponseForListOfTransactions(restActions,data);
		}
	}	
	/**
	 * @param restActions
	 * @param data
	 * @throws Throwable 
	 */
	public static void retailerDailyTransactionReportManager(RESTActions restActions, Hashtable<String, String> data) throws Throwable 
	{
		//Send POST request and get Client Response
		sendUrlAndGetClientResponse(restActions, data);

		//Validate Response Field
		if(DataUtils.validateResponseCode(restActions, data.get("ExpectedResponseCode"), clientResponse))
		{
			restActions.successReport("Verifying All  manager and employee's Transaction data", "Verifying all manager and employee's transactions under the same customerId "+data.get("CustomerId"));
			validateAllUsersTransactionsForACustomerManager(restActions,data);
			restActions.successReport("Verifyied All  manager and employee's Transaction data", "Verified all manager and employee's transactions under the same customerId "+data.get("CustomerId"));
			validateResponseForListOfTransactions(restActions,data);
		}
	}
	/**
	 * @param restActions
	 * @param data
	 */
	private static void validateAllUsersTransactionsForACustomerAdmin(RESTActions restActions,Hashtable<String, String> data)
	{
		if(iterateResponseToValidateUserInTransaction(restActions,data.get("Cust_CMS000005210_Admin")))
		{
			restActions.successReport("Verifying user(Admin) "+data.get("Cust_CMS000005210_Admin")+ " Transactions in Response", "Admin "+data.get("Cust_CMS000005210_Admin")+" Transactions is available in Response");
		}
		else
		{
			restActions.failureReport("Verifying user(Admin) "+data.get("Cust_CMS000005210_Admin")+ " Transactions in Response", "Admin "+data.get("Cust_CMS000005210_Admin")+" Transactions is not available in Response");
		}		
		verifyIsManagerAndEmployeeTransactionsAvailable(restActions,data);				
	}
	/**
	 * @param restActions
	 * @param transactions
	 * @param user
	 */
	private static boolean iterateResponseToValidateUserInTransaction(RESTActions restActions, String user)
	{
		List<WSRetailerTransaction> transactions = respObj.getTransactions().getTransactions();
		Boolean flag = false;
		for(int i=0;i<transactions.size();i++)
		{			
			if(transactions.get(i).getEmployeeInfo().getEmployeeId()==Integer.parseInt(user))
			{
				flag=true;
			}
		}
		return flag;		
	}

	/**
	 * @param restActions
	 * @param data
	 */
	private static void validateAllUsersTransactionsForACustomerManager(RESTActions restActions,Hashtable<String, String> data) 
	{
		if(iterateResponseToValidateUserInTransaction(restActions,data.get("Cust_CMS000005210_Admin")))
		{
			restActions.failureReport("Verifying user(Admin) "+data.get("Cust_CMS000005210_Admin")+ " Transactions in Response", data.get("Cust_CMS000005210_Admin")+" who is an admin employee id is available in Transactions Response of a Manager");
		}
		verifyIsManagerAndEmployeeTransactionsAvailable(restActions,data);
	}
	/**
	 * @param restActions
	 * @param data
	 */
	private static void verifyIsManagerAndEmployeeTransactionsAvailable(RESTActions restActions,Hashtable<String, String> data) 
	{
		if(iterateResponseToValidateUserInTransaction(restActions,data.get("Cust_CMS000005210_Manager1")))
		{
			restActions.successReport("Verifying user(Manager) "+data.get("Cust_CMS000005210_Manager1")+ " Transactions in Response", "Manager "+data.get("Cust_CMS000005210_Manager1")+" Transactions is available in Response");
		}
		else
		{
			restActions.failureReport("Verifying user(Manager) "+data.get("Cust_CMS000005210_Manager1")+ " Transactions in Response", "Manager "+data.get("Cust_CMS000005210_Manager1")+" Transactions is not available in Response");
		}

		if(iterateResponseToValidateUserInTransaction(restActions,data.get("Cust_CMS000005210_Manager2")))
		{
			restActions.successReport("Verifying user(Manager) "+data.get("Cust_CMS000005210_Manager2")+ " Transactions in Response", "Manager "+data.get("Cust_CMS000005210_Manager2")+" Transactions is available in Response");
		}
		else
		{
			restActions.failureReport("Verifying user(Manager) "+data.get("Cust_CMS000005210_Manager2")+ " Transactions in Response", "Manager "+data.get("Cust_CMS000005210_Manager2")+" Transactions is not available in Response");
		}

		if(iterateResponseToValidateUserInTransaction(restActions,data.get("Cust_CMS000005210_Employee1")))
		{
			restActions.successReport("Verifying user(Employee) "+data.get("Cust_CMS000005210_Employee1")+ " Transactions in Response", "Employee "+data.get("Cust_CMS000005210_Employee1")+" Transactions is available in Response");
		}
		else
		{
			restActions.failureReport("Verifying user(Employee) "+data.get("Cust_CMS000005210_Employee1")+ " Transactions in Response", "Employee "+data.get("Cust_CMS000005210_Employee1")+" Transactions is not available in Response");
		}

		if(iterateResponseToValidateUserInTransaction(restActions,data.get("Cust_CMS000005210_Employee2")))
		{
			restActions.successReport("Verifying user(Employee) "+data.get("Cust_CMS000005210_Employee2")+ " Transactions in Response", "Employee "+data.get("Cust_CMS000005210_Employee2")+" Transactions is available in Response");
		}
		else
		{
			restActions.failureReport("Verifying user(Employee) "+data.get("Cust_CMS000005210_Employee2")+ " Transactions in Response", "Employee "+data.get("Cust_CMS000005210_Employee2")+" Transactions is not available in Response");
		}		
	}
	/**
	 * @param restActions
	 * @param data
	 * @throws Throwable 
	 */
	public static void retailerDailyTransactionReportDeviceIdNotFound(RESTActions restActions,Hashtable<String, String> data) throws Throwable 
	{
		//Send POST request and get Client Response
		sendUrlAndGetClientResponse(restActions, data);

		//Validate Response Field
		if(DataUtils.validateResponseCode(restActions, data.get("ExpectedResponseCode"), clientResponse))
		{
			restActions.successReport("Validating Response", "Verifying Response FieldName & ErrorKey");			
			DataUtils.validateResponseFieldValue(restActions, data.get("ExpectedFieldName"), respObj.getHdr().getFieldName(),data.get("AttributeFieldName"));
			DataUtils.validateResponseFieldValue(restActions, data.get("ExpectedErrorKey"), respObj.getHdr().getErrorKey(),data.get("AttributeErrorKey"));
		}		
	}
	/**
	 * @param restActions
	 * @param data
	 * @throws Throwable 
	 */
	public static void retailerDailyTransactionReportInvalidDate(RESTActions restActions,Hashtable<String, String> data) throws Throwable 
	{
		//Send POST request and get Client Response
		sendUrlAndGetClientResponse(restActions, data);

		//Validate Response Field
		if(DataUtils.validateResponseCode(restActions, data.get("ExpectedResponseCode"), clientResponse))
		{
			restActions.successReport("Validating Response", "Verifying Response Error Message & Error Key");
			DataUtils.validateResponseFieldValue(restActions, data.get("ExpectedErrorMessage"), respObj.getHdr().getErrorMessage(),data.get("AttributeErrorMessage"));
			DataUtils.validateResponseFieldValue(restActions, data.get("ExpectedErrorKey"), respObj.getHdr().getErrorKey(),data.get("AttributeErrorKey"));
		}		
	}	

	/**
	 * @param restActions
	 * @param data
	 * @throws Throwable 
	 */
	public static void retailerDailyTransactionReportNoTransaction(RESTActions restActions,Hashtable<String, String> data) throws Throwable 
	{
		//Send POST request and get Client Response
		sendUrlAndGetClientResponse(restActions, data);		
		//Validate Response Field
		if(DataUtils.validateResponseCode(restActions, data.get("ExpectedResponseCode"), clientResponse))
		{
			validateResponseForNoTransactions(restActions);
		}		
	}
	/**
	 * @param restActions
	 */
	private static void validateResponseForNoTransactions(RESTActions restActions) 
	{
		restActions.successReport("No of transactions returned in response ", "       "+respObj.getTransactions().getTransactions().size());
		restActions.assertTrue(respObj.getTransactions().getTransactions().size()==0, "No Transactions has been returned in Response as size of the Transactions list is 0");
	}
	/**
	 * @param restActions
	 * @param data
	 */
	private static void validateResponseForListOfTransactions(RESTActions restActions,Hashtable<String, String> data) 
	{
		restActions.successReport("Started Validating Response", "Verifying Response contains List of Transactions(ItemType,Quantity,LineiTem Totla, Employee Id) and doesn't contains any null values");

		//Validating List of Transactions
		List<WSRetailerTransaction> transactionsList = respObj.getTransactions().getTransactions();
		if(transactionsList.size()==0)
		{
			restActions.failureReport("Verifying List of Transactions", "List of transactions returned is "+transactionsList.size());
		}
		else
		{
			restActions.successReport("Verifying List of Transactions", "List of transactions returned is "+transactionsList.size());
		}
		for(int i=0;i<transactionsList.size();i++)
		{
			DataUtils.validateResponseStringFieldForNullValues(restActions, transactionsList.get(i).getItemType(), data.get("Field_Transactions_ItemType"));
			DataUtils.validateResponseIntegerField(restActions, transactionsList.get(i).getQuantity(), data.get("Field_Transactions_Quantity"));
			DataUtils.validateResponseIntegerField(restActions, transactionsList.get(i).getLineItemTotal(), data.get("Field_Transactions_LineItemTotal"));
			DataUtils.validateResponseIntegerField(restActions, transactionsList.get(i).getEmployeeInfo().getEmployeeId(), data.get("Field_Transactions_EmployeeID"));
			DataUtils.validateResponseStringFieldForNullValues(restActions, transactionsList.get(i).getEmployeeInfo().getEmployeeName().getFirstName(), data.get("Field_Transactions_Employee_FirstName"));
			DataUtils.validateResponseStringFieldForNullValues(restActions, transactionsList.get(i).getEmployeeInfo().getEmployeeName().getLastName(), data.get("Field_Transactions_Employee_LastName"));
			DataUtils.validateResponseStringFieldForNullValues(restActions, transactionsList.get(i).getEmployeeInfo().getUsername(), data.get("Field_Transactions_Employee_UserName"));
		}
		restActions.successReport("Validated Response", "Verified Response contains List of Transactions(ItemType,Quantity,LineiTem Totla, Employee Id) without any null values");
	}

	/**
	 * @param restActions
	 * @param data
	 * @throws Throwable 
	 */
	private static void sendUrlAndGetClientResponse(RESTActions restActions,Hashtable<String, String> data) throws Throwable
	{
		url = baseUrl+data.get("CustomerId")+data.get("EndPoints");
		LOG.info(url);

		restActions.successReport("Sending URL..", url);		
		// Build JSON Object
		jsonStr = buildJsonRequestBody(restActions,data);
		restActions.successReport("Sending Request body", jsonStr);

		//Send request and get Client response
		clientResponse = restActions.postClientResponse(url, jsonStr, DataUtils.getHeaderForRetailApi(),null,RESTConstants.APPLICATION_JSON);
		//restActions.successReport("Response Code from Server", ""+clientResponse.getStatus());
		resp = clientResponse.getEntity(String.class);
		LOG.info(resp);
		restActions.successReport("Response from Server is ", resp);

		// De-serialize the Response into a JSON Object
		Gson g = new Gson();
		respObj = g.fromJson(resp, jsonClass);

		//Printing response using Pretty Printer
		DataUtils.printResponseWithPrettyPrinter(resp);				
	}
	/**
	 * @param restActions
	 * @param data
	 * @return
	 */
	private static String buildJsonRequestBody(RESTActions restActions,Hashtable<String, String> data) 
	{
		//Setting up the Request body
		jsonObj = new WSRetailerTransactionRequest();
		jsonObj.setDate(data.get("TransactionDate"));
		jsonObj.setDeviceId(data.get("DeviceID"));
		jsonObj.setUserId(data.get("UserId"));

		// Get JSON String representation of the Object
		jsonStr = BackOfficeUtils.getJSONFromObject(jsonObj);		
		LOG.info("Converted JSON String: " + jsonStr);
		//restActions.successReport("Request body is ", jsonStr);
		return jsonStr;
	}
	

}
