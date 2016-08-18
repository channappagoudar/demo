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
								partner_id : '',
								duration : '',
								ad_content : ''
							};
							self.partners = [];

							self.submit = submit;
							self.edit = edit;
							self.remove = remove;
							self.reset = reset;

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
												fetchAllUsers,
												function(errResponse) {
													console
															.error('Error while deleting Partner');
												});
							}

							function submit() {
								//alert (self.partner.partner_id);
								console.log('Saving New Partner', self.partner);
								createPartner(self.partner);
								reset();
							}

							function edit(partner_id) {
								console.log('id to be edited', partner_id);
								for (var i = 0; i < self.partners.length; i++) {
									if (self.partners[i].partner_id === partner_id) {
										self.partner = angular
												.copy(self.partners[i]);
										break;
									}
								}
							}

							function remove(partner_id) {
								console.log('id to be deleted', partner_id);
								if (self.partner.partner_id === partner_id) {//clean form if the user to be deleted is shown there.
									reset();
								}
								deletePartner(partner_id);
							}

							function fetch(partner_id) {
								console.log('id to be fetched', partner_id);
								if (self.partner.partner_id === partner_id) {//clean form if the user to be deleted is shown there.
									reset();
								}
								fetchPartner(partner_id);
							}
							
							function reset() {
								self.user = {
									partner_id : '',
									duration : '',
									ad_content : ''
								};
								$scope.myForm.$setPristine(); //reset Form
							}

						} ]);
