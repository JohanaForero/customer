# Sunbelt

## requirements:

java 21
docker

## Download the Project

Clone the project repository from GitHub or download it as a ZIP file.
git clone https://github.com/JohanaForero/customer/tree/develop

## Open the Project in IntelliJ IDEA

-- Open IntelliJ IDEA.
-- Select File > New > Module from Existing Sources....
-- Navigate to the folder where you downloaded the project and select the pom.xml file.
-- Click OK to import the project.

## Download Dependencies

Once the project is open in IntelliJ IDEA: `./mvn install`

### Execute the Project

To run the project, you must locate yourself in the main class and run the application: `spring-boot:run`
Navigate to the following URL to access Swagger UI:
swagger at http://localhost:8090/swagger-ui/index.html

### postman

Collection postman at the root of this project, enter the postman directory, file with the name
`Parking.postman_collection.json`

### get customer

get customer with:

```
curl -X 'POST' \
  'http://localhost:8090/sunbelt/customer' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
  "documentType": "c",
  "documentNumber": "10121314"
}'
```
