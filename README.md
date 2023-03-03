This application can compile by maven + jdk 17.

Import the project in Intellij/Eclipse and run AllstateApplication.java.

It has following end points

GET /contacts : To get all the contacts
```aidl
curl -u "user:password" "http://localhost:8080/contacts"
```
GET /contacts/{id} : To get contact by id
```aidl
curl -u "user:password" "http://localhost:8080/contacts/1"
```
POST /contacts : To create new contact

 ```
  curl  -u "user:password"  -XPOST -H "Content-type: application/json" -d '[{"firstName":"first","lastName":"last","phoneNumbers":[{"countryCode":"+44","number":"1234567890"}],"addresses":[{"addressLine1":"7/8 980","addressLine2":"St Edmund Road","city":"Glasgow","country":"UK","pincode":"G86RG"}]}]' 'http://localhost:8080/contacts'
```

PUT /contacts/{id} - To update contact by id
```aidl
 curl  -u "user:password" -X PUT -H "Content-Type: application/json" -d '{"firstName":"first","lastName":"lastOne","phoneNumbers":[{"countryCode":"+91","number":"1234567890"}],"addresses":[{"addressLine1":"7/8 980","addressLine2":"St Edmund Road","city":"Glasgow","country":"UK","pincode":"G86RG"}]}' 'http://localhost:8080/contacts/3'
```
DELETE /contact  - To delete contact

```aidl
curl -u "user:password" -X DELETE "http://localhost:8080/contacts/3"
```

GET /contacts/search : To search for contact based on combination of 
first name and last name
```aidl
curl -u "user:password" "http://localhost:8080/contacts/search?firstName=first&lastName=last"
curl -u "user:password" "http://localhost:8080/contacts/search?lastName=last"
curl -u "user:password" "http://localhost:8080/contacts/search?firstName=first"
```
