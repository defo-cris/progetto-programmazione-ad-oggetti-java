package com.example.progetto;

import csvClasses.*;

import csvClasses.csvRetrieve.CsvValidator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


@SpringBootApplication
public class ProgettoApplication
{

    public static void main(String[] args)
    {

        SpringApplication.run(ProgettoApplication.class, args);

        DataCsv data = new DataCsv();

        data.readAndStore();

        data.setServicesData();

    }

}
