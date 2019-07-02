# FUEL TYPE
## 1. Get all fuel types  
Resource url: 8086/carSale/car/fuelType  
Parameters: no  
Response format: JSON  
Response status: 200 
  
Example request:GET /carSale/car/fuelType  
Example response body:  
```
[{"fuelTypeId":1,"fuelType":"Bensin"},
{"fuelTypeId":2,"fuelType":"Diesel"}]
```  

# CAR
## 1. Get all cars  
Resource url: /carSale/car  
Parameters: no  
Response format: JSON  
Response status: 200 
  
Example request: GET /carSale/car  
Example response body: 
```
[{"carId":1,"year":1483218000000,"mileage":90300,"fuelType":{"fuelTypeId":1,"fuelType":"Bensin"},  
"transmission":{"transmissionId":1,"transmission":"Automatic"},  
"carModel":{"carModelId":1,"carMake":{"carMakeId":1,"carMake":"Mercedes"},"carModel":"C63_AMG"}},  
{"carId":2,"year":1462654800000,"mileage":110300,"fuelType":{"fuelTypeId":1,"fuelType":"Bensin"},  
"transmission":{"transmissionId":2,"transmission":"Manual"},  
"carModel":{"carModelId":2,"carMake":{"carMakeId":2,"carMake":"BMW"},"carModel":"M5"}}]
```  
## 2. Get one car  
Resource url: /carSale/car/{id}  
Parameters:   
id: required, type = int, example = 1  
Content-type: application/json  
Response status: 200 
  
Example request: GET /carSale/car/1  
Example response body:  
```
{"carId":1,"year":1483218000000,"mileage":90300,"fuelType":{"fuelTypeId":1,"fuelType":"Bensin"},  
"transmission":{"transmissionId":1,"transmission":"Automatic"},  
"carModel":{"carModelId":1,"carMake":{"carMakeId":1,"carMake":"Mercedes"},"carModel":"C63_AMG"}}  
```
## 3. Add car  
Resource url: /carSale/car  
Parameters: no  
Request body format: JSON  
Response status: 201 
  
Example request: POST /carSale/car  
Example request body:
```  
{"carId":2,"year":1462654800000,"mileage":110300,"fuelType":{"fuelTypeId":1,"fuelType":"Bensin"},  
"transmission":{"transmissionId":2,"transmission":"Manual"},  
"carModel":{"carModelId":2,"carMake":{"carMakeId":2,"carMake":"BMW"},"carModel":"M5"}}  
``` 
## 4. Update car  
Resource url: /carSale/car  
Parameters: no    
Request body format: JSON  
Response status: 200 
  
Example request: PUT /carSale/car  
Example request body:
```  
{"carId":1,"year":1483218000000,"mileage":90300,"fuelType":{"fuelTypeId":1,"fuelType":"Bensin"},  
"transmission":{"transmissionId":1,"transmission":"Automatic"},  
"carModel":{"carModelId":1,"carMake":{"carMakeId":1,"carMake":"Mercedes"},"carModel":"C63_AMG"}  
```  
## 5. Delete car  
Resource url: /carSale/car/{id}  
Parameters:   
id: required, type = int, example = 1    
Request body: no  
Response status: 204 
  
Example request: DELETE /carSale/car/1   

# CAR_SALE
## 1. Get all car sales    
Resource url: /carSale  
Parameters: no  
Response format: JSON  
Response status: 200 
  
Example request: GET /carSale  
Example response body:
``` 
[{"carSaleId":1,"price":36800,"date":1559336400000,  "user":{"userId":1,"userName":"Oleg","phoneNumber":"623-44-21",  
"login":"oleg97","password":"12321"},"car":{"carId":1,"year":1483218000000,"mileage":90300,  
"fuelType":{"fuelTypeId":1,"fuelType":"Bensin"},  "transmission":{"transmissionId":1,"transmission":"Automatic"},  
"carModel":{"carModelId":1,"carMake":{"carMakeId":1,"carMake":"Mercedes"},"carModel":"C63_AMG"}}},  
{"carSaleId":2,"price":40500,"date":1559854800000,"user":{"userId":1,"userName":"Oleg","phoneNumber":"623-44-21",  
"login":"oleg97","password":"12321"},"car":{"carId":2,"year":1462654800000,"mileage":110300,  
"fuelType":{"fuelTypeId":1,"fuelType":"Bensin"},"transmission":{"transmissionId":2,"transmission":"Manual"},  
"carModel":{"carModelId":2,"carMake":{"carMakeId":2,"carMake":"BMW"},"carModel":"M5"}}}]
```  
## 2. Get one car sale  
Resource url: /carSale/{id}  
Parameters:   
id: required, type = int, example = 1  
Content-type: application/json  
Response status: 200 
  
