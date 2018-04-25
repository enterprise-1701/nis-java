package com.cubic.nisjava.apiobjects;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSCXSInfo {

	@SerializedName("eulaId")
	@Expose
	private Integer eulaId;
	@SerializedName("eulaType")
	@Expose
	private String eulaType;
	@SerializedName("name")
	@Expose
	private String name;
	@SerializedName("noticeDate")
	@Expose
	private String noticeDate;
	@SerializedName("effectiveDate")
	@Expose
	private String effectiveDate;
	@SerializedName("publishDate")
	@Expose
	private String publishDate;
	@SerializedName("explicit")
	@Expose
	private Boolean explicit;
	@SerializedName("feature")
	@Expose
	private String feature;
	@SerializedName("channel")
	@Expose
	private String channel;
	@SerializedName("userType")
	@Expose
	private String userType;
	@SerializedName("status")
	@Expose
	private String status;
	@SerializedName("archive")
	@Expose
	private Boolean archive;
	@SerializedName("authorId")
	@Expose
	private String authorId;
	@SerializedName("localeInfo")
	@Expose
	private List<Object> localeInfo = null;
	@SerializedName("featureDescription")
	@Expose
	private String featureDescription;
	@SerializedName("channelDescription")
	@Expose
	private String channelDescription;
	@SerializedName("userTypeDescription")
	@Expose
	private String userTypeDescription;
	@SerializedName("eulaTypeDescription")
	@Expose
	private String eulaTypeDescription;
	@SerializedName("createDtm")
	@Expose
	private String createDtm;
	@SerializedName("publisherId")
	@Expose
	private String publisherId;

	public Integer getEulaId() {
		return eulaId;
	}

	public void setEulaId(Integer eulaId) {
		this.eulaId = eulaId;
	}

	public String getEulaType() {
		return eulaType;
	}

	public void setEulaType(String eulaType) {
		this.eulaType = eulaType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNoticeDate() {
		return noticeDate;
	}

	public void setNoticeDate(String noticeDate) {
		this.noticeDate = noticeDate;
	}

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public Boolean getExplicit() {
		return explicit;
	}

	public void setExplicit(Boolean explicit) {
		this.explicit = explicit;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Boolean getArchive() {
		return archive;
	}

	public void setArchive(Boolean archive) {
		this.archive = archive;
	}

	public String getAuthorId() {
		return authorId;
	}

	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}

	public List<Object> getLocaleInfo() {
		return localeInfo;
	}

	public void setLocaleInfo(List<Object> localeInfo) {
		this.localeInfo = localeInfo;
	}

	public String getFeatureDescription() {
		return featureDescription;
	}

	public void setFeatureDescription(String featureDescription) {
		this.featureDescription = featureDescription;
	}

	public String getChannelDescription() {
		return channelDescription;
	}

	public void setChannelDescription(String channelDescription) {
		this.channelDescription = channelDescription;
	}

	public String getUserTypeDescription() {
		return userTypeDescription;
	}

	public void setUserTypeDescription(String userTypeDescription) {
		this.userTypeDescription = userTypeDescription;
	}

	public String getEulaTypeDescription() {
		return eulaTypeDescription;
	}

	public void setEulaTypeDescription(String eulaTypeDescription) {
		this.eulaTypeDescription = eulaTypeDescription;
	}

	public String getCreateDtm() {
		return createDtm;
	}

	public void setCreateDtm(String createDtm) {
		this.createDtm = createDtm;
	}

	public String getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(String publisherId) {
		this.publisherId = publisherId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((archive == null) ? 0 : archive.hashCode());
		result = prime * result + ((authorId == null) ? 0 : authorId.hashCode());
		result = prime * result + ((channel == null) ? 0 : channel.hashCode());
		result = prime * result + ((channelDescription == null) ? 0 : channelDescription.hashCode());
		result = prime * result + ((createDtm == null) ? 0 : createDtm.hashCode());
		result = prime * result + ((effectiveDate == null) ? 0 : effectiveDate.hashCode());
		result = prime * result + ((eulaId == null) ? 0 : eulaId.hashCode());
		result = prime * result + ((eulaType == null) ? 0 : eulaType.hashCode());
		result = prime * result + ((eulaTypeDescription == null) ? 0 : eulaTypeDescription.hashCode());
		result = prime * result + ((explicit == null) ? 0 : explicit.hashCode());
		result = prime * result + ((feature == null) ? 0 : feature.hashCode());
		result = prime * result + ((featureDescription == null) ? 0 : featureDescription.hashCode());
		result = prime * result + ((localeInfo == null) ? 0 : localeInfo.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((noticeDate == null) ? 0 : noticeDate.hashCode());
		result = prime * result + ((publishDate == null) ? 0 : publishDate.hashCode());
		result = prime * result + ((publisherId == null) ? 0 : publisherId.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((userType == null) ? 0 : userType.hashCode());
		result = prime * result + ((userTypeDescription == null) ? 0 : userTypeDescription.hashCode());
		return result;
	}

	private static String errorMessage;
	
	public static String getErrorMessage() {
		return errorMessage;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
		    errorMessage = "this == that";
			return true;
		}
		if (obj == null) {
			errorMessage = "obj == null";
			return false;
		}
		if (getClass() != obj.getClass()) {
			errorMessage = String.format( "Classes are different: %s vs. %s", getClass().getName(), obj.getClass().getName() );
			return false;
		}
		WSCXSInfo other = (WSCXSInfo) obj;
		if (archive == null) {
			if (other.archive != null) {
				errorMessage = String.format( "Archive %s != %s", archive, other.archive );
				return false;
			}
		} else if (!archive.equals(other.archive)) {
			errorMessage = String.format( "Archive %s != %s", archive, other.archive );
			return false;
		}
		
		if (authorId == null) {
			if (other.authorId != null) {
				errorMessage = String.format( "authorId %s != %s", authorId, other.authorId );
				return false;
			}
		} else if (!authorId.equals(other.authorId)) {
			errorMessage = String.format( "authorId %s != %s", authorId, other.authorId );
			return false;
		}
		
		if (channel == null) {
			if (other.channel != null) {
				errorMessage = String.format( "channel %s != %s", channel, other.channel );
				return false;
			}
		} else if (!channel.equals(other.channel)) {
			errorMessage = String.format( "channel %s != %s", channel, other.channel );
			return false;
		}
		if (channelDescription == null) {
			if (other.channelDescription != null) {
				errorMessage = String.format( "channelDescription %s != %s", channelDescription, other.channelDescription );
				return false;
			}
		} else if (!channelDescription.equals(other.channelDescription)) {
			errorMessage = String.format( "channelDescription %s != %s", channelDescription, other.channelDescription );
			return false;
		}
		if (createDtm == null) {
			if (other.createDtm != null) {
				errorMessage = String.format( "createDtm %s != %s", createDtm, other.createDtm );
				return false;
			}
		} else if (!createDtm.equals(other.createDtm)) {
			errorMessage = String.format( "createDtm %s != %s", createDtm, other.createDtm );
			return false;
		}
		
		if (effectiveDate == null) {
			if (other.effectiveDate != null) {
				errorMessage = String.format( "effectiveDate %s != %s", effectiveDate, other.effectiveDate );
				return false;
			}
		} else if (!effectiveDate.equals(other.effectiveDate)) {
			errorMessage = String.format( "effectiveDate %s != %s", effectiveDate, other.effectiveDate );
			return false;
		}
		if (eulaId == null) {
			if (other.eulaId != null) {
				errorMessage = String.format( "eulaId %s != %s", eulaId, other.eulaId );
				return false;
			}
		} else if (!eulaId.equals(other.eulaId)) {
			errorMessage = String.format( "eulaId %s != %s", eulaId, other.eulaId );
			return false;
		}
		if (eulaType == null) {
			if (other.eulaType != null) {
				errorMessage = String.format( "eulaType %s != %s", eulaType, other.eulaType );
				return false;
			}
		} else if (!eulaType.equals(other.eulaType)) {
			errorMessage = String.format( "eulaType %s != %s", eulaType, other.eulaType );
			return false;
		}
		if (eulaTypeDescription == null) {
			if (other.eulaTypeDescription != null) {
				errorMessage = String.format( "eulaTypeDescription %s != %s", eulaTypeDescription, other.eulaTypeDescription );
				return false;
			}
		} else if (!eulaTypeDescription.equals(other.eulaTypeDescription)) {
			errorMessage = String.format( "eulaTypeDescription %s != %s", eulaTypeDescription, other.eulaTypeDescription );
			return false;
		}
		if (explicit == null) {
			if (other.explicit != null) {
				errorMessage = String.format( "explicit %s != %s", explicit, other.explicit );
				return false;
			}
		} else if (!explicit.equals(other.explicit)) {
			errorMessage = String.format( "explicit %s != %s", explicit, other.explicit );
			return false;
		}
		
		if (feature == null) {
			if (other.feature != null) {
				errorMessage = String.format( "feature %s != %s", feature, other.feature );
				return false;
			}
		} else if (!feature.equals(other.feature)) {
			errorMessage = String.format( "feature %s != %s", feature, other.feature );
			return false;
		}
		
		if (featureDescription == null) {
			if (other.featureDescription != null) {
				errorMessage = String.format( "featureDescription %s != %s", featureDescription, other.featureDescription );
				return false;
			}
		} else if (!featureDescription.equals(other.featureDescription)) {
			errorMessage = String.format( "featureDescription %s != %s", featureDescription, other.featureDescription );
			return false;
		}
		
		if (localeInfo == null) {
			if (other.localeInfo != null) {
				errorMessage = String.format( "this.localInfo is null but that.localeInfo is not null " );
				return false;
			}
		} else if (!localeInfo.equals(other.localeInfo)) {
			errorMessage = String.format( "this.localInfo != that.localeInfo" );
			return false;
		}
		
		if (name == null) {
			if (other.name != null) {
				errorMessage = String.format( "name %s != %s", name, other.name );
				return false;
			}
		} else if (!name.equals(other.name)) {
			errorMessage = String.format( "name %s != %s", name, other.name );
			return false;
		}
		if (noticeDate == null) {
			if (other.noticeDate != null) {
				errorMessage = String.format( "noticeDate %s != %s", noticeDate, other.noticeDate );
				return false;
			}
		} else if (!noticeDate.equals(other.noticeDate)) {
			errorMessage = String.format( "noticeDate %s != %s", noticeDate, other.noticeDate );
			return false;
		}
		
		if (publishDate == null) {
			if (other.publishDate != null) {
				errorMessage = String.format( "publishDate %s != %s", publishDate, other.publishDate );
				return false;
			}
		} else if (!publishDate.equals(other.publishDate)) {
			errorMessage = String.format( "publishDate %s != %s", publishDate, other.publishDate );
			return false;
		}
		
		if (publisherId == null) {
			if (other.publisherId != null) {
				errorMessage = String.format( "publisherId %s != %s", publisherId, other.publisherId );
				return false;
			}
		} else if (!publisherId.equals(other.publisherId)) {
			errorMessage = String.format( "publisherId %s != %s", publisherId, other.publisherId );
			return false;
		}
		
		if (status == null) {
			if (other.status != null) {
				errorMessage = String.format( "status %s != %s", status, other.status );
				return false;
			}
		} else if (!status.equals(other.status)) {
			errorMessage = String.format( "status %s != %s", status, other.status );
			return false;
		}
		
		if (userType == null) {
			if (other.userType != null) {
				errorMessage = String.format( "userType %s != %s", userType, other.userType );
				return false;
			}
		} else if (!userType.equals(other.userType)) {
			errorMessage = String.format( "userType %s != %s", userType, other.userType );
			return false;
		}
		
		if (userTypeDescription == null) {
			if (other.userTypeDescription != null) {
				errorMessage = String.format( "userTypeDescription %s != %s", userTypeDescription, other.userTypeDescription );
				return false;
			}
		} else if (!userTypeDescription.equals(other.userTypeDescription)) {
			errorMessage = String.format( "userTypeDescription %s != %s", userTypeDescription, other.userTypeDescription );
			return false;
		}
		
		return true;
	}

}
