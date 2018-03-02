package com.cubic.nisjava.apiobjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSCatalogContainer {

	@SerializedName("hdr")
	@Expose
	private WSHdr hdr;
	@SerializedName("result")
	@Expose
	private WSResult result;
	@SerializedName("catalog")
	@Expose
	private WSCatalog catalog;

	public WSHdr getHdr() {
		return hdr;
	}

	public void setHdr(WSHdr hdr) {
		this.hdr = hdr;
	}

	public WSResult getResult() {
		return result;
	}

	public void setResult(WSResult result) {
		this.result = result;
	}

	public WSCatalog getCatalog() {
		return catalog;
	}

	public void setCatalog(WSCatalog catalog) {
		this.catalog = catalog;
	}

}
