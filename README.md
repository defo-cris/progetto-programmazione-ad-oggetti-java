progetto-programmazione-ad-oggetti-java
=======================================

esempi filtri:
localhost:8080/filter
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




localhost:8080/filter
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



```http
localhost:8080/data
```



```http
localhost:8080/metadata
```



