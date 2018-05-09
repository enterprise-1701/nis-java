package com.cubic.nisjava.apiobjects;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class WSReasonCodeType {

	@SerializedName("typeId")
	@Expose
	private Integer typeId;
	@SerializedName("name")
	@Expose
	private String name;
	@SerializedName("description")
	@Expose
	private String description;
	@SerializedName("reasonCodes")
	@Expose
	private List<WSReasonCode> reasonCodes = null;

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<WSReasonCode> getReasonCodes() {
		return reasonCodes;
	}

	public void setReasonCodes(List<WSReasonCode> reasonCodes) {
		this.reasonCodes = reasonCodes;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(description).append(name).append(typeId).append(reasonCodes).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof WSReasonCodeType) == false) {
			return false;
		}
		WSReasonCodeType rhs = ((WSReasonCodeType) other);
		return new EqualsBuilder().append(description, rhs.description).append(name, rhs.name)
				.append(typeId, rhs.typeId).append(reasonCodes, rhs.reasonCodes).isEquals();
	}

}
