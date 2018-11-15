# kuehne - People list

Examples of use:

```
localhost:8080/api/people
```
By default, the service will return the first 20 lines.
You can change this value:
```
localhost:8080/api/people?page_size=10
```

You can paginate the result:
```
localhost:8080/api/people?page=2
```

You can filter the results using the name of a person:
```
localhost:8080/api/people?name=homer
```


## Requirements

  * JDK 8+
  * Maven 3.3.9+
  * Node.js 8+
  * yarn

## Running this service

```bash
  >> mvn spring-boot:run
```
## Running the client
To run the client, cd into the ```client``` folder and run:

```bash
  >> yarn && yarn start
```
