

progetto-programmazione-ad-oggetti-java
=======================================

The purpose of this project is to read and analyze a data-set and to create a set of classes to represent it and expose them to the user with API rest get/post.

Our data-set is a Examples of EU funded projects. 

Our Java projects consist in this features:

###### At the start of the program:

Our program download in ram the data-set of the CSV from the Json URL. This first operation is performed when the application is started.  This is done by the class GetCsvUrlFromJsonUrl. This class read the json from the URL and get the URL of the CSV. After that the main call another class to read the data of the csv and save them in a class.

###### When the reading is completed:

The data are split and parse in a data structure based on the above classes, because every record of the data-set correspond to an object of a class. The data structure are two; one for the data and one for the *Metadata*. Every row of the data-set represent an *Eu founded project* and each field of the columns is the *Metadata.* Every *Eu founded project* have this fields:

- Nid --> nid (Integer)
- Original ID --> originalId (String)
- Name --> name (String)
- Project acronym --> projectAcronym (String)
- Visual --> visual (String)
- Project description --> projectDescription (String)
- Results --> results (String)
- Coordinators --> coordinators (String)
- Partners --> partners (ObjArray<String>)
- Project address(es) --> projectAddresses (ObjArray<String>)
- Project postal code(s) --> projectPostalCodes (ObjArray<String>)
- Project town(s) --> projectTowns (ObjArray<String>)
- Project country(ies) --> projectCountryies (ObjArray<String>)
- Project location latitude --> projectLocationLatitude (ObjArray<Float>)
- Project location longitude --> projectLocationLongitude (ObjArray<Float>)
- Link to a video --> linkToAVideo (String)
- Timeframe start --> timeframeStart (String)
- Timeframe end --> timeframeEnd (String)
- Project webpage --> projectWebpage (String)
- Related links --> relatedLinks (ObjArray<UrlWithDescription>)
- EU Budget MFF heading --> euBudgetMffHeading (String)
- Programme name --> programmeName (String)
- Funding area --> fundingArea (ObjArray<String>)
- EC’s priorities --> ecsPriorities (ObjArray<String>)
- EU Budget contribution --> euBudgetContribution (Integer)
- Total project budget --> totalProjectBudget (Integer)
- Author --> author (String)
- Language --> language (String)

We had some cases where some fields of the row had some HTML tag and some end of line character in the quotation marks. In the first case we resolved the problem creating a method that delete only the tag conserving the URL and the anchor of the link:

```java
public static ObjArray<UrlWithDescription> validateUrlArraySemicolonSeparated(String s)
{
    String[] tmp = s.split(";");

    UrlWithDescription[] urls = new UrlWithDescription[tmp.length];

    for (int i = 0; i < tmp.length; i++)
    {
        urls[i] = new UrlWithDescription();
        urls[i].setDescription(validateString(tmp[i]));
        try
        {
            urls[i].setUrl(tmp[i].replace("\"<a href=\"", "").replace("<a href=\"",                   "").split("\"")[1]);
        }
        catch (Exception e)
        {
            urls[i].setUrl("NULL");
        }
    }
    return new ObjArray<>(urls);
}
```

For the second problem we had to create a new class that simulate the read line of the Buffered Reader, this class read the line character by character,and where the reader find a end of line character it doesn't add it at the string.

###### On Postman:

Using API Rest GET and POST we return data, metadata, statistics and filtered datasets. 

Afterwards the various requests that can be carried out with relevant examples will be listed.

------

#### GET Request:

##### /data

Return all the data-set.



##### /data/{colName}?excludeNull={true | false}

Return the data only of the specified column; and if the parameter excludeNull is true all the null values of the column are excluded, otherwise all the data are retrieved.

*example:*

```http 
localhost:8080/data/nid?excludeNull=false
```

```http
localhost:8080/data/nid?excludeNull=true
```



##### /metadata

Return all the field with the alias, the source field and the type of every field.

##### /stats/{colName}

Return the statistics only based on the *NumberStats* class. The only two statistics that have some sense are:

- euBudgetContribution 
- totalProjectBudget

The statistics that result from the class are:

- Average
- Minimum
- Maximum
- Standard Deviation
- Sum

And are calculated in this method:

```java
for (DataCsvRow item : csvData)
{
    Method m = item.getCl
        ass().getMethod("get" + fieldName);
    int data = (int) m.invoke(item);
    min = (min == null ? data : (min < data ? min : data));
    max = (max == null ? data : (max > data ? max : data));
    sum += data;
    tmp[i++] = data;
}
avg = sum / count;
for (int xi : tmp)
{
    std += Math.pow(xi - avg, 2);
}
std = Math.sqrt(std / count);
```

*example:*

```http
localhost:8080/stats/totalProjectBudget
```

