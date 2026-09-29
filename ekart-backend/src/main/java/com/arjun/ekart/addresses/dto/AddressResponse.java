package com.arjun.ekart.addresses.dto;

import com.arjun.ekart.addresses.enums.AddressType;

public class AddressResponse {
	
		private Long id;
	
		private String fullName;

	    private String phoneNumber;

	    private String houseNo;

	    private String apartment;

	    private String street;

	    private String landmark;

	    private String city;

	    private String district;

	    private String state;

	    private String country;

	    private String pincode;

	    private AddressType addressType;

	    private boolean defaultAddress;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getFullName() {
			return fullName;
		}

		public void setFullName(String fullName) {
			this.fullName = fullName;
		}

		public String getPhoneNumber() {
			return phoneNumber;
		}

		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}

		public String getHouseNo() {
			return houseNo;
		}

		public void setHouseNo(String houseNo) {
			this.houseNo = houseNo;
		}

		public String getApartment() {
			return apartment;
		}

		public void setApartment(String apartment) {
			this.apartment = apartment;
		}

		public String getStreet() {
			return street;
		}

		public void setStreet(String street) {
			this.street = street;
		}

		public String getLandmark() {
			return landmark;
		}

		public void setLandmark(String landmark) {
			this.landmark = landmark;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getDistrict() {
			return district;
		}

		public void setDistrict(String district) {
			this.district = district;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}

		public String getPincode() {
			return pincode;
		}

		public void setPincode(String pincode) {
			this.pincode = pincode;
		}

		public AddressType getAddressType() {
			return addressType;
		}

		public void setAddressType(AddressType addressType) {
			this.addressType = addressType;
		}

		public boolean isDefaultAddress() {
			return defaultAddress;
		}

		public void setDefaultAddress(boolean defaultAddress) {
			this.defaultAddress = defaultAddress;
		}
	    
	    
}
