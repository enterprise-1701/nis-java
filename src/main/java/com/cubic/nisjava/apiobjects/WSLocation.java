package com.cubic.nisjava.apiobjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSLocation {

	@SerializedName("operator")
	@Expose
	private String operator;
	@SerializedName("operatorName")
	@Expose
	private String operatorName;
	@SerializedName("stopPoint")
	@Expose
	private String stopPoint;
	@SerializedName("stopPointDesc")
	@Expose
	private String stopPointDesc;
	@SerializedName("routeNumber")
	@Expose
	private String routeNumber;
	@SerializedName("routeDesc")
	@Expose
	private String routeDesc;
	@SerializedName("zone")
	@Expose
	private String zone;
	@SerializedName("zoneDesc")
	@Expose
	private String zoneDesc;
	@SerializedName("direction")
	@Expose
	private String direction;
	@SerializedName("directionDesc")
	@Expose
	private String directionDesc;
	@SerializedName("sector")
	@Expose
	private String sector;
	@SerializedName("sectorDesc")
	@Expose
	private String sectorDesc;
	@SerializedName("serviceType")
	@Expose
	private String serviceType;
	@SerializedName("serviceTypeDesc")
	@Expose
	private String serviceTypeDesc;

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getStopPoint() {
		return stopPoint;
	}

	public void setStopPoint(String stopPoint) {
		this.stopPoint = stopPoint;
	}

	public String getStopPointDesc() {
		return stopPointDesc;
	}

	public void setStopPointDesc(String stopPointDesc) {
		this.stopPointDesc = stopPointDesc;
	}

	public String getRouteNumber() {
		return routeNumber;
	}

	public void setRouteNumber(String routeNumber) {
		this.routeNumber = routeNumber;
	}

	public String getRouteDesc() {
		return routeDesc;
	}

	public void setRouteDesc(String routeDesc) {
		this.routeDesc = routeDesc;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public String getZoneDesc() {
		return zoneDesc;
	}

	public void setZoneDesc(String zoneDesc) {
		this.zoneDesc = zoneDesc;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getDirectionDesc() {
		return directionDesc;
	}

	public void setDirectionDesc(String directionDesc) {
		this.directionDesc = directionDesc;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getSectorDesc() {
		return sectorDesc;
	}

	public void setSectorDesc(String sectorDesc) {
		this.sectorDesc = sectorDesc;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getServiceTypeDesc() {
		return serviceTypeDesc;
	}

	public void setServiceTypeDesc(String serviceTypeDesc) {
		this.serviceTypeDesc = serviceTypeDesc;
	}

	private static String errorMessage;

	public static String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * <p>
	 * Override the hashCode method to preserve the contract between the
	 * hashCode method and the equals method, i.e., "If two objects are 
	 * equal according to the equals(Object) method, then calling the 
	 * hashCode method on each of the two objects must produce the same
	 * integer result."  
	 * </p><p>
	 * Therefore, since we want to make the equals method compare two
	 * objects in terms of the member variables, such as 'direction',
	 * we have to make the hashCode work in the same way.
	 * </p>
	 * 
	 *  @return The hashCode of the object.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((direction == null) ? 0 : direction.hashCode());
		result = prime * result + ((directionDesc == null) ? 0 : directionDesc.hashCode());
		result = prime * result + ((operator == null) ? 0 : operator.hashCode());
		result = prime * result + ((operatorName == null) ? 0 : operatorName.hashCode());
		result = prime * result + ((routeDesc == null) ? 0 : routeDesc.hashCode());
		result = prime * result + ((routeNumber == null) ? 0 : routeNumber.hashCode());
		result = prime * result + ((sector == null) ? 0 : sector.hashCode());
		result = prime * result + ((sectorDesc == null) ? 0 : sectorDesc.hashCode());
		result = prime * result + ((serviceType == null) ? 0 : serviceType.hashCode());
		result = prime * result + ((serviceTypeDesc == null) ? 0 : serviceTypeDesc.hashCode());
		result = prime * result + ((stopPoint == null) ? 0 : stopPoint.hashCode());
		result = prime * result + ((stopPointDesc == null) ? 0 : stopPointDesc.hashCode());
		result = prime * result + ((zone == null) ? 0 : zone.hashCode());
		result = prime * result + ((zoneDesc == null) ? 0 : zoneDesc.hashCode());
		return result;
	}

	/**
	 * Compare two objects in terms of the member variables, 
	 * such as 'direction'.
	 * 
	 * @return True if the two objects are of the same type and have the same member variable values, else false.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null) {
			errorMessage = "obj == null";
			return false;
		}
		if (getClass() != obj.getClass()) {
			errorMessage = String.format( "Classes are different: %s vs. %s", getClass().getName(), obj.getClass().getName() );
			return false;
		}
		WSLocation other = (WSLocation) obj;
		if (direction == null) {
			if (other.direction != null) {
				errorMessage = String.format( "Direction %s != %s", direction, other.direction );
				return false;
			}
		} else if (!direction.equals(other.direction)) {
			errorMessage = String.format( "Direction %s != %s", direction, other.direction );
			return false;
		}
		if (directionDesc == null) {
			if (other.directionDesc != null) {
				errorMessage = String.format( "Direction Desc %s != %s", directionDesc, other.directionDesc );
				return false;
			}
		} else if (!directionDesc.equals(other.directionDesc)) {
			errorMessage = String.format( "Direction Desc %s != %s", directionDesc, other.directionDesc );
			return false;
		}
		if (operator == null) {
			if (other.operator != null) {
				errorMessage = String.format( "Operator %s != %s", operator, other.operator );
				return false;
			}
		} else if (!operator.equals(other.operator)) {
			errorMessage = String.format( "Operator %s != %s", operator, other.operator );
			return false;
		}
		if (operatorName == null) {
			if (other.operatorName != null) {
				errorMessage = String.format( "Operator Name %s != %s", operatorName, other.operatorName );
				return false;
			}
		} else if (!operatorName.equals(other.operatorName)) {
			errorMessage = String.format( "Operator Name %s != %s", operatorName, other.operatorName );
			return false;
		}
		if (routeDesc == null) {
			if (other.routeDesc != null) {
				errorMessage = String.format( "Route Desc %s != %s", routeDesc, other.routeDesc );
				return false;
			}
		} else if (!routeDesc.equals(other.routeDesc)) {
			errorMessage = String.format( "Route Desc %s != %s", routeDesc, other.routeDesc );
			return false;
		}
		if (routeNumber == null) {
			if (other.routeNumber != null) {
				errorMessage = String.format( "Route Number %s != %s", routeNumber, other.routeNumber );
				return false;
			}
		} else if (!routeNumber.equals(other.routeNumber)) {
			errorMessage = String.format( "Route Number %s != %s", routeNumber, other.routeNumber );
			return false;
		}
		if (sector == null) {
			if (other.sector != null) {
				errorMessage = String.format( "Sector %s != %s", sector, other.sector );
				return false;
			}
		} else if (!sector.equals(other.sector)) {
			errorMessage = String.format( "Sector %s != %s", sector, other.sector );
			return false;
		}
		if (sectorDesc == null) {
			if (other.sectorDesc != null) {
				errorMessage = String.format( "Sector Desc %s != %s", sectorDesc, other.sectorDesc );
				return false;
			}
		} else if (!sectorDesc.equals(other.sectorDesc)) {
			errorMessage = String.format( "Sector Desc %s != %s", sectorDesc, other.sectorDesc );
			return false;
		}
		if (serviceType == null) {
			if (other.serviceType != null) {
				errorMessage = String.format( "Service Type %s != %s", serviceType, other.serviceType );
				return false;
			}
		} else if (!serviceType.equals(other.serviceType)) {
			errorMessage = String.format( "Service Type %s != %s", serviceType, other.serviceType );
			return false;
		}
		if (serviceTypeDesc == null) {
			if (other.serviceTypeDesc != null) {
				errorMessage = String.format( "Service Type Desc %s != %s", serviceTypeDesc, other.serviceTypeDesc );
				return false;
			}
		} else if (!serviceTypeDesc.equals(other.serviceTypeDesc)) {
			errorMessage = String.format( "Service Type Desc %s != %s", serviceTypeDesc, other.serviceTypeDesc );
			return false;
		}
		if (stopPoint == null) {
			if (other.stopPoint != null) {
				errorMessage = String.format( "Stop Point %s != %s", stopPoint, other.stopPoint );
				return false;
			}
		} else if (!stopPoint.equals(other.stopPoint)) {
			errorMessage = String.format( "Stop Point %s != %s", stopPoint, other.stopPoint );
			return false;
		}
		if (stopPointDesc == null) {
			if (other.stopPointDesc != null) {
				errorMessage = String.format( "Stop Point Desc %s != %s", stopPointDesc, other.stopPointDesc );
				return false;
			}
		} else if (!stopPointDesc.equals(other.stopPointDesc)) {
			errorMessage = String.format( "Stop Point Desc %s != %s", stopPointDesc, other.stopPointDesc );
			return false;
		}
		if (zone == null) {
			if (other.zone != null) {
				errorMessage = String.format( "Zone %s != %s", zone, other.zone );
				return false;
			}
		} else if (!zone.equals(other.zone)) {
			errorMessage = String.format( "Zone %s != %s", zone, other.zone );
			return false;
		}
		if (zoneDesc == null) {
			if (other.zoneDesc != null) {
				errorMessage = String.format( "Zone Desc %s != %s", zoneDesc, other.zoneDesc );
				return false;
			}
		} else if (!zoneDesc.equals(other.zoneDesc)) {
			errorMessage = String.format( "Zone Desc %s != %s", zoneDesc, other.zoneDesc );
			return false;
		}
		return true;
	}
}