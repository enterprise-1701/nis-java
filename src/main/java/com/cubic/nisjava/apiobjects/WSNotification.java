package com.cubic.nisjava.apiobjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSNotification {

	@SerializedName("notificationId")
	@Expose
	private Integer notificationId;
	
	@SerializedName("contactId")
	@Expose
	private String contactId;
	
	@SerializedName("createDateTime")
	@Expose
	private String createDateTime;
	
	@SerializedName("notificationReference")
	@Expose
	private String notificationReference;
	
	@SerializedName("type")
	@Expose
	private String type;
	
	@SerializedName("description")
	@Expose
	private String description;
	
	@SerializedName("channel")
	@Expose
	private String channel;
	
	@SerializedName("recipient")
	@Expose
	private String recipient;
	
	@SerializedName("subject")
	@Expose
	private String subject;
	
	@SerializedName("status")
	@Expose
	private String status;
	
	@SerializedName("sendDateTime")
	@Expose
	private String sendDateTime;
	
	@SerializedName("allowResend")
	@Expose
	private Boolean allowResend;
	
	@SerializedName("contactName")
	@Expose
	private String contactName;

	public Integer getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(Integer notificationId) {
		this.notificationId = notificationId;
	}

	public String getContactId() {
		return contactId;
	}

	public void setContactId(String contactId) {
		this.contactId = contactId;
	}

	public String getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(String createDateTime) {
		this.createDateTime = createDateTime;
	}

	public String getNotificationReference() {
		return notificationReference;
	}

	public void setNotificationReference(String notificationReference) {
		this.notificationReference = notificationReference;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSendDateTime() {
		return sendDateTime;
	}

	public void setSendDateTime(String sendDateTime) {
		this.sendDateTime = sendDateTime;
	}

	public Boolean getAllowResend() {
		return allowResend;
	}

	public void setAllowResend(Boolean allowResend) {
		this.allowResend = allowResend;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((allowResend == null) ? 0 : allowResend.hashCode());
		result = prime * result + ((channel == null) ? 0 : channel.hashCode());
		result = prime * result + ((contactId == null) ? 0 : contactId.hashCode());
		result = prime * result + ((contactName == null) ? 0 : contactName.hashCode());
		result = prime * result + ((createDateTime == null) ? 0 : createDateTime.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((notificationId == null) ? 0 : notificationId.hashCode());
		result = prime * result + ((notificationReference == null) ? 0 : notificationReference.hashCode());
		result = prime * result + ((recipient == null) ? 0 : recipient.hashCode());
		result = prime * result + ((sendDateTime == null) ? 0 : sendDateTime.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	private static String diff;

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
		WSNotification other = (WSNotification) obj;
		if (allowResend == null) {
			if (other.allowResend != null) {
				diff = String.format("BAD allowResend: EXPECTED null, FOUND %s", other.allowResend);
				return false;
			}
		} else if (!allowResend.equals(other.allowResend)) {
			diff = String.format("BAD allowResend: EXPECTED %s, FOUND %s", this.allowResend, other.allowResend);
			return false;
		}
		if (channel == null) {
			if (other.channel != null) {
				diff = String.format("BAD channel: EXPECTED null, FOUND %s", other.channel);
				return false;
			}
		} else if (!channel.equals(other.channel)) {
			diff = String.format("BAD channel: EXPECTED %s, FOUND %s", this.channel, other.channel);
			return false;
		}

		if (contactId == null) {
			if (other.contactId != null) {
				diff = String.format("BAD contactId: EXPECTED null, FOUND %s", other.contactId);
				return false;
			}
		} else if (!contactId.equals(other.contactId)) {
			diff = String.format("BAD contactId: EXPECTED %s, FOUND %s", this.contactId, other.contactId);
			return false;
		}
		if (contactName == null) {
			if (other.contactName != null) {
				diff = String.format("BAD contactName: EXPECTED null, FOUND %s", other.contactName);
				return false;
			}
		} else if (!contactName.equals(other.contactName)) {
			diff = String.format("BAD contactName: EXPECTED %s, FOUND %s", this.contactName, other.contactName);
			return false;
		}

		if (createDateTime == null) {
			if (other.createDateTime != null) {
				diff = String.format("BAD createDateTime: EXPECTED null, FOUND %s", other.createDateTime);
				return false;
			}
		} else if (!createDateTime.equals(other.createDateTime)) {
			diff = String.format("BAD createDateTime: EXPECTED %s, FOUND %s", this.createDateTime,
					other.createDateTime);
			return false;
		}

		if (description == null) {
			if (other.description != null) {
				diff = String.format("BAD description: EXPECTED null, FOUND %s", other.description);
				return false;
			}
		} else if (!description.equals(other.description)) {
			diff = String.format("BAD description: EXPECTED %s, FOUND %s", this.description, other.description);
			return false;
		}

		if (notificationId == null) {
			if (other.notificationId != null) {
				diff = String.format("BAD notificationId: EXPECTED null, FOUND %s", other.notificationId);
				return false;
			}
		} else if (!notificationId.equals(other.notificationId)) {
			diff = String.format("BAD notificationId: EXPECTED %s, FOUND %s", this.notificationId,
					other.notificationId);
			return false;
		}

		if (notificationReference == null) {
			if (other.notificationReference != null) {
				diff = String.format("BAD notificationReference: EXPECTED null, FOUND %s",
						other.notificationReference);
				return false;
			}
		} else if (!notificationReference.equals(other.notificationReference)) {
			diff = String.format("BAD notificationReference: EXPECTED %s, FOUND %s", this.notificationReference,
					other.notificationReference);
			return false;
		}

		if (recipient == null) {
			if (other.recipient != null) {
				diff = String.format("BAD recipient: EXPECTED null, FOUND %s", other.recipient);
				return false;
			}
		} else if (!recipient.equals(other.recipient)) {
			diff = String.format("BAD recipient: EXPECTED %s, FOUND %s", this.recipient, other.recipient);
			return false;
		}

		if (sendDateTime == null) {
			if (other.sendDateTime != null) {
				diff = String.format("BAD sendDateTime: EXPECTED null, FOUND %s", other.sendDateTime);
				return false;
			}
		} else if (!sendDateTime.equals(other.sendDateTime)) {
			diff = String.format("BAD sendDateTime: EXPECTED %s, FOUND %s", this.sendDateTime, other.sendDateTime);
			return false;
		}

		if (status == null) {
			if (other.status != null) {
				diff = String.format("BAD status: EXPECTED null, FOUND %s", other.status);
				return false;
			}
		} else if (!status.equals(other.status)) {
			diff = String.format("BAD status: EXPECTED %s, FOUND %s", this.status, other.status);
			return false;
		}

		if (subject == null) {
			if (other.subject != null) {
				diff = String.format("BAD subject: EXPECTED null, FOUND %s", other.subject);
				return false;
			}
		} else if (!subject.equals(other.subject)) {
			diff = String.format("BAD subject: EXPECTED %s, FOUND %s", this.subject, other.subject);
			return false;
		}

		if (type == null) {
			if (other.type != null) {
				diff = String.format("BAD type: EXPECTED null, FOUND %s", other.type);
				return false;
			}
		} else if (!type.equals(other.type)) {
			diff = String.format("BAD type: EXPECTED %s, FOUND %s", this.type, other.type);
			return false;
		}

		return true;
	}

}

