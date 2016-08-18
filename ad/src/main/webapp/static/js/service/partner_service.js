'use strict';

angular.module('myApp').factory('PartnerService', ['$http', '$q', function($http, $q){

	var REST_SERVICE_URI = 'http://localhost:8080/ad/partner/';

	var factory = {
			fetchAllPartners: fetchAllPartners,
			fetchPartner: fetchPartner,
			createPartner: createPartner,
			updatePartner:updatePartner,
			deletePartner:deletePartner
	};

	return factory;
	
	function fetchPartner(partner_id) {
		var deferred = $q.defer();
		$http.get(REST_SERVICE_URI+partner_id)
		.then(
				function (response) {
					deferred.resolve(response.data);
				},
				function(errResponse){
					console.error('Error while fetching Partners');
					deferred.reject(errResponse);
				}
		);
		return deferred.promise;
	}
	
	function fetchAllPartners() {
		var deferred = $q.defer();
		$http.get(REST_SERVICE_URI)
		.then(
				function (response) {
					deferred.resolve(response.data);
				},
				function(errResponse){
					console.error('Error while fetching Partners');
					deferred.reject(errResponse);
				}
		);
		return deferred.promise;
	}

	function createPartner(partner) {
		/*
		alert(partner.partner_id);
		alert(partner.duration);
		alert(partner.ad_content);
		*/
		var deferred = $q.defer();
		$http.post(REST_SERVICE_URI, partner)
		.then(
				function (response) {
					deferred.resolve(response.data);
				},
				function(errResponse){
					console.error('Error while creating Partner');
					deferred.reject(errResponse);
				}
		);
		return deferred.promise;
	}


	function updatePartner(partner, partner_id) {
		var deferred = $q.defer();
		$http.put(REST_SERVICE_URI+partner_id, partner)
		.then(
				function (response) {
					deferred.resolve(response.data);
				},
				function(errResponse){
					console.error('Error while updating partner');
					deferred.reject(errResponse);
				}
		);
		return deferred.promise;
	}

	function deletePartner(partner_id) {
		var deferred = $q.defer();
		$http.delete(REST_SERVICE_URI+partner_id)
		.then(
				function (response) {
					deferred.resolve(response.data);
				},
				function(errResponse){
					console.error('Error while deleting Partner');
					deferred.reject(errResponse);
				}
		);
		return deferred.promise;
	}

}]);
