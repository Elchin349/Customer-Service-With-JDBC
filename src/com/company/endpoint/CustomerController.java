package com.company.endpoint;

import com.company.dto.CommonResponse;
import com.company.dto.request.AddressRequest;
import com.company.dto.request.ContactInfoRequest;
import com.company.dto.request.CustomerCreateRequest;
import com.company.entity.Customer;
import com.company.services.CustomerService;
import com.company.services.CustomerServiceImpl;
import com.company.util.ConverterDate;
import org.apache.commons.lang3.StringUtils;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class CustomerController {

    private CustomerService customerService = new CustomerServiceImpl();

    private static Scanner scanner = new Scanner(System.in);

    public void controller() throws SQLException, ClassNotFoundException {

        System.out.println("CREATE CUSTOMER                   - 1");
        System.out.println("FIND CUSTOMER BY FIN CODE         - 2");
        System.out.println("FIND CUSTOMER DETAILS BY FIN CODE - 3");
        System.out.println("FIND ALL CUSTOMERS                - 4");
        System.out.println("EXIT                              - 0");
        int select = scanner.nextInt();
        scanner.nextLine();
        boolean lifeCycle = true;

        switch (select) {
            case 1:
                lifeCycle = createCustomer();
                break;
            case 2:
                lifeCycle = findCustomerByFinCode();
                break;
            case 3:
                lifeCycle = findCustomerDetailsByFinCode();
                break;
            case 4:
                lifeCycle = findAllCustomers();
                break;
            case 0:
                break;
        }

        if (lifeCycle) {
            controller();
        }

    }

    private boolean createCustomer() throws SQLException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\t\t\t\t\t ************************************ ");
        System.out.println("\t\t\t\t\t *    CUSTOMER REGISTRATION FORM    *");
        System.out.println("\t\t\t\t\t ************************************ \n\n\n");

        System.out.print(" FIRST NAME : ");
        String firstName = scanner.nextLine();
        System.out.println();

        System.out.print(" LAST NAME : ");
        String lastName = scanner.nextLine();
        System.out.println();

        System.out.print(" FATHER NAME : ");
        String fatherName = scanner.nextLine();
        System.out.println();

        System.out.print(" FIN CODE : ");
        String finCode = scanner.nextLine();
        System.out.println();

        System.out.print(" DOC SERIAL : ");
        String docSerial = scanner.nextLine();
        System.out.println();

        System.out.print(" BIRTH DATE : ");
        String birthDate = scanner.nextLine();
        System.out.println();

        System.out.print(" EMAIL ADDRESS : ");
        String emailAddress = scanner.nextLine();
        System.out.println();

        System.out.print(" COUNTRY : ");
        String country = scanner.nextLine();
        System.out.println();

        System.out.print(" COUNTRY CODE : ");
        String countryCode = scanner.nextLine();
        System.out.println();

        System.out.print(" CITY : ");
        String city = scanner.nextLine();
        System.out.println();

        System.out.print(" DISTRICT : ");
        String district = scanner.nextLine();
        System.out.println();

        System.out.print(" STREET : ");
        String street = scanner.nextLine();
        System.out.println();

        System.out.print(" PHONE NUMBER 1 : ");
        String phoneNumber1 = scanner.nextLine();
        System.out.println();

        System.out.print(" PHONE NUMBER 2 : ");
        String phoneNumber2 = scanner.nextLine();
        System.out.println();

        System.out.print(" HOME NUMBER : ");
        String homeNumber = scanner.nextLine();
        System.out.println("\n===============================================================");

        System.out.println("\t SAVE - 1  |  CANCEL - 2");
        int select = scanner.nextInt();
        scanner.nextLine();

        if (select == 1) {
            CustomerCreateRequest request = new CustomerCreateRequest();
            request.setFirstName(firstName);
            request.setLastName(lastName);
            request.setFatherName(fatherName);
            request.setFinCode(finCode);
            request.setDocSerial(docSerial);
            request.setBirthDate(ConverterDate.stringToLocalDate(birthDate));
            request.setEmailAddress(emailAddress);

            AddressRequest addressRequest = new AddressRequest();
            addressRequest.setCountry(country);
            addressRequest.setCountryCode(countryCode);
            addressRequest.setCity(city);
            addressRequest.setDistrict(district);
            addressRequest.setStreet(street);

            ContactInfoRequest contactInfoRequest = new ContactInfoRequest();
            contactInfoRequest.setHomeNumber(homeNumber);
            contactInfoRequest.setPhoneNumber1(phoneNumber1);
            contactInfoRequest.setPhoneNumber2(phoneNumber2);

            request.setAddressRequest(addressRequest);
            request.setContactInfoDTO(contactInfoRequest);
            customerService.createCustomer(request);
        }

        return true;
    }

    private boolean findAllCustomers() throws SQLException, ClassNotFoundException {
        CommonResponse commonResponse = customerService.getAllCustomers();
        System.out.println(commonResponse);
        return true;
    }

    private boolean findCustomerByFinCode() throws SQLException, ClassNotFoundException {
        System.out.println("Enter fincode");
        String fincode = scanner.nextLine();
        CommonResponse commonResponse = customerService.findByFinCode(fincode);
        printCustomer(commonResponse);
        return true;
    }

    private boolean findCustomerDetailsByFinCode() throws SQLException, ClassNotFoundException {
        System.out.println("Enter fincode");
        String fincode = scanner.nextLine();
        CommonResponse commonResponse = customerService.getCustomerDetailsByFinCode(fincode);
        printCustomer(commonResponse);
        return true;
    }

    private void printCustomer(CommonResponse c) {

        System.out.println(c);

    }
}
