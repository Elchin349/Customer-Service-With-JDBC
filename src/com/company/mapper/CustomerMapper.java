package com.company.mapper;

import com.company.dto.request.AddressRequest;
import com.company.dto.request.ContactInfoRequest;
import com.company.dto.request.CustomerCreateRequest;
import com.company.dto.response.AddressResponse;
import com.company.dto.response.ContactInfoResponse;
import com.company.dto.response.CustomerDetailResponse;
import com.company.dto.response.CustomerDetailResponseList;
import com.company.entity.Address;
import com.company.entity.ContactInfo;
import com.company.entity.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CustomerMapper {
    public static Customer toEntity(CustomerCreateRequest ccr) {
        Customer customer = new Customer();
        customer.setFirstName(ccr.getFirstName());
        customer.setLastName(ccr.getLastName());
        customer.setFatherName(ccr.getFatherName());
        customer.setEmailAddress(ccr.getEmailAddress());
        customer.setFinCode(ccr.getFinCode());
        customer.setDocSerial(ccr.getDocSerial());
        customer.setBirthDate(ccr.getBirthDate());


        return customer;
    }

    public static Address toEntity(AddressRequest dto) {
        Address address = new Address();
        address.setCountry(dto.getCountry());
        address.setCity(dto.getCity());
        address.setCountryCode(dto.getCountryCode());
        address.setStreet(dto.getStreet());
        address.setDistrict(dto.getDistrict());

        return address;
    }

    public static ContactInfo toEntity(ContactInfoRequest dto) {
        ContactInfo cInfo = new ContactInfo();
        cInfo.setHomeNumber(dto.getHomeNumber());
        cInfo.setPhoneNumber1(dto.getPhoneNumber1());
        cInfo.setPhoneNumber2(dto.getPhoneNumber2());

        return cInfo;
    }


    public static CustomerDetailResponse customerResponse(Customer customer) {
        CustomerDetailResponse cr = new CustomerDetailResponse();
        cr.setFirstName(customer.getFirstName());
        cr.setId(customer.getId());
        cr.setFatherName(customer.getFatherName());
        cr.setLastName(customer.getLastName());
        cr.setBirthDate(customer.getBirthDate());
        cr.setDocSerial(customer.getDocSerial());
        cr.setFinkod(customer.getFinCode());
        cr.setCreatedDate(customer.getCreatedDate());
        cr.setEmailAddress(customer.getEmailAddress());

        if (Objects.nonNull(customer.getContactInfo())) {
            ContactInfoResponse cInfo = new ContactInfoResponse();
            cInfo.setHomeNumber(customer.getContactInfo().getHomeNumber());
            cInfo.setPhoneNumber1(customer.getContactInfo().getPhoneNumber1());
            cInfo.setPhoneNumber2(customer.getContactInfo().getPhoneNumber2());
            cInfo.setHomeNumber(customer.getContactInfo().getHomeNumber());
            cInfo.setId(customer.getContactInfo().getId());
            cInfo.setCustomerID(customer.getContactInfo().getCustomerID());
            cr.setContactInfoResponse(cInfo);
        }

        if (Objects.nonNull(customer.getContactInfo())) {
            AddressResponse ar = new AddressResponse();
            ar.setCustomerId(customer.getAddress().getCustomerId());
            ar.setCity(customer.getAddress().getCity());
            ar.setDistrict(customer.getAddress().getDistrict());
            ar.setCountryCode(customer.getAddress().getCountryCode());
            ar.setStreet(customer.getAddress().getStreet());
            ar.setCountryCode(customer.getAddress().getCountryCode());
            ar.setId(customer.getAddress().getId());
            cr.setAddressResponse(ar);
        }

        return cr;
    }


    public static CustomerDetailResponseList toResponseList(List<Customer> customerList) {

        CustomerDetailResponseList list = new CustomerDetailResponseList();
        List<CustomerDetailResponse> customerDetailResponses = new ArrayList<>();

        for (Customer c : customerList) {
            customerDetailResponses.add(customerResponse(c));
        }
        list.setCustomerDetailResponses(customerDetailResponses);

        return list;
    }
}
