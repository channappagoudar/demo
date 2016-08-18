<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>AdCampaign</title>
<style type="text/css">
.partnername.ng-valid {
	background-color: lightgreen;
}

.partnername.ng-dirty.ng-invalid-required {
	background-color: red;
}

.partnername.ng-dirty.ng-invalid-minlength {
	background-color: yellow;
}
</style>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>
<body ng-app="myApp" class="ng-cloak">
	<div class="generic-container"
		ng-controller="AdCampaignController as ctrl">
		<div class="panel panel-default">
			<div class="panel-heading">
				<span class="lead">Partner Registration</span>
			</div>
			<div class="formcontainer">
				<form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal" action="">
				<!--<input type="hidden" ng-model="ctrl.partner.creation_time" />-->
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">Partner ID</label>
							<div class="col-md-7">
								<input type="text" ng-model="ctrl.partner.partner_id"
									name="partner_id" class="partner_id form-control input-sm"
									placeholder="[unique_string_representing_partner]" required
									ng-minlength="3" />
								<div class="has-error" ng-show="myForm.$dirty">
									<span ng-show="myForm.partner_id.$error.required">This
										is a required field</span> <span
										ng-show="myForm.partner_id.$error.minlength">Minimum
										length required is 3</span> <span
										ng-show="myForm.partner_id.$invalid">This field is
										invalid </span>
								</div>
							</div>
						</div>
					</div>


					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">Duration</label>
							<div class="col-md-7">
								<input type="number" ng-model="ctrl.partner.duration"
									class="form-control input-sm"
									placeholder="[int_representing_campaign_duration_in_seconds_from_now]"
									min="10" max="99" required />
								<div class="has-error" ng-show="myForm.$dirty">
									<span ng-show="myForm.duration.$error.required">This is
										a required field</span> <span ng-show="myForm.duration.$invalid">This
										field is invalid </span>
								</div>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">Ad
								Content</label>
							<div class="col-md-7">
								<input type="text" ng-model="ctrl.partner.ad_content"
									class="form-control input-sm"
									placeholder="[string_of_content_to_display_as_ad]" />
								<div class="has-error" ng-show="myForm.$dirty">
									<span ng-show="myForm.duration.$error.required">This is
										a required field</span> <span ng-show="myForm.duration.$invalid">This
										field is invalid </span>
								</div>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="form-actions floatRight">
							<input type="submit" value="Add" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
							<!-- <button type="button" ng-click="ctrl.reset()"
								class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset
								Form</button> -->
						</div>
					</div>
				</form>
			</div>
		</div>
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading">
				<span class="lead">Ad Postings</span>
			</div>
			<div class="tablecontainer">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Partner ID</th>
							<th>Duration</th>
							<th>Ad</th>
							<th width="20%"></th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="u in ctrl.partners">
							<td><span ng-bind="u.partner_id"></span></td>
							<td><span ng-bind="u.duration"></span></td>
							<td><span ng-bind="u.ad_content"></span></td>
							<td>
								 <button type="button" ng-click="ctrl.edit(u.partner_id)"
									class="btn btn-success custom-width">Fetch</button> 	
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"
		type="text/javascript"></script>
	<script src="<c:url value='/static/js/app.js' />"
		type="text/javascript"></script>
	<script src="<c:url value='/static/js/service/partner_service.js' />"
		type="text/javascript"></script>
	<script
		src="<c:url value='/static/js/controller/adcampaign_controller.js' />"
		type="text/javascript"></script>
</body>
</html>