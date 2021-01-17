package com.company.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConverterDate {

    public static LocalDate stringToLocalDate(String date){
        if(date != null){
            return LocalDate.parse(date);
        }
        return null;
    }


    public static LocalDateTime stringToLocalDateTime(String date){
        if(date != null){
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return LocalDateTime.parse(date,df);
        }
        return null;
    }

}
