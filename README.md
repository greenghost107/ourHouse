## Basic usage
To be able to access the server you will need to authenticate
```
Post Request - http://<your-ip>:8080/register
```
you should send a json
{
"username":"yourName"
"password":"yourPassword"
}

```
Post Request - http://<your-ip>:8080/authenticate
```
which will return a token that can be used to access the api-s

