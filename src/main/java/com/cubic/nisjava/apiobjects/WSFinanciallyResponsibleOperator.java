package com.cubic.nisjava.apiobjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class WSFinanciallyResponsibleOperator {

	@SerializedName("financiallyResponsibleOperatorId")
	@Expose
	private Integer financiallyResponsibleOperatorId;
	@SerializedName("financiallyResponsibleOperatorDescription")
	@Expose
	private String financiallyResponsibleOperatorDescription;

	public Integer getFinanciallyResponsibleOperatorId() {
		return financiallyResponsibleOperatorId;
	}

	public void setFinanciallyResponsibleOperatorId(Integer financiallyResponsibleOperatorId) {
		this.financiallyResponsibleOperatorId = financiallyResponsibleOperatorId;
	}

	public String getFinanciallyResponsibleOperatorDescription() {
		return financiallyResponsibleOperatorDescription;
	}

	public void setFinanciallyResponsibleOperatorDescription(String financiallyResponsibleOperatorDescription) {
		this.financiallyResponsibleOperatorDescription = financiallyResponsibleOperatorDescription;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(financiallyResponsibleOperatorId)
				.append(financiallyResponsibleOperatorDescription).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof WSFinanciallyResponsibleOperator) == false) {
			return false;
		}
		WSFinanciallyResponsibleOperator rhs = ((WSFinanciallyResponsibleOperator) other);
		return new EqualsBuilder().append(financiallyResponsibleOperatorId, rhs.financiallyResponsibleOperatorId)
				.append(financiallyResponsibleOperatorDescription, rhs.financiallyResponsibleOperatorDescription)
				.isEquals();
	}

}
