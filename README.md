

## Controllers

![image](https://user-images.githubusercontent.com/59394298/101530407-27c28500-3992-11eb-9207-96331a0c1a57.png)
--------------------------------------------------------------------------
## API AuthController

![image](https://user-images.githubusercontent.com/59394298/101530944-e1215a80-3992-11eb-9a29-cc4fe51d1897.png)

**Sign in**

![image](https://user-images.githubusercontent.com/59394298/101531280-4ecd8680-3993-11eb-9787-4979da50c2e0.png)

##### Request
```
{
  "password": "1834asd",
  "username": "test1"
}
```

##### Response *code 200*
```
{
  "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0MSIsImlhdCI6MTYwNzQ1NjIzNSwiZXhwIjoxNjA3NTQyNjM1fQ.zZj1eOLz10sNdkzTuI7rnbgzOGrRdl63-zrnyaWiEGf7f0IEhuS3lDlfy8elYyl4AJS32TJhMYSdbQaJBHRAsA",
  "id": 3,
  "username": "test1",
  "email": "test1@gmail.com",
  "roles": [
    "ROLE_USER"
  ],
  "tokenType": "Bearer"
} 
```
##### Response *code 401*
```
{
  "timestamp": "2020-12-08T19:42:49.616+00:00",
  "status": 401,
  "error": "Unauthorized",
  "message": "",
  "path": "/api/auth/signin"
}
```

**Sign up**

![image](https://user-images.githubusercontent.com/59394298/101534201-1cbe2380-3997-11eb-8e60-de2a50e8fed7.png)

##### Request
```
{
  "email": "test2@gmail.com",
  "password": "1834asd",
  "role": [
    "user"
  ],
  "username": "test234"
}
```
##### Response *code 200*
```
{
  "message": "User registered successfully!"
}
```
##### Response *code 400*
```
{
  "message": "Error: Username is already taken!"
}
```

##### Response *code 400* the same Email
```
{
  "message": "Error: Email is already in use!"
}
```
