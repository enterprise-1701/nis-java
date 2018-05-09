package com.cubic.nisjava.apiobjects;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class WSReasonCodeTypeList {

	@SerializedName("reasonCodeTypes")
	@Expose
	private List<WSReasonCodeType> reasonCodeTypes = null;

	public List<WSReasonCodeType> getReasonCodeTypes() {
		return reasonCodeTypes;
	}

	public void setReasonCodeTypes(List<WSReasonCodeType> reasonCodeTypes) {
		this.reasonCodeTypes = reasonCodeTypes;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(reasonCodeTypes).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof WSReasonCodeTypeList) == false) {
			return false;
		}
		WSReasonCodeTypeList rhs = ((WSReasonCodeTypeList) other);
		return new EqualsBuilder().append(reasonCodeTypes, rhs.reasonCodeTypes).isEquals();
	}

}