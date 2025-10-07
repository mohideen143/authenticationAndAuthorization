This  is the authentication And Authorization startup Project in Spring Boot.

using : 
      1. Json Web Token (JWT),
      2. Spring Security
      3.Bcrypt Password Encoder

API'S : 
      1. User Register
      2. Login
      3. getAllUsers

* Create User Authentication and Authorization startUp project for ready to use.
* I create Webconfig class for Cors Origin.  
* In this project I use Json Web Token (JWT) and Spring security for User Authentication & Authorization .
* once user Register their profile the password will be encrypted using Bcrypt Password Encoder.
* then user login with their credentials, if the user details is matching with our DB records Application give one Bearer Token to the user.
* after that we use that token for other API'S like getAllUsers.
* in getAllUsers Api we give that token via headers then the application check the token is valid .
* if it is valid token the api working respectively otherwise Unauthorized Exception Occure. 
