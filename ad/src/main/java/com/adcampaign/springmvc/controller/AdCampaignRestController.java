package com.adcampaign.springmvc.controller;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.adcampaign.springmvc.model.Partner;
import com.adcampaign.springmvc.service.PartnerService;
 
@RestController
public class AdCampaignRestController {
 
    @Autowired
    PartnerService partnerService;  //Service which will do all data retrieval/manipulation work
 
    
    //-------------------Retrieve All Partners--------------------------------------------------------
     
    @RequestMapping(value = "/partner/", method = RequestMethod.GET)
    public ResponseEntity<List<Partner>> listAllPartners() {
        List<Partner> partners = partnerService.findallpartners();
        if(partners.isEmpty()){
            return new ResponseEntity<List<Partner>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Partner>>(partners, HttpStatus.OK);
    }
 
 
    
    //-------------------Retrieve Single Partner--------------------------------------------------------
     
    @RequestMapping(value = "/partner/{partner_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Partner> getPartner(@PathVariable("partner_id") String partner_id) {
        System.out.println("Fetching partner with id " + partner_id);
        Partner partner = partnerService.findbypartner_id(partner_id);
        if (partner == null) {
            System.out.println("Partner with id " + partner_id + " not found");
            return new ResponseEntity<Partner>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Partner>(partner, HttpStatus.OK);
    }
 
     
     
    //-------------------Create a partner--------------------------------------------------------
     
    @RequestMapping(value = "/partner/", method = RequestMethod.POST)
    public ResponseEntity<Void> createPartner(@RequestBody Partner partner,    UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Partner " + partner.getpartner_id());
 
        if (partnerService.ispartnerexist(partner)) {
            System.out.println("A Partner with name " + partner.getpartner_id() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
 
        partnerService.savepartner(partner);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/partner/{partner_id}").buildAndExpand(partner.getpartner_id()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
 
    
     
    //------------------- Update a Partner --------------------------------------------------------
     
    @RequestMapping(value = "/partner/{partner_id}", method = RequestMethod.PUT)
    public ResponseEntity<Partner> updatePartner(@PathVariable("partner_id") String partner_id, @RequestBody Partner partner) {
        System.out.println("Updating Partner " + partner_id);
         
        Partner currentPartner = partnerService.findbypartner_id(partner_id);
         
        if (currentPartner==null) {
            System.out.println("Partner with id " + partner_id + " not found");
            return new ResponseEntity<Partner>(HttpStatus.NOT_FOUND);
        }
 
        currentPartner.setpartner_id(partner.getpartner_id());
        currentPartner.setduration(partner.getduration());
        currentPartner.setad_content(partner.getad_content());
         
        partnerService.updatepartner(currentPartner);
        return new ResponseEntity<Partner>(currentPartner, HttpStatus.OK);
    }
 
    
    
    //------------------- Delete a Partner --------------------------------------------------------
     
    @RequestMapping(value = "/partner/{partner_id}", method = RequestMethod.DELETE)
    public ResponseEntity<Partner> deletePartner(@PathVariable("partner_id") String partner_id) {
        System.out.println("Fetching & Deleting Partner with id " + partner_id);
 
        Partner partner = partnerService.findbypartner_id(partner_id);
        if (partner == null) {
            System.out.println("Unable to delete. Partner with id " + partner_id + " not found");
            return new ResponseEntity<Partner>(HttpStatus.NOT_FOUND);
        }
 
        partnerService.deletebypartner_id(partner_id);
        return new ResponseEntity<Partner>(HttpStatus.NO_CONTENT);
    }
 
     
    
    //------------------- Delete All Partners --------------------------------------------------------
     
    @RequestMapping(value = "/partner/", method = RequestMethod.DELETE)
    public ResponseEntity<Partner> deleteAllPartners() {
        System.out.println("Deleting All Partners");
 
        partnerService.deleteallpartners();
        return new ResponseEntity<Partner>(HttpStatus.NO_CONTENT);
    }
 
}