# FUEL TYPE
## 1. Get all fuel types  
HTTP method: GET  
Resource url: http://localhost:8086/carSale/car/fuelType  
Parameters: no  
Response format: JSON  
Response status: 200 
  
Example request: http://localhost:8086/carSale/car/fuelType  
Example response body: [{"fuelTypeId":1,"fuelType":"Bensin"},{"fuelTypeId":2,"fuelType":"Diesel"}]  
  
## 2. Get one fuel type  
HTTP method: GET  
Resource url: http://localhost:8086/carSale/car/fuelType/{id}  
Parameters:   
id: required, type = int, example = 1 
Content-type: application/json  
Response status: 200 
  
Example request: http://localhost:8086/carSale/car/fuelType/1  
Example response body: {"fuelTypeId":1,"fuelType":"Bensin"}  
  
## 3. Add fuel type  
HTTP method: POST  
Resource url: http://localhost:8086/carSale/car/fuelType  
Parameters: no  
Request body format: JSON  
Response status: 201 
  
Example request: http://localhost:8086/carSale/car/fuelType  
Example request body: {"fuelTypeId":1,"fuelType":"Bensin"} 
    
## 4. Update fuel type  
HTTP method: PUT  
Resource url: http://localhost:8086/carSale/car/fuelType  
Parameters: no    
Request body format: JSON  
Response status: 200 
  
Example request: http://localhost:8086/carSale/car/fuelType  
Example request body: {"fuelTypeId":2,"fuelType":"Diesel"}  
  
## 5. Delete fuel type  
HTTP method: PUT  
Resource url: http://localhost:8086/carSale/car/fuelType/{id}  
Parameters:   
id: required, type = int, example = 1    
Request body: no  
Response status: 204 
  
Example request: http://localhost:8086/carSale/car/fuelType/1   
  
# CAR
## 1. Get all cars  
HTTP method: GET  
Resource url: http://localhost:8086/carSale/car  
Parameters: no  
Response format: JSON  
Response status: 200 
  
Example request: http://localhost:8086/carSale/car  
Example response body: 
[{"carId":1,"year":1483218000000,"mileage":90300,"fuelType":{"fuelTypeId":1,"fuelType":"Bensin"},  
"transmission":{"transmissionId":1,"transmission":"Automatic"},  
"carModel":{"carModelId":1,"carMake":{"carMakeId":1,"carMake":"Mercedes"},"carModel":"C63_AMG"}},  
{"carId":2,"year":1462654800000,"mileage":110300,"fuelType":{"fuelTypeId":1,"fuelType":"Bensin"},  
"transmission":{"transmissionId":2,"transmission":"Manual"},  
"carModel":{"carModelId":2,"carMake":{"carMakeId":2,"carMake":"BMW"},"carModel":"M5"}}]
  
## 2. Get one car  
HTTP method: GET  
Resource url: http://localhost:8086/carSale/car/{id}  
Parameters:   
id: required, type = int, example = 1 
Content-type: application/json  
Response status: 200 
  
Example request: http://localhost:8086/carSale/car/1  
Example response body:  
{"carId":1,"year":1483218000000,"mileage":90300,"fuelType":{"fuelTypeId":1,"fuelType":"Bensin"},  
"transmission":{"transmissionId":1,"transmission":"Automatic"},  
"carModel":{"carModelId":1,"carMake":{"carMakeId":1,"carMake":"Mercedes"},"carModel":"C63_AMG"}}  
  
## 3. Add car  
HTTP method: POST  
Resource url: http://localhost:8086/carSale/car  
Parameters: no  
Request body format: JSON  
Response status: 201 
  
Example request: http://localhost:8086/carSale/car  
Example request body:  
{"carId":2,"year":1462654800000,"mileage":110300,"fuelType":{"fuelTypeId":1,"fuelType":"Bensin"},  
"transmission":{"transmissionId":2,"transmission":"Manual"},  
"carModel":{"carModelId":2,"carMake":{"carMakeId":2,"carMake":"BMW"},"carModel":"M5"}}  
    
## 4. Update car  
HTTP method: PUT  
Resource url: http://localhost:8086/carSale/car  
Parameters: no    
Request body format: JSON  
Response status: 200 
  
