package com.adcampaign.springmvc.service;

import java.util.List;

import com.adcampaign.springmvc.model.Partner;



public interface PartnerService {
	
	Partner findbypartner_id(String partner_id);
	
	void savepartner(Partner partner);
	
	void updatepartner(Partner partner);
	
	void deletebypartner_id(String partner_id);

	List<Partner> findallpartners(); 
	
	void deleteallpartners();
	
	public boolean ispartnerexist(Partner partner);
	
}
