# DBBENCH

For building and/or testing this project, Maven needs to be installed locally.
If Maven is not installed, replace ```mvn``` in the commands below with either ```mvnw``` (UNIX) or ```mvnw.cmd``` (Windows).

## Build
Run

    mvn package
   
To build and test the application. This will place the executable JAR-file in the target/ directory.

## Run Tests separately
To run the default test suite, use
    
    mvn test
   
There are a couple of extra test profiles, run them as:

    mvn test -P <profile-name>
    
The test profiles are:
- integration-tests (integration with Docker etc)
- development-tests (tests with project-specific files that are not part of the repo)
- all-tests (everything)
 


## Notes
- Before running tests in IDE, make sure maven install or package has run once (to put the resources
 in the right place)
- ```mvn clean``` will also delete the generated parser files for the IDE, to avoid duplicate 
classes during build.
