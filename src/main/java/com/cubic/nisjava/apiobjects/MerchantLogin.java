/**
 * 
 */

package com.cubic.nisjava.apiobjects;

/**
 * @author 205974
 *
 */

public class MerchantLogin {
	private String username;
	private String password;
	private String deviceSerialNumber;
	
	public MerchantLogin() {
		// Default
	}

	/**
	 * @param username
	 * @param password
	 */
	public MerchantLogin(String username, String password) {
		this.username = username;
		this.password = password;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * @return the deviceSerialNumber
	 */
	public String getDeviceSerialNumber() {
		return deviceSerialNumber;
	}

	/**
	 * @param deviceSerialNumber the deviceSerialNumber to set
	 */
	public void setDeviceSerialNumber(String deviceSerialNumber) {
		this.deviceSerialNumber = deviceSerialNumber;
	}

}
