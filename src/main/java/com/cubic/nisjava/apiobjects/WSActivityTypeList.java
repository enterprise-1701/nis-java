package com.cubic.nisjava.apiobjects;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSActivityTypeList {
	@SerializedName("activityTypes")
	@Expose
	private List<WSActivityType> activityTypes = null;

	public List<WSActivityType> getActivityTypes() {
		return activityTypes;
	}

	public void setActivityTypes(List<WSActivityType> activityTypes) {
		this.activityTypes = activityTypes;
	}
	
	/**
	 * Method to determine of the Activity List contains the given key and value pair.
	 * 
	 * @param key  The key to search for
	 * @param value  The associated value to search for.
	 * @return  True if the list contains the given key/value pair.
	 */
	public boolean contains( String key, String value ) {
		boolean result = false;
		for ( WSActivityType activityType : this.activityTypes ) {
			String k = activityType.getKey();
			String v = activityType.getValue();
			
			result = k.equals( key ) && v.equals( value );
			if ( result == true ) {
				return result;
			}
		}
		return result;
	}
	
	/**
	 * Method to remove the given key from the Activity Type list.
	 * 
	 * @param key  The key to search for
	 */
	public void remove( String key ) {
		for ( WSActivityType activityType : this.activityTypes ) {
			String k = activityType.getKey();
			if ( k.equals( key ) == true ) {
				this.activityTypes.remove( activityType );
				break;
			}
		}
	}
}