Example request: GET /carSale/1  
Example response body:  
```
{"carSaleId":1,"price":36800,"date":1559336400000,  "user":{"userId":1,"userName":"Oleg","phoneNumber":"623-44-21",  
"login":"oleg97","password":"12321"},"car":{"carId":1,"year":1483218000000,"mileage":90300,  
"fuelType":{"fuelTypeId":1,"fuelType":"Bensin"},  "transmission":{"transmissionId":1,"transmission":"Automatic"},  
"carModel":{"carModelId":1,"carMake":{"carMakeId":1,"carMake":"Mercedes"},"carModel":"C63_AMG"}}}  
```  
## 3. Add car sale  
Resource url: /carSale  
Parameters: no  
Request body format: JSON  
Response status: 201 
  
Example request: POST /carSale  
Example request body:
```  
{"carSaleId":2,"price":40500,"date":1559854800000,"user":{"userId":1,"userName":"Oleg","phoneNumber":"623-44-21",  
"login":"oleg97","password":"12321"},"car":{"carId":2,"year":1462654800000,"mileage":110300,  
"fuelType":{"fuelTypeId":1,"fuelType":"Bensin"},"transmission":{"transmissionId":2,"transmission":"Manual"},  
"carModel":{"carModelId":2,"carMake":{"carMakeId":2,"carMake":"BMW"},"carModel":"M5"}}}  
```    
## 4. Update car sale  
Resource url: /carSale  
Parameters: no    
Request body format: JSON  
Response status: 200 
  
Example request: PUT /carSale  
Example request body:
```  
{"carSaleId":1,"price":36800,"date":1559336400000,  "user":{"userId":1,"userName":"Oleg","phoneNumber":"623-44-21",  
"login":"oleg97","password":"12321"},"car":{"carId":1,"year":1483218000000,"mileage":90300,  
"fuelType":{"fuelTypeId":1,"fuelType":"Bensin"},  "transmission":{"transmissionId":1,"transmission":"Automatic"},  
"carModel":{"carModelId":1,"carMake":{"carMakeId":1,"carMake":"Mercedes"},"carModel":"C63_AMG"}}}  
```  
## 5. Delete car sale  
Resource url: /carSale/{id}  
Parameters:   
id: required, type = int, example = 1    
Request body: no  
Response status: 204 
  
Example request: DELETE /carSale/1    
   
# CAR_FEATURE
## 1. Get all features of the selected car   
Resource url: /carSale/car/{id}/carFeature  
Parameters: no  
Response format: JSON  
Response status: 200 
  
Example request: GET /carSale/car/1/carFeature  
Example response body:
``` 
[{"carFeatureId":1,"carFeature":"Winter tires","car":{"carId":1,"year":1483218000000,"mileage":90300,  
"fuelType":{"fuelTypeId":1,"fuelType":"Bensin"},"transmission":{"transmissionId":1,"transmission":"Automatic"},  
"carModel":{"carModelId":1,"carMake":{"carMakeId":1,"carMake":"Mercedes"},"carModel":"C63_AMG"}}},  
{"carFeatureId":2,"carFeature":"air conditioning","car":{"carId":1,"year":1483218000000,"mileage":90300,  
"fuelType":{"fuelTypeId":1,"fuelType":"Bensin"},"transmission":{"transmissionId":1,"transmission":"Automatic"},  
"carModel":{"carModelId":1,"carMake":{"carMakeId":1,"carMake":"Mercedes"},"carModel":"C63_AMG"}}}]  
```  
  
## 3. Add car feature  
Resource url: /carSale/car/carFeature  
Parameters: no  
Request body format: JSON  
Response status: 201 
  
Example request: POST /carSale/car/carFeature  
Example request body:  
```
{"carFeatureId":2,"carFeature":"air conditioning","car":{"carId":1,"year":1483218000000,"mileage":90300,  
"fuelType":{"fuelTypeId":1,"fuelType":"Bensin"},"transmission":{"transmissionId":1,"transmission":"Automatic"},  
"carModel":{"carModelId":1,"carMake":{"carMakeId":1,"carMake":"Mercedes"},"carModel":"C63_AMG"}}}  
```    
## 4. Update car feature  
Resource url: /carSale/car/carFeature  
Parameters: no    
Request body format: JSON  
Response status: 200 
  
