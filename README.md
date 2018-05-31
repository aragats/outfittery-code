# Tech Challenge


## Build and Run

The application is written in Java with Spring-Boot and Swagger-UI to easier explore API.

Build: mvn package

Run: java -jar JAR_FILE.jar


Here are the Endpoints and some example responses:
***

## Create a stylist

Endpoint: `/api/v1/stylist`

### Request

Method: `POST`

Body:

```
{
  "name": "Anna",
  "state": "ROOKIE"
}
```

### Response

Body:
```
{
  "name": "Anna",
  "state": "ROOKIE"
}
```

## Get a stylist

Endpoint: `/api/v1/stylists/{id}`

### Request

Method: `GET`

### Response

Body:
```
{
  "name": "Max",
  "state": "READY_TO_STYLE"
}
```



## Delete a stylist

Endpoint: `/api/v1/stylists/{id}`

### Request

Method: `DELETE`



## Update a stylist state

Endpoint: `/api/v1/stylists/{stylistId}/{styleState}`

### Request

Method: `PUT`

StyleState: `READY_TO_STYLE`



## Get list of available time slots for a given time slot range

Endpoint: `/api/v1/stylists/availability`

### Request

Method: `Post`

Body:

TODO 
```
{
  "from": "2018-06-04T08:00:00.000Z",
  "to": "2018-06-04T19:00:00.000Z"
}
```

### Response

Body:
```
[
  {
    "from": "2018-06-04T08:00Z",
    "to": "2018-06-04T08:30Z"
  },
  {
    "from": "2018-06-04T08:30Z",
    "to": "2018-06-04T09:00Z"
  },
  {
    "from": "2018-06-04T09:00Z",
    "to": "2018-06-04T09:30Z"
  },
  {
    "from": "2018-06-04T09:30Z",
    "to": "2018-06-04T10:00Z"
  }
  ]
```




## Make a reservation

Endpoint: `/api/v1/stylists/reservation`

### Request

Method: `Post`

Body:

TODO 
```
{
  "comment": "Comment",
  "customer": "Customer",
  "timeslot": {
    "from": "2018-06-04T08:00:00.000Z",
    "to": "2018-06-04T08:30:00.000Z"
  }
}
```



## Notes and Assumptions


1. The implementation does not fully cover the specification. It has a lot of limitations. 
2. I did not implement a state machine. The stylist just has different types that can be change through API.






