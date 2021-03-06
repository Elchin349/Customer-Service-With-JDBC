package com.company.dto.response;

public class AddressResponse {
    private int id;
    private String country;
    private String city;
    private String district;
    private String street;
    private String countryCode;
    private int customerId;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "AddressResponse{" +
                "id=" + id +
                ", country='" + country + "\n" +
                ", city='" + city + "\n" +
                ", district='" + district + "\n" +
                ", street='" + street + "\n" +
                ", countryCode='" + countryCode + "\n" +
                ", customerId=" + customerId +
                '}';
    }
}


