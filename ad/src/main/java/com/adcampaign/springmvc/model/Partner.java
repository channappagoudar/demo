package com.adcampaign.springmvc.model;

public class Partner {

	private String partner_id;
	
	private long duration;
	
	private String ad_content;
	
	
	public Partner(){
		partner_id="";
	}
	
	public Partner( String partner_id,long duration, String ad_content){
		this.partner_id = partner_id;
		this.duration = duration;
		this.ad_content = ad_content;
	}

	public String getpartner_id() {
		return partner_id;
	}

	public void setpartner_id(String partner_id) {
		this.partner_id = partner_id;
	}

	public long getduration() {
		return duration;
	}

	public void setduration(long duration) {
		this.duration = duration;
	}

	public String getad_content() {
		return ad_content;
	}

	public void setad_content(String ad_content) {
		this.ad_content = ad_content;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (duration ^ (duration >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Partner))
			return false;
		Partner other = (Partner) obj;
		if (partner_id != other.partner_id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Partner [partner_id=" + partner_id + ", duration=" + duration + ", ad_content=" + ad_content+"]";
	}
	

	
}
