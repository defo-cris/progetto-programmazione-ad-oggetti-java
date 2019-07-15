package com.example.progetto;

import com.example.progetto.csvClasses.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


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
