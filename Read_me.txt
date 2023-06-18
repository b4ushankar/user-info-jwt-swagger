ROLE_USER -> can only do users/getUser request

ROLE_ADMIN -> Can do users/createUser , users/updateUser , users/deleteUser

for Sing up : /signup


Sample request: localhost:8080/signup
request type: POST
body:
{
    "username":"testUser",
    "password":"testUser",
    "role":"ROLE_USER"	
}

{
    "username":"testAdmin",
    "password":"testAdmin",
    "role":"ROLE_ADMIN"	
}

JWT token:
url : localhost:8080/authenticate
request type: POST
body:
{
    "username":"testUser",
    "password":"testUser",
    
}






