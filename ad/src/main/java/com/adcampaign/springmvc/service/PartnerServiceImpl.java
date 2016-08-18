package com.adcampaign.springmvc.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.adcampaign.springmvc.model.Partner;

@Service("partnerService")
public class PartnerServiceImpl implements PartnerService{
		
	//persistence mechanism
	private static List<Partner> partners;
	
	static{
		partners= populatepartners();
	}

	public List<Partner> findallpartners() {
		/*for(Partner partner : partners){
			Calendar expiration_date = (Calendar) partner.getcreation_time().clone();
			expiration_date.add(Calendar.MINUTE, partner.getduration());
			
			if(expiration_date.after(Calendar.getInstance())){
			deletebypartner_id(partner.getpartner_id());
			}
		}*/
		return partners;
	}
	
	public Partner findbypartner_id(String partner_id) {
		for(Partner partner : partners){
			//System.out.println("findbypartner_id checking -->"+partner.getpartner_id() +" against "+ partner_id +" result "+(partner.getpartner_id().equals(partner_id)));
			if(partner.getpartner_id().equals(partner_id)){
				return partner;
			}
		}
		return null;
	}
	
	public void savepartner(Partner partner) {
		partner.setcreation_time(Calendar.getInstance());
		partners.add(partner);
	}

	public void updatepartner(Partner partner) {
		int index = partners.indexOf(partner);
		partners.set(index, partner);
	}

	public void deletebypartner_id(String partner_id) {
		
		for (Iterator<Partner> iterator = partners.iterator(); iterator.hasNext(); ) {
		    Partner partner = iterator.next();
		    if (partner.getpartner_id() == partner_id) {
		        iterator.remove();
		    }
		}
	}

	public boolean ispartnerexist(Partner partner) {
		return findbypartner_id(partner.getpartner_id())!=null;
	}
	
	public void deleteallpartners(){
		partners.clear();
	}

	private static List<Partner> populatepartners(){
		List<Partner> partners = new ArrayList<Partner>();
		partners.add(new Partner("WalMart",99,"Low prices always"));
		partners.add(new Partner("Target",99,"Best prices always"));
		partners.add(new Partner("Sears",99,"Price match"));
		return partners;
	}


}