*response:* 

```json
{
    "avg": 20234875,
    "min": 0,
    "max": 2110874201,
    "std": 99926570.48545268,
    "sum": 35249152826
}
```



##### /search?value="value"&exactMatch={"true" | "false"}

Return only the row where the value is contained into it; if the parameter exactMatch is true only the row where an element as the exact match of the value are returned, otherwise the row that contain into them the value are returned.

*example:*

1. ```http
   localhost:8080/search?value="UK"&exactMatch="true"
   ```

2. ```http
   localhost:8080/search?value="Sparking"&exactMatch="false"
   ```



##### /count/{fieldName}?value={"String" | int}

Return an integer value of the number of times the parameter value is contained in the fieldName.

*example:*

```http
localhost:8080/count/euBudgetContribution?value=1000000
```

*response:*

```json
{
    "count": 16
}
```



------

#### POST Request

##### /filter

Used to indicate a generic command of filter through a POST Request. 

*example:*

```haml
localhost:8080/filter
```

Since it is a POST Request it must have a body, this body must be a JSON format, inside this JSON it must have a field where to operate the filter operation, the operation to do, and a value to use how comparator. The returned body is the row of the data-set that respect the condition of the filter.

*body example:*

```json
{
    "fieldName": "projectAcronym",
    "operator": "==",
	"value": "SONO"
}
```

The response body will be only the row of the data-set where in the column "projectAcronym" is contained the string "SONO". If a logical operator is found ("$or" or "$and") and inside it I have an Array of Json request, it filter them multiple times. The two logical operation work the opposite, the $and catch only the row of the CSV that respect all the parameters. The $or catch all the row that respect at least one of the them.

*example of a filter with the "$or" logical condition:* 

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~json
{
    "$or": [
        {
            "fieldName": "projectAcronym",
            "operator": "==",
            "value": "SONO"
        },
        {
            "fieldName": "name",
            "operator": "==",
            "value": "The INOV Contacto Programme"
        }
    ]
}
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

*example of a filter with the "$and" logical condition:*

```json
{
    "$and": [
        {
            "fieldName": "nid",
            "operator": ">=",
            "value": "100"
        },
        {
            "fieldName": "totalProjectBudget",
            "operator": "<",
            "value": "5000"
        },
        {
            "fieldName": "totalProjectBudget",
            "operator": ">",
            "value": "100"
        }
    ]
}
```



------

#### Error Handling

In case of an incorrect route or a request has a bad format, the application return an error message

- filter features

  In case of a wrong operator in the Json body will appear this message:

  - int : 

    *example:*

    ```json
    {
    	"fieldName": "euBudgetContribution",
        "operator": "=<",
        "value": "1000"
    }
    ```

    ```html
    message = "Error in the operator, please use ==,<,<=,>,>= or !="
    ```

    

  - string : 

    *example:*

    ```json
    {
        "fieldName": "name",
        "operator": "=<",
    	"value": "The INOV Contacto Programme"
    }
    ```

    ```html
    message = "Error in the operator, please use == or !="
    ```

In case of a wrong field name in the Json body will appear this message:

*example:*

```json
{
    "fieldName": "nome",
    "operator": "==",
    "value": "The INOV Contacto Programme"
}
```

```html
message = "Error in the declaration of the fieldname"
```



In case you write the logical operator wrong will appear this message:

*example:*

```json
{
    "and": [
        {
            "fieldName": "euBudgetContribution",
            "operator": "==",
            "value": "1000"
        },
        {
            "fieldName": "name",
            "operator": "=<",
            "value": "The INOV Contacto Programme"
        }
    ]
}
```

```html
message = "Incorrect JSON body"
```



- stats feature

  In case of a wrong field name in the route will appear this message:

  *example:*

  ```http
  localhost:8080/stats/nidd
  ```

  ```html
  message = "Error in the declaration of the route, the right parameter to use are totalProjectBudge and euBudgetContribution"
  ```



- data feature

  In case you want to retrieve a data from an inexistent field will appear this error message:

  *example:*

  ```http
  localhost:8080/data/nird?excludeNull=false
  ```

  ```html
  message = "column name not valid, please write some of them: [Nid, OriginalId, Name, ProjectAcronym, Visual, ProjectDescription, Results, Coordinators, Partners, ProjectAddresses, ProjectPostalCodes, ProjectTowns, ProjectCountryies, ProjectLocationLatitude, ProjectLocationLongitude, LinkToAVideo, TimeframeStart, TimeframeEnd, ProjectWebpage, RelatedLinks, EuBudgetMffHeading, ProgrammeName, FundingArea, EcsPriorities, EuBudgetContribution, TotalProjectBudget, Author, Language]"
  ```

