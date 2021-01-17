package com.company;

import com.company.endpoint.CustomerController;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        CustomerController customerController = new CustomerController();
        customerController.controller();


    }
}