Example request: PUT /carSale/car/carFeature  
Example request body:  
```
{"carFeatureId":1,"carFeature":"Winter tires","car":{"carId":1,"year":1483218000000,"mileage":90300,  
"fuelType":{"fuelTypeId":1,"fuelType":"Bensin"},"transmission":{"transmissionId":1,"transmission":"Automatic"},  
"carModel":{"carModelId":1,"carMake":{"carMakeId":1,"carMake":"Mercedes"},"carModel":"C63_AMG"}}}  
```  
## 5. Delete car feature  
Resource url: DELETE/carSale/car/carFeature/{id}  
Parameters:   
id: required, type = int, example = 1    
Request body: no  
Response status: 204 
  
Example request: DELETE /carSale/car/carFeature/1
  
# COMMENT
## 1. Get all comments of the selected car sale   
Resource url: /carSale/{id}/comment  
Parameters: no  
Response format: JSON  
Response status: 200 
  
Example request: GET /carSale/4/comment  
Example response body: 
```
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
```  
 
## 3. Add comment  
Resource url: /carSale/comment  
Parameters: no  
Request body format: JSON  
Response status: 201 
  
Example request: POST /carSale/comment  
Example request body:  
```
{"commentId":3,"userName":"Michael","comment":"Rubbish","carSale":{"carSaleId":4,"price":57900,"date":1559682000000,  
"user":{"userId":1,"userName":"Michael","phoneNumber":"623-44-21","login":"oleg97","password":"12321"},  
"car":{"carId":4,"year":1496782800000,"mileage":70300,"fuelType":{"fuelTypeId":1,"fuelType":"Bensin"},  
"transmission":{"transmissionId":1,"transmission":"Automatic"},"carModel":{"carModelId":3,  
"carMake":{"carMakeId":2,"carMake":"BMW"},"carModel":"M8"}}}}  
```    
## 4. Update comment  
Resource url: /carSale/comment  
Parameters: no    
Request body format: JSON  
Response status: 200 
  
Example request: PUT /carSale/comment  
Example request body:  
```
{"commentId":1,"userName":"Pasha","comment":"Good car","carSale":{"carSaleId":4,"price":57900,"date":1559682000000,  
"user":{"userId":1,"userName":"Pasha","phoneNumber":"623-44-21","login":"oleg97","password":"12321"},  
"car":{"carId":4,"year":1496782800000,"mileage":70300,"fuelType":{"fuelTypeId":1,"fuelType":"Bensin"},  
"transmission":{"transmissionId":1,"transmission":"Automatic"},"carModel":{"carModelId":3,  
"carMake":{"carMakeId":2,"carMake":"BMW"},"carModel":"M8"}}}}  
```  
## 5. Delete comment  
Resource url: /carSale/comment/{id}  
Parameters:   
id: required, type = int, example = 1    
Request body: no  
Response status: 204 
  
Example request: DELETE /carSale/comment/1

# CAR_MODEL
## 1. Get all car models    
Resource url: /carSale/car/carModel  
Parameters: no  
Response format: JSON  
Response status: 200 
  
Example request: GET /carSale/car/carModel  
Example response body: 
```
[{"carModelId":1,"carMake":{"carMakeId":1,"carMake":"Mercedes"},"carModel":"C63_AMG"},  
{"carModelId":2,"carMake":{"carMakeId":2,"carMake":"BMW"},"carModel":"M5"},  
{"carModelId":3,"carMake":{"carMakeId":2,"carMake":"BMW"},"carModel":"M8"}]  
```  
  
# CAR_MAKE
## 1. Get all car makes    
Resource url: /carSale/car/carModel/carMake  
Parameters: no  
Response format: JSON  
Response status: 200 
  
Example request: GET /carSale/car/carModel/carMake  
Example response body: 
```
[{"carMakeId":1,"carMake":"Mercedes"},
{"carMakeId":2,"carMake":"BMW"}]
```  
  
# TRANSMISSION
## 1. Get all transmissions    
Resource url: /carSale/car/transmission  
Response format: JSON  
Response status: 200 
  
Example request: GET /carSale/car/transmission  
Example response body: 
```
[{"transmissionId":1,"transmission":"Automatic"},
{"transmissionId":2,"transmission":"Manual"}]
```
