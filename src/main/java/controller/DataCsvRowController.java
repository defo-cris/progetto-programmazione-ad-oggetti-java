package controller;

import csvClasses.dataType.Metadata;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Vector;

@RestController
public class DataCsvRowController
{
    private final DataCsvRowServices dataServices;

    public DataCsvRowController(DataCsvRowServices dataServices)
    {
        this.dataServices = dataServices;
    }

    @GetMapping("/data")
    public Vector<DataCsvRow> retrieveData()
    {
        return DataCsvRowServices.getCsvData();
    }

    @GetMapping("/metadata")
    public Vector<Metadata> retrieveMetadata()
    {
        return DataCsvRowServices.getMetadata();
    }

}
