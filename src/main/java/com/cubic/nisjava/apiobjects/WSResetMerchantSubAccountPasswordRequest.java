/**
 * 
 */
package com.cubic.nisjava.apiobjects;

/**
 * @author 203610
 * Jun 4, 2018
 */
public class WSResetMerchantSubAccountPasswordRequest 
{
	private String password;

    public String getPassword ()
    {
        return password;
    }

    public void setPassword (String password)
    {
        this.password = password;
    }
    
    @Override
    public String toString()
    {
        return "ClassPojo [password = "+password+"]";
    }
}
