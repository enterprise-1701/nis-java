package com.cubic.nisjava.apiobjects;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class WSReasonCode {

	@SerializedName("reasonCodeId")
	@Expose
	private Integer reasonCodeId;
	@SerializedName("description")
	@Expose
	private String description;
	@SerializedName("active")
	@Expose
	private Boolean active;
	@SerializedName("minAmount")
	@Expose
	private Integer minAmount;
	@SerializedName("maxAmount")
	@Expose
	private Integer maxAmount;
	@SerializedName("notesMandatoryFlag")
	@Expose
	private Boolean notesMandatoryFlag;
	@SerializedName("financiallyResponsibleOperators")
	@Expose
	private List<WSFinanciallyResponsibleOperator> financiallyResponsibleOperators = null;

	public Integer getReasonCodeId() {
		return reasonCodeId;
	}

	public void setReasonCodeId(Integer reasonCodeId) {
		this.reasonCodeId = reasonCodeId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Integer getMinAmount() {
		return minAmount;
	}

	public void setMinAmount(Integer minAmount) {
		this.minAmount = minAmount;
	}

	public Integer getMaxAmount() {
		return maxAmount;
	}

	public void setMaxAmount(Integer maxAmount) {
		this.maxAmount = maxAmount;
	}

	public Boolean getNotesMandatoryFlag() {
		return notesMandatoryFlag;
	}

	public void setNotesMandatoryFlag(Boolean notesMandatoryFlag) {
		this.notesMandatoryFlag = notesMandatoryFlag;
	}

	public List<WSFinanciallyResponsibleOperator> getFinanciallyResponsibleOperators() {
		return financiallyResponsibleOperators;
	}

	public void setFinanciallyResponsibleOperators(
			List<WSFinanciallyResponsibleOperator> financiallyResponsibleOperators) {
		this.financiallyResponsibleOperators = financiallyResponsibleOperators;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(minAmount).append(financiallyResponsibleOperators).append(description)
				.append(reasonCodeId).append(notesMandatoryFlag).append(maxAmount).append(active).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof WSReasonCode) == false) {
			return false;
		}
		WSReasonCode rhs = ((WSReasonCode) other);
		return new EqualsBuilder().append(minAmount, rhs.minAmount)
				.append(financiallyResponsibleOperators, rhs.financiallyResponsibleOperators)
				.append(description, rhs.description).append(reasonCodeId, rhs.reasonCodeId)
				.append(notesMandatoryFlag, rhs.notesMandatoryFlag).append(maxAmount, rhs.maxAmount)
				.append(active, rhs.active).isEquals();
	}

}