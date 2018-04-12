# DBBENCH

## Issues:
- Grammar only takes keywords uppercase.


## Notes
- Before running tests in IDE, make sure maven install or package has run once (to put the resources
 in the right place)
- Use ```generate_parserfiles.sh``` when the IDE has trouble finding the generated parser files.
- ```mvn clean``` will also delete the generated parser files for the IDE, to avoid duplicate 
classes during build.