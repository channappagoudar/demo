'use strict';

angular
.module('myApp')
.controller(
		'AdCampaignController',
		[
		 '$scope',
		 'PartnerService',
		 function($scope, PartnerService) {
			 var self = this;
			 self.partner = {
					 creation_time : null,
					 partner_id : '',
					 duration : '',
					 ad_content : ''
			 };
			 self.partners = [];

			 self.submit = submit;
			 self.edit = edit;
			 // self.fetch = fetch;
			 // self.remove = remove;
			 // self.reset = reset;

			 fetchAllPartners();

			 function fetchAllPartners() {
				 PartnerService
				 .fetchAllPartners()
				 .then(
						 function(d) {
							 self.partners = d;
						 },
						 function(errResponse) {
							 console
							 .error('Error while fetching Partners');
						 });
			 }

			 function createPartner(partner) {
				 PartnerService
				 .createPartner(partner)
				 .then(
						 fetchAllPartners,
						 function(errResponse) {
							 //alert("Partner already exists");
							 console
							 .error('Error while creating Partner');
						 });
			 }

			 function updatePartner(partner, partner_id) {
				 PartnerService
				 .updatePartner(partner, partner_id)
				 .then(
						 fetchAllPartners,
						 function(errResponse) {
							 console
							 .error('Error while updating Partner');
						 });
			 }

			 function deletePartner(partner_id) {
				 PartnerService
				 .deletePartner(partner_id)
				 .then(
						 fetchAllPartners,
						 function(errResponse) {
							 console
							 .error('Error while deleting Partner');
						 });
			 }
			 
			 function fetchPartner(partner_id) {
				 PartnerService
				 .fetchPartner(partner_id)
				 .then(
						 function(errResponse) {
							 console
							 .error('Error while deleting Partner');
						 });
			 }

			 function submit() {
				 if (self.partner.creation_time === null) {
					 console.log('Saving New Partner',
							 self.partner);
					// alert('Create');

					 createPartner(self.partner);
				 } else {
					 //alert('Update');
					 updatePartner(self.partner,
							 self.partner.partner_id);
					 console.log('Partner updated with id ',
							 self.partner.partner_id);

					 reset();
				 }
			 }

			 function edit(partner_id) {
				 console.log('id to be edited', partner_id);
				 
				
				 
				 for (var i = 0; i < self.partners.length; i++) {
					 if (self.partners[i].partner_id === partner_id) {
						 
						 var time = new Date().getTime();
						 var expiration = self.partners[i].creation_time+self.partners[i].duration;
						 alert("Now "+time+" Creation time : "+self.partners[i].creation_time+" Expiration "+expiration+" "+(expiration>time));
						 if (expiration > time)
							 {
							 	alert ("Your Ad Posting has Expired");
							 	remove(partner_id);
							 	
							 }
						 else
							 self.partner = angular.copy(self.partners[i]);
						 break;
					 }
				 }
			 }

			 function remove(partner_id) {
				 console.log('id to be deleted', partner_id);
				 if (self.partner.partner_id === partner_id) {

					 reset();
				 }
				 deletePartner(partner_id);
			 }

			 function reset() {
				 self.partner = {
						 creation_time : null,
						 partner_id : '',
						 duration : '',
						 ad_content : ''
				 };
				 $scope.myForm.$setPristine(); //reset Form
			 }

		 } ]);
