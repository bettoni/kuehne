# kuehne - People list

Examples of use:

```
localhost:8080/people
```
By default, the service will return the first 20 lines.
You can change this value:
```
localhost:8080/people?page_size=10
```

You can paginate the result:
```
localhost:8080/people?page=2
```

You can filter the results using the name of a person:
```
localhost:8080/people?name=homer
```


## Requirements

  * JDK 8+
  * Maven 3.3.9+

## Running this service

```bash
  >> mvn spring-boot:run
```