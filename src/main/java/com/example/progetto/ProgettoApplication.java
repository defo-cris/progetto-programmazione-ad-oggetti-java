package com.example.progetto;

import csvClasses.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ProgettoApplication
{

    public static void main(String[] args)
    {
        DataCsv data = new DataCsv();

        data.readAndStore();

        data.setServicesData();

        SpringApplication.run(ProgettoApplication.class, args);
    }

}
