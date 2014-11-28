# REST Service
Simple REST JSON/XML web service.


## Usage
To execute `ping` method send the following HTTP request

```
POST /glu-service/handler HTTP/1.1
User-Agent: curl/7.37.1
Host: localhost:8080
Content-Type: application/json
Accept: application/json
Content-Length: 11
```

You wil receive something like that

```
HTTP/1.1 200 OK
Set-Cookie: USER_ID=40ab2e72-36e5-4273-b29a-d2439a027e27
Content-Type: application/json
Transfer-Encoding: chunked
Server: Jetty(6.1.25)
{"count":"54"}%  
```

Remember the `USER_ID=40ab2e72-36e5-4273-b29a-d2439a027e27` to append to all your next requests.
If you change `Accept` to `application/xml` you will receive xml as a result


## Build
Run `./gradlew`(NIX) or `gradlew.bat`(Windows) from project root.
No gradle installation required.
To package service in  `war` simply run `./gradlew war`

## API

* Method `ping` - Post the following json string `{"ping":{}}` - response will be `{"count":<number of calls>}`

## Prerequisites

You need to have valid Redis server running.
You can change host and port of target Redis server in `src/main/resources/application.conf`.
If you don`t have/don`t want Redis server you can build service with `InMemoryModule.java` instead `RedisModule.java` - 
change in `src/main/java/guice/ServiceServletConfig.java`.