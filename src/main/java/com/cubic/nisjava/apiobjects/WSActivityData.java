package com.cubic.nisjava.apiobjects;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSActivityData {

	@SerializedName("content")
	@Expose
	private List<WSContent> content = null;

	private static String diff;
	
	public static String getDiff() {
		return diff;
	}

	public List<WSContent> getContent() {
		return content;
	}

	public void setContent(List<WSContent> content) {
		this.content = content;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WSActivityData other = (WSActivityData) obj;
		if (content == null) {
			if (other.content != null) {
				int i = 0;
				for ( WSContent thisContent : this.content ) {
					WSContent thatContent = other.content.get(i++);
					boolean bEquals = thisContent.equals(thatContent);
					if ( !bEquals ) {
						diff = WSContent.getDiff();
						break;
					}
				}
				return false;
			}
		} else if (!content.equals(other.content)) {
			int i = 0;
			for ( WSContent thisContent : this.content ) {
				WSContent thatContent = other.content.get(i++);
				boolean bEquals = thisContent.equals(thatContent);
				if ( !bEquals ) {
					diff = WSContent.getDiff();
					break;
				}
			}
			return false;
		}
		return true;
	}

}
