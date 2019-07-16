progetto-programmazione-ad-oggetti-java
=======================================
 
esempi filtri:
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
{
    "$or": [
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
        ],
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
}
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
