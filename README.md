# DBBENCH


## Notes
- Before running tests in IDE, make sure maven install or package has run once (to put the resources
 in the right place)
- ```mvn clean``` will also delete the generated parser files for the IDE, to avoid duplicate 
classes during build.
- At the moment - during development - JSQLParser's debug mode is set. Need to compile JSQLParser
 separately: In lib/jsqlparser run ```mvn install -DskipTests=true```

- ```SELECT ... FROM multiple, tablenames``` is not yet supported