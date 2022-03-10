# Hello Keycloak

## Test project

In order to test this project in your local machine, you first need to have a running Keycloak instance; then create a Realm and add a new client with access type `confidential` or `public`.
In addition to that, you need to create some fake users to test accessibility to some endpoints. You can assign one of the following roles for each user: `ADMIN`, `MANAGER`, `USER`.

### Create access token

To access to the API, you need to have a JWT token, this actually should be handled by the front-end application, but for demo purposes; we will create this manually using a cURL command as shown below:

```shell
curl -X POST 'http://localhost:8080/realms/<REALM-NAME>/protocol/openid-connect/token' \
 --header 'Content-Type: application/x-www-form-urlencoded' \
 --data-urlencode 'grant_type=password' \
 --data-urlencode 'client_id=<CLIENT-NAME>' \
 --data-urlencode 'client_secret=<CLIENT-SECRET>' \
 --data-urlencode 'username=<USERNAME>' \
 --data-urlencode 'password=<PASSWORD>'
```

Note: update the elements between <> with the corresponding ones in your Keycloak instance.
The **<CLIENT-SECRET>** is not required if the client is public.

Copy the value of `access_token` key given by the response of the above command.

Now, run the springboot application, and test accessing to one of the following endpoint with the copied access_token added as Bearer authorization to our request: 
- [GET] /hello/auth
- [GET] /hello/user (USER or ADMIN role is required)
- [GET] /hello/manager (MANAGER or ADMIN role is required)
- [GET] /hello/admin (ADMIN role is required)