package com.cubic.nisjava.apiobjects;


import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.LinkedList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Bean used by GSON to parse the response from the Notification Detail URL.
 * 
 * @author 203402
 *
 */
public class WSContact {
	@SerializedName("contactId")
	@Expose
	private String contactId;

	@SerializedName("firstName")
	@Expose
	private String firstName;

	@SerializedName("lastName")
	@Expose
	private String lastName;

	@SerializedName("email")
	@Expose
	private String email;

	@SerializedName("smsPhone")
	@Expose
	private String smsPhone;

	@SerializedName("mandatory")
	@Expose
	private Boolean mandatory;
	@SerializedName("preferences")
	@Expose
	private List<WSPreference> preferences = null;

	private static String diff;

	private List<String> errors = new LinkedList<String>();

	/**
	 * Get the list of errors, which will be non-null, and of length
	 * zero if there are no errors.
	 * 
	 * @return The String of errors.
	 */
	public String getErrors() {
		StringWriter sw = new StringWriter();
		PrintWriter out = new PrintWriter( sw );
		for ( String error : errors ) {
			out.println( error );
		}
		return sw.toString();
	}

	public String getContactId() {
		return contactId;
	}

	public void setContactId(String contactId) {
		this.contactId = contactId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSmsPhone() {
		return smsPhone;
	}

	public void setSmsPhone(String smsPhone) {
		this.smsPhone = smsPhone;
	}

	public Boolean getMandatory() {
		return mandatory;
	}

	public void setMandatory(Boolean mandatory) {
		this.mandatory = mandatory;
	}
	public List<WSPreference> getPreferences() {
		return preferences;
	}

	public void setPreferences(List<WSPreference> preferences) {
		this.preferences = preferences;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contactId == null) ? 0 : contactId.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((mandatory == null) ? 0 : mandatory.hashCode());
		result = prime * result + ((smsPhone == null) ? 0 : smsPhone.hashCode());
		return result;
	}

	public static String getDiff() {
		return diff;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WSContact other = (WSContact) obj;
		if (contactId == null) {
			if (other.contactId != null) {
				diff = String.format("BAD CONTACT ID - EXPECTED null, FOUND %s", other.contactId );
				return false;
			}
		} else if (!contactId.equals(other.contactId)) {
			diff = String.format("BAD CONTACT ID - EXPECTED %s, FOUND %s", this.contactId, other.contactId );
			return false;
		}
		if (email == null) {
			if (other.email != null) {
				diff = String.format("BAD EMAIL - EXPECTED null, FOUND %s", other.email );
				return false;
			}
		} else if (!email.equals(other.email)) {
			diff = String.format("BAD EMAIL - EXPECTED %s, FOUND %s", this.email, other.email );
			return false;
		}

		if (firstName == null) {
			if (other.firstName != null) {
				diff = String.format("BAD FIRST NAME - EXPECTED null, FOUND %s", other.firstName );
				return false;
			}
		} else if (!firstName.equals(other.firstName)) {
			diff = String.format("BAD FIRST NAME - EXPECTED %s, FOUND %s", this.firstName, other.firstName );
			return false;
		}

		if (lastName == null) {
			if (other.lastName != null) {
				diff = String.format("BAD LAST NAME - EXPECTED null, FOUND %s", other.lastName );
				return false;
			}
		} else if (!lastName.equals(other.lastName)) {
			diff = String.format("BAD LAST NAME - EXPECTED %s, FOUND %s", this.lastName, other.lastName );
			return false;
		}

		if (mandatory == null) {
			if (other.mandatory != null) {
				diff = String.format("BAD MANDATORY - EXPECTED null, FOUND %s", other.mandatory );
				return false;
			}
		} else if (!mandatory.equals(other.mandatory)) {
			diff = String.format("BAD MANDATORY - EXPECTED %s, FOUND %s", this.mandatory, other.mandatory );
			return false;
		}
		if (smsPhone == null) {
			if (other.smsPhone != null) {
				diff = String.format("BAD MANDATORY - EXPECTED null, FOUND %s", other.smsPhone );
				return false;
			}
		} else if (!smsPhone.equals(other.smsPhone)) {
			diff = String.format("BAD MANDATORY - EXPECTED %s, FOUND %s", this.smsPhone, other.smsPhone );
			return false;
		}
		return true;
	}
}