Example request: http://localhost:8086/carSale/car  
Example request body:  
{"carId":1,"year":1483218000000,"mileage":90300,"fuelType":{"fuelTypeId":1,"fuelType":"Bensin"},  
"transmission":{"transmissionId":1,"transmission":"Automatic"},  
"carModel":{"carModelId":1,"carMake":{"carMakeId":1,"carMake":"Mercedes"},"carModel":"C63_AMG"}  
  
## 5. Delete car  
HTTP method: PUT  
Resource url: http://localhost:8086/carSale/car/{id}  
Parameters:   
id: required, type = int, example = 1    
Request body: no  
Response status: 204 
  
Example request: http://localhost:8086/carSale/car/1   

# CAR_SALE
## 1. Get all car sales    
HTTP method: GET  
Resource url: http://localhost:8086/carSale 
Parameters: no  
Response format: JSON  
Response status: 200 
  
Example request: http://localhost:8086/carSale  
Example response body: 
[{"carSaleId":1,"price":36800,"date":1559336400000,  "user":{"userId":1,"userName":"Oleg","phoneNumber":"623-44-21",  
"login":"oleg97","password":"12321"},"car":{"carId":1,"year":1483218000000,"mileage":90300,  
"fuelType":{"fuelTypeId":1,"fuelType":"Bensin"},  "transmission":{"transmissionId":1,"transmission":"Automatic"},  
"carModel":{"carModelId":1,"carMake":{"carMakeId":1,"carMake":"Mercedes"},"carModel":"C63_AMG"}}},  
{"carSaleId":2,"price":40500,"date":1559854800000,"user":{"userId":1,"userName":"Oleg","phoneNumber":"623-44-21",  
"login":"oleg97","password":"12321"},"car":{"carId":2,"year":1462654800000,"mileage":110300,  
"fuelType":{"fuelTypeId":1,"fuelType":"Bensin"},"transmission":{"transmissionId":2,"transmission":"Manual"},  
"carModel":{"carModelId":2,"carMake":{"carMakeId":2,"carMake":"BMW"},"carModel":"M5"}}}]
  
## 2. Get one car sale  
HTTP method: GET  
Resource url: http://localhost:8086/carSale/{id}  
Parameters:   
id: required, type = int, example = 1 
Content-type: application/json  
Response status: 200 
  
Example request: http://localhost:8086/carSale/1  
Example response body:  
{"carSaleId":1,"price":36800,"date":1559336400000,  "user":{"userId":1,"userName":"Oleg","phoneNumber":"623-44-21",  
"login":"oleg97","password":"12321"},"car":{"carId":1,"year":1483218000000,"mileage":90300,  
"fuelType":{"fuelTypeId":1,"fuelType":"Bensin"},  "transmission":{"transmissionId":1,"transmission":"Automatic"},  
"carModel":{"carModelId":1,"carMake":{"carMakeId":1,"carMake":"Mercedes"},"carModel":"C63_AMG"}}}  
  
## 3. Add car sale  
HTTP method: POST  
Resource url: http://localhost:8086/carSale  
Parameters: no  
Request body format: JSON  
Response status: 201 
  
Example request: http://localhost:8086/carSale  
Example request body:  
{"carSaleId":2,"price":40500,"date":1559854800000,"user":{"userId":1,"userName":"Oleg","phoneNumber":"623-44-21",  
"login":"oleg97","password":"12321"},"car":{"carId":2,"year":1462654800000,"mileage":110300,  
"fuelType":{"fuelTypeId":1,"fuelType":"Bensin"},"transmission":{"transmissionId":2,"transmission":"Manual"},  
"carModel":{"carModelId":2,"carMake":{"carMakeId":2,"carMake":"BMW"},"carModel":"M5"}}}  
    
## 4. Update car sale  
HTTP method: PUT  
Resource url: http://localhost:8086/carSale  
Parameters: no    
Request body format: JSON  
Response status: 200 
  
Example request: http://localhost:8086/carSale  
Example request body:  
{"carSaleId":1,"price":36800,"date":1559336400000,  "user":{"userId":1,"userName":"Oleg","phoneNumber":"623-44-21",  
"login":"oleg97","password":"12321"},"car":{"carId":1,"year":1483218000000,"mileage":90300,  
"fuelType":{"fuelTypeId":1,"fuelType":"Bensin"},  "transmission":{"transmissionId":1,"transmission":"Automatic"},  
"carModel":{"carModelId":1,"carMake":{"carMakeId":1,"carMake":"Mercedes"},"carModel":"C63_AMG"}}}  
  
## 5. Delete car sale  
HTTP method: PUT  
Resource url: http://localhost:8086/carSale/{id}  
Parameters:   
id: required, type = int, example = 1    
Request body: no  
Response status: 204 
  
Example request: http://localhost:8086/carSale/1    
   
# CAR_FEATURE
## 1. Get all car features    
HTTP method: GET  
Resource url: http://localhost:8086/carSale/car/carFeature 
Parameters: no  
Response format: JSON  
Response status: 200 
  
Example request: http://localhost:8086/carSale/car/carFeature  
Example response body: 
[{"carFeatureId":1,"carFeature":"Winter tires","car":{"carId":1,"year":1483218000000,"mileage":90300,  
"fuelType":{"fuelTypeId":1,"fuelType":"Bensin"},"transmission":{"transmissionId":1,"transmission":"Automatic"},  
"carModel":{"carModelId":1,"carMake":{"carMakeId":1,"carMake":"Mercedes"},"carModel":"C63_AMG"}}},  
{"carFeatureId":2,"carFeature":"air conditioning","car":{"carId":1,"year":1483218000000,"mileage":90300,  
"fuelType":{"fuelTypeId":1,"fuelType":"Bensin"},"transmission":{"transmissionId":1,"transmission":"Automatic"},  
"carModel":{"carModelId":1,"carMake":{"carMakeId":1,"carMake":"Mercedes"},"carModel":"C63_AMG"}}}]  
  
## 2. Get one car feature  
HTTP method: GET  
Resource url: http://localhost:8086/carSale/car/carFeature/{id}  
Parameters:   
id: required, type = int, example = 1 
Content-type: application/json  
Response status: 200 
  
Example request: http://localhost:8086/carSale/car/carFeature/1  
Example response body:  
{"carFeatureId":1,"carFeature":"Winter tires","car":{"carId":1,"year":1483218000000,"mileage":90300,  
"fuelType":{"fuelTypeId":1,"fuelType":"Bensin"},"transmission":{"transmissionId":1,"transmission":"Automatic"},  
"carModel":{"carModelId":1,"carMake":{"carMakeId":1,"carMake":"Mercedes"},"carModel":"C63_AMG"}}}  
  
## 3. Add car feature  
HTTP method: POST  
Resource url: http://localhost:8086/carSale/car/carFeature  
Parameters: no  
Request body format: JSON  
Response status: 201 
  
Example request: http://localhost:8086/carSale/car/carFeature  
Example request body:  
{"carFeatureId":2,"carFeature":"air conditioning","car":{"carId":1,"year":1483218000000,"mileage":90300,  
"fuelType":{"fuelTypeId":1,"fuelType":"Bensin"},"transmission":{"transmissionId":1,"transmission":"Automatic"},  
"carModel":{"carModelId":1,"carMake":{"carMakeId":1,"carMake":"Mercedes"},"carModel":"C63_AMG"}}}  
    
## 4. Update car feature  
HTTP method: PUT  
Resource url: http://localhost:8086/carSale/car/carFeature  
Parameters: no    
Request body format: JSON  
Response status: 200 
  
Example request: http://localhost:8086/carSale/car/carFeature  
Example request body:  
{"carFeatureId":1,"carFeature":"Winter tires","car":{"carId":1,"year":1483218000000,"mileage":90300,  
"fuelType":{"fuelTypeId":1,"fuelType":"Bensin"},"transmission":{"transmissionId":1,"transmission":"Automatic"},  
"carModel":{"carModelId":1,"carMake":{"carMakeId":1,"carMake":"Mercedes"},"carModel":"C63_AMG"}}}  
  
## 5. Delete car feature  
HTTP method: PUT  
Resource url: http://localhost:8086/carSale/car/carFeature/{id}  
Parameters:   
id: required, type = int, example = 1    
Request body: no  
Response status: 204 
  
Example request: http://localhost:8086/carSale/car/carFeature/1
  
# COMMENT
## 1. Get all comments    
HTTP method: GET  
Resource url: http://localhost:8086/carSale/comment 
Parameters: no  
Response format: JSON  
Response status: 200 
  
Example request: http://localhost:8086/carSale/comment  
Example response body: 
[{"commentId":1,"userName":"Pasha","comment":"Good car","carSale":{"carSaleId":4,"price":57900,"date":1559682000000,  
"user":{"userId":1,"userName":"Pasha","phoneNumber":"623-44-21","login":"oleg97","password":"12321"},  
"car":{"carId":4,"year":1496782800000,"mileage":70300,"fuelType":{"fuelTypeId":1,"fuelType":"Bensin"},  
"transmission":{"transmissionId":1,"transmission":"Automatic"},"carModel":{"carModelId":3,  
"carMake":{"carMakeId":2,"carMake":"BMW"},"carModel":"M8"}}}},  
{"commentId":3,"userName":"Michael","comment":"Rubbish","carSale":{"carSaleId":4,"price":57900,"date":1559682000000,  
"user":{"userId":1,"userName":"Michael","phoneNumber":"623-44-21","login":"oleg97","password":"12321"},  
"car":{"carId":4,"year":1496782800000,"mileage":70300,"fuelType":{"fuelTypeId":1,"fuelType":"Bensin"},  
"transmission":{"transmissionId":1,"transmission":"Automatic"},"carModel":{"carModelId":3,  
"carMake":{"carMakeId":2,"carMake":"BMW"},"carModel":"M8"}}}}]  
  
## 2. Get one comment  
HTTP method: GET  
Resource url: http://localhost:8086/carSale/comment/{id}  
Parameters:   
id: required, type = int, example = 1 
Content-type: application/json  
Response status: 200 
  
Example request: http://localhost:8086/carSale/comment/1  
Example response body:  
[{"commentId":1,"userName":"Pasha","comment":"Good car","carSale":{"carSaleId":4,"price":57900,"date":1559682000000,  
"user":{"userId":1,"userName":"Pasha","phoneNumber":"623-44-21","login":"oleg97","password":"12321"},  
"car":{"carId":4,"year":1496782800000,"mileage":70300,"fuelType":{"fuelTypeId":1,"fuelType":"Bensin"},  
"transmission":{"transmissionId":1,"transmission":"Automatic"},"carModel":{"carModelId":3,  
"carMake":{"carMakeId":2,"carMake":"BMW"},"carModel":"M8"}}}}  
  
## 3. Add comment  
HTTP method: POST  
Resource url: http://localhost:8086/carSale/comment  
Parameters: no  
Request body format: JSON  
Response status: 201 
  
Example request: http://localhost:8086/carSale/comment  
Example request body:  
{"commentId":3,"userName":"Michael","comment":"Rubbish","carSale":{"carSaleId":4,"price":57900,"date":1559682000000,  
"user":{"userId":1,"userName":"Michael","phoneNumber":"623-44-21","login":"oleg97","password":"12321"},  
"car":{"carId":4,"year":1496782800000,"mileage":70300,"fuelType":{"fuelTypeId":1,"fuelType":"Bensin"},  
"transmission":{"transmissionId":1,"transmission":"Automatic"},"carModel":{"carModelId":3,  
"carMake":{"carMakeId":2,"carMake":"BMW"},"carModel":"M8"}}}}  
    
## 4. Update comment  
HTTP method: PUT  
Resource url: http://localhost:8086/carSale/comment  
Parameters: no    
Request body format: JSON  
Response status: 200 
  
Example request: http://localhost:8086/carSale/comment  
Example request body:  
{"commentId":1,"userName":"Pasha","comment":"Good car","carSale":{"carSaleId":4,"price":57900,"date":1559682000000,  
"user":{"userId":1,"userName":"Pasha","phoneNumber":"623-44-21","login":"oleg97","password":"12321"},  
"car":{"carId":4,"year":1496782800000,"mileage":70300,"fuelType":{"fuelTypeId":1,"fuelType":"Bensin"},  
"transmission":{"transmissionId":1,"transmission":"Automatic"},"carModel":{"carModelId":3,  
"carMake":{"carMakeId":2,"carMake":"BMW"},"carModel":"M8"}}}}  
  
## 5. Delete comment  
HTTP method: PUT  
Resource url: http://localhost:8086/carSale/comment/{id}  
Parameters:   
id: required, type = int, example = 1    
Request body: no  
Response status: 204 
  
Example request: http://localhost:8086/carSale/comment/1

# CAR_MODEL
## 1. Get all car models    
HTTP method: GET  
Resource url: http://localhost:8086/carSale/car/carModel 
Parameters: no  
Response format: JSON  
Response status: 200 
  
Example request: http://localhost:8086/carSale/car/carModel  
Example response body: 
[{"carModelId":1,"carMake":{"carMakeId":1,"carMake":"Mercedes"},"carModel":"C63_AMG"},  
{"carModelId":2,"carMake":{"carMakeId":2,"carMake":"BMW"},"carModel":"M5"},  
{"carModelId":3,"carMake":{"carMakeId":2,"carMake":"BMW"},"carModel":"M8"}]  
  
## 2. Get one car model  
HTTP method: GET  
Resource url: http://localhost:8086/carSale/car/carModel/{id}  
Parameters:   
id: required, type = int, example = 1 
Content-type: application/json  
Response status: 200 
  
Example request: http://localhost:8086/carSale/car/carModel/1  
Example response body:  
{"carModelId":1,"carMake":{"carMakeId":1,"carMake":"Mercedes"},"carModel":"C63_AMG"}  
  
## 3. Add car model  
HTTP method: POST  
Resource url: http://localhost:8086/carSale/car/carModel  
Parameters: no  
Request body format: JSON  
Response status: 201 
  
Example request: http://localhost:8086/carSale/car/carModel  
Example request body:  
{"carModelId":3,"carMake":{"carMakeId":2,"carMake":"BMW"},"carModel":"M8"}  
    
## 4. Update car model  
HTTP method: PUT  
Resource url: http://localhost:8086/carSale/car/carModel  
Parameters: no    
Request body format: JSON  
Response status: 200 
  
Example request: http://localhost:8086/carSale/car/carModel  
Example request body:  
{"carModelId":2,"carMake":{"carMakeId":2,"carMake":"BMW"},"carModel":"M5"}  
  
## 5. Delete car model  
HTTP method: PUT  
Resource url: http://localhost:8086/carSale/car/carModel/{id}  
Parameters:   
id: required, type = int, example = 1    
Request body: no  
Response status: 204 
  
Example request: http://localhost:8086/carSale/car/carModel/1

# CAR_MAKE
## 1. Get all car makes    
HTTP method: GET  
Resource url: http://localhost:8086/carSale/car/carModel/carMake 
Parameters: no  
Response format: JSON  
Response status: 200 
  
Example request: http://localhost:8086/carSale/car/carModel/carMake  
Example response body: [{"carMakeId":1,"carMake":"Mercedes"},{"carMakeId":2,"carMake":"BMW"}]
  
## 2. Get one car make  
HTTP method: GET  
Resource url: http://localhost:8086/carSale/car/carModel/carMake/{id}  
Parameters:   
id: required, type = int, example = 1 
Content-type: application/json  
Response status: 200 
  
Example request: http://localhost:8086/carSale/car/carModel/carMake/1  
Example response body: {"carMakeId":1,"carMake":"Mercedes"}  
  
## 3. Add car make  
HTTP method: POST  
Resource url: http://localhost:8086/carSale/car/carModel/carMake  
Parameters: no  
Request body format: JSON  
Response status: 201 
  
Example request: http://localhost:8086/carSale/car/carModel/carMake  
Example request body: {"carMakeId":2,"carMake":"BMW"}  
    
## 4. Update car make  
HTTP method: PUT  
Resource url: http://localhost:8086/carSale/car/carModel/carMake  
Parameters: no    
Request body format: JSON  
Response status: 200 
  
Example request: http://localhost:8086/carSale/car/carModel/carMake  
Example request body: {"carMakeId":1,"carMake":"Mercedes"}  
  
## 5. Delete car make  
HTTP method: PUT  
Resource url: http://localhost:8086/carSale/car/carModel/carMake/{id}  
Parameters:   
id: required, type = int, example = 1    
Request body: no  
Response status: 204 
  
Example request: http://localhost:8086/carSale/car/carModel/carMake/1





















 
 
 
 
 
 
 
 
# TRANSMISSION
## 1. Get all transmissions    
HTTP method: GET  
Resource url: http://localhost:8086/carSale/car/transmission
Response format: JSON  
Response status: 200 
  
Example request: http://localhost:8086/carSale/car/transmission  
Example response body: [{"transmissionId":1,"transmission":"Automatic"},{"transmissionId":2,"transmission":"Manual"}]
  
## 2. Get one transmission  
HTTP method: GET  
Resource url: http://localhost:8086/carSale/car/transmission/{id}  
Parameters:   
id: required, type = int, example = 1 
Content-type: application/json  
Response status: 200 
  
Example request: http://localhost:8086/carSale/car/transmission/1  
Example response body: {"transmissionId":1,"transmission":"Automatic"}  
  
## 3. Add transmission  
HTTP method: POST  
Resource url: http://localhost:8086/carSale/car/transmission  
Parameters: no  
Request body format: JSON  
Response status: 201 
  
Example request: http://localhost:8086/carSale/car/transmission  
Example request body: {"transmissionId":2,"transmission":"Manual"}  
    
## 4. Update transmission  
HTTP method: PUT  
Resource url: http://localhost:8086/carSale/car/transmission  
Parameters: no    
Request body format: JSON  
Response status: 200 
  
Example request: http://localhost:8086/carSale/car/transmission  
Example request body: {"transmissionId":1,"transmission":"Automatic"}  
  
## 5. Delete transmission  
HTTP method: PUT  
Resource url: http://localhost:8086/carSale/car/transmission/{id}  
Parameters:   
id: required, type = int, example = 1    
Request body: no  
Response status: 204 
  
Example request: http://localhost:8086/carSale/car/transmission/1
