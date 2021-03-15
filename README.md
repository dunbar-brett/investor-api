
# crowdstreet-investor-api

Java with Spring Boot API for CrowdStreet Interview

  

## Starting the API

Open the project in an IDE

Run `CrowdStreetInvestorApiApplication` in `com.brettdunbar.crowdStreetInvestorApiApplication` package.

The `api` should be accessible through `[http://localhost:8080/api/](http://localhost:8080/api/)`

  
  

## Postman

A [postman](https://www.postman.com/downloads/) collection is in the directory `crowdstreet-investor-api > postmanCollection` to quickly test the required endpoints and also provides additional testing endpoints

  

Open the collection in Postman and you can hit each test endpoint with a body format for POSTs/PUTs set.

  

There are also some Preset Testing request that tests a few things that include:

Test Requests

> (1) Test Request wrong type
> 
> - Sends an int instead of a string
> 
> - Passes: Handles parsing int to string
> 
> (1.1) Test Request additional params
> 
> - sends additional parameters in the body
> 
> - Passes: only uses params needed (body)
> 
> (1.2) Test Request no body
> 
> - sends request with no body
> 
> - Passes: Handles with a bad request

These same tests can be applied to the other endpoints and similar results are expected. 
If I had more time with the project I would also including some Unit tests testing each of the endpoints and the service methods.