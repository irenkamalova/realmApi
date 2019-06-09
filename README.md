# Realm Management API

The APIs specified below support the creation and retrieval of representations of a User
Realm.

## Installation

Package jar (it also will run all tests) then execute it:

```bash
mvn clean package
cd target realm-1.0-SNAPSHOT.jar
java -jar
```

## Usage

[http://localhost:8080/](http://localhost:8080) will redirect you to swagger-api.html page, where you can find methods GET and POST to work with app.

You can also use curl to execute requests:

## POST

### Success
curl -i -X POST "http://localhost:8080/service/user/realm" -H "accept: application/xml" -H "Content-Type: application/xml" -d "<?xml version=\"1.0\" encoding=\"UTF-8\"?><realm name=\"name\">\t<description>string</description></realm>"

HTTP/1.1 201
Content-Type: application/xml;charset=UTF-8
Transfer-Encoding: chunked
Date: Sun, 09 Jun 2019 09:43:42 GMT

```
<realm id="5" name="name">
<description>string</description>
<key>secretKey</key>
</realm>
```

curl -i -X POST "http://localhost:8080/service/user/realm" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"description\": \"string\", \"name\": \"alias\"}"

HTTP/1.1 201
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Sun, 09 Jun 2019 09:52:29 GMT

```
{"id":13,"name":"alias","description":"string","key":"secretKey"}
```

### Error Duplicated

curl -i -X POST "http://localhost:8080/service/user/realm" -H "accept: application/xml" -H "Content-Type: application/xml" -d "<?xml version=\"1.0\" encoding=\"UTF-8\"?><realm name=\"name\">\t<description>string</description></realm>"

HTTP/1.1 400
Content-Type: application/xml;charset=UTF-8
Transfer-Encoding: chunked
Date: Sun, 09 Jun 2019 09:45:33 GMT
Connection: close
```
<error><code>DuplicateRealmName</code></error>
```

curl -i -X POST "http://localhost:8080/service/user/realm" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"description\": \"string\", \"name\": \"string\"}"

HTTP/1.1 400
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Sun, 09 Jun 2019 09:50:21 GMT
Connection: close

```
{"code":"DuplicateRealmName"}
```

### Error Argument

curl -i -X POST "http://localhost:8080/service/user/realm" -H "accept: application/xml" -H "Content-Type: application/xml" -d "<?xml version=\"1.0\" encoding=\"UTF-8\"?><realm name=\"\">\t<description>string</description></realm>"

HTTP/1.1 400
Content-Type: application/xml;charset=UTF-8
Transfer-Encoding: chunked
Date: Sun, 09 Jun 2019 09:48:02 GMT
Connection: close

```
<error><code>InvalidRealmName</code></error>
```

curl -i -X POST "http://localhost:8080/service/user/realm" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"description\": \"string\", \"name\": \"\"}"

HTTP/1.1 400
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Sun, 09 Jun 2019 09:51:33 GMT
Connection: close

```
{"code":"InvalidRealmName"}
```

## GET

### Success

curl -i -H "Accept: application/json" http://localhost:8080/service/user/realm/1

HTTP/1.1 200
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Sun, 09 Jun 2019 09:15:53 GMT

```
{"id":1,"name":"123","description":"123","key":"secretKey"}
```

curl -i -H "Accept: application/xml" http://localhost:8080/service/user/realm/1

HTTP/1.1 200
Content-Type: application/xml;charset=UTF-8
Transfer-Encoding: chunked
Date: Sun, 09 Jun 2019 09:16:03 GMT

```
<realm id="1" name="123"><description>123</description><key>secretKey</key></realm>
```

### Error Not Found

curl -i -H "Accept: application/json" http://localhost:8080/service/user/realm/1

HTTP/1.1 404
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Sun, 09 Jun 2019 09:32:18 GMT

```
{"code":"RealmNotFound"}
```
curl -i -H "Accept: application/xml" http://localhost:8080/service/user/realm/1

HTTP/1.1 404
Content-Type: application/xml;charset=UTF-8
Transfer-Encoding: chunked
Date: Sun, 09 Jun 2019 09:25:58 GMT

```
<error><code>RealmNotFound</code></error>
```

### Invalid Argument

curl -i -H "Accept: application/json" http://localhost:8080/service/user/realm/ry
HTTP/1.1 400
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Sun, 09 Jun 2019 09:33:09 GMT
Connection: close

```
{"code":"InvalidArgument"}
```

curl -i -H "Accept: application/xml" http://localhost:8080/service/user/realm/ry

HTTP/1.1 400
Content-Type: application/xml;charset=UTF-8
Transfer-Encoding: chunked
Date: Sun, 09 Jun 2019 09:33:35 GMT
Connection: close

```
<error><code>InvalidArgument</code></error>
```

## Instruments

* Spring Boot
* Spring JPA
* H2 embedded
* Swagger API & Swagger UI
* Jackson XML
* Lombok

## Notes

It's better to add more error code (for example, 500 Internel Server Error),
add error codes to one enum or interface-constants file