# SDRM
> To run appliaction with frontend run gradle bootRun or ./gradlew bootRun

> To create new admin user please add user to db via query:
 ` insert into userdata (id, last_name, user_email, user_name, user_password)
values (9999,'adminlastname','admin@admin.pl','superadmin','$2a$12$QM3hbr4gtRYij2XWHCAZXukeNqyVAbp9V3WEoMX3wtHZb0D04xJJy');`
> 
### To generate jwt token please call API:
```
curl --location --request POST 'http://localhost:8081/api/login' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=4631F2B8996B009E5DC5E2029C6841D9' \
--data-raw '{
    "userName": "superadmin",
    "password": "superadmin"
}'
```
### you should receive token in response:
>> {"token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdXBlcmFkbWluIiwiZXhwIjoxNjQwMDU3MzcyfQ.AXX0mrW7uITNg06SfoZHI1x2t6vRRnWDm-kWzs_tExc"}
> 
### and now you can call API using token:
```java
curl --location --request GET 'http://localhost:8081/api/users' \
--header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdXBlcmFkbWluIiwiZXhwIjoxNjQwMDU3MzcyfQ.AXX0mrW7uITNg06SfoZHI1x2t6vRRnWDm-kWzs_tExc' \
--header 'Cookie: JSESSIONID=4631F2B8996B009E5DC5E2029C6841D9'
```

### and you should be able to get response with list of users:
```json
[
    {
        "username": "admin",
        "lastname": "admin",
        "email": "admin"
    },
    {
        "username": "Karol",
        "lastname": "Majster",
        "email": "karol91@gmail.com"
    },
    {
        "username": "karol91",
        "lastname": "Majster",
        "email": "karol91@gmail.com"
    },
    {
        "username": "superadmin",
        "lastname": "adminlastname",
        "email": "admin@admin.pl"
    }
]
```

SQL INSERTS

insert into userdata (id, last_name, user_email, user_name, user_password)
values (9999,'adminlastname','admin@admin.pl','superadmin','$2a$12$QM3hbr4gtRYij2XWHCAZXukeNqyVAbp9V3WEoMX3wtHZb0D04xJJy');
insert into userdata (id, last_name, user_email, user_name, user_password)
values (9998,'Majster','karol91@gmail.com','karol91','$2a$12$QM3hbr4gtRYij2XWHCAZXukeNqyVAbp9V3WEoMX3wtHZb0D04xJJy');

insert into userdata (id, last_name, user_email, user_name, user_password)
values (1,'wadas','michalwadas@onet.pl','michal','$2a$12$QM3hbr4gtRYij2XWHCAZXukeNqyVAbp9V3WEoMX3wtHZb0D04xJJy');

insert into userdata (id, last_name, user_email, user_name, user_password)
values (9999,'adminlastname','admin@admin.pl','superadmin','$2a$12$QM3hbr4gtRYij2XWHCAZXukeNqyVAbp9V3WEoMX3wtHZb0D04xJJy');
insert into userdata (id, last_name, user_email, user_name, user_password)
values (9998,'Majster','karol91@gmail.com','karol91','$2a$12$QM3hbr4gtRYij2XWHCAZXukeNqyVAbp9V3WEoMX3wtHZb0D04xJJy');

insert into userdata (id, last_name, user_email, user_name, user_password)
values (1,'wadas','michalwadas@onet.pl','michal','$2a$12$QM3hbr4gtRYij2XWHCAZXukeNqyVAbp9V3WEoMX3wtHZb0D04xJJy');


select * from userdata;

select * from reservation;


delete from reservation where reservation_id =1;

insert into reservation (reservation_id, reservation_create_date, reservation_end_date, reservation_name, reservation_space_id, reservation_start_date, user_id)
values (1,'2021-12-10 12:12:12', '2021-12-22', 'mich', 3,'2021-12-21', 1);

select * from company;

select * from room;

select * from space;

-- laczona tabela dla many to many
select * from reservation_space;



select * from space_reservation;


### tworzy sloty 10 slotow po 1h kazdy
### Create for example spot with time slots from 10 to 18
curl --location --request POST 'http://localhost:8081/api/manage/reservation' \
--header 'Content-Type: application/json' \
--data-raw '{
"reservationName": "Stolik 123",
"reservationStartDate": "2021-14-11T10:00:00.000Z",
"reservationEndDate": "2021-14-11T18:00:00.000Z",
"reservationSpaceId": 1,
"reservationCreateDate": "2021-12-10T15:12:12.123Z",
"userData":1
}'


### Book particular time slot from reservationId(which should be renamed to place/spotId)
curl --location --request PUT 'http://localhost:8081/api/reservation/book' \
--header 'Content-Type: application/json' \
--data-raw '{
"reservationId": 5,
"timeSlotId": 7,
"userId":1,
"bookingCreationTime": "2021-12-10T11:00:00.000Z",
"bookingCancelTime": null,
"bookPlace": true,
"additionalInfo": "No people around me please."

}'


get all bookings for particular user
curl --location --request GET 'http://localhost:8081/api/reservation/user/1/booked' \
--header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdXBlcmFkbWluIiwiZXhwIjoxNjQwNjQ4NTE2fQ.r0WS2ywxb2yBLKtHKk18MYGVRzHFQUregwVJSxZkXfU'

example response:
[
{
"bookedPlaceId": 3,
"reservationId": 2,
"timeSlot": "2022-02-11T10:00:00.000+0000",
"bookingCreationTime": "2021-12-10T11:00:00.000+0000",
"personalRequest": "No people around me please."
},
{
"bookedPlaceId": 4,
"reservationId": 2,
"timeSlot": "2022-02-11T11:00:00.000+0000",
"bookingCreationTime": "2021-12-10T11:00:00.000+0000",
"personalRequest": "No people around me please."
},
{
"bookedPlaceId": 5,
"reservationId": 2,
"timeSlot": "2022-02-11T12:00:00.000+0000",
"bookingCreationTime": "2021-12-10T11:00:00.000+0000",
"personalRequest": "No people around me please."
},
{
"bookedPlaceId": 6,
"reservationId": 2,
"timeSlot": "2022-02-11T13:00:00.000+0000",
"bookingCreationTime": "2021-12-10T11:00:00.000+0000",
"personalRequest": "No people around me please."
},
{
"bookedPlaceId": 7,
"reservationId": 2,
"timeSlot": "2022-02-11T14:00:00.000+0000",
"bookingCreationTime": "2021-12-10T11:00:00.000+0000",
"personalRequest": "No people around me please."
},
{
"bookedPlaceId": 8,
"reservationId": 2,
"timeSlot": "2022-02-11T15:00:00.000+0000",
"bookingCreationTime": "2021-12-10T11:00:00.000+0000",
"personalRequest": "No people around me please."
},
{
"bookedPlaceId": 21,
"reservationId": 20,
"timeSlot": "2022-02-11T10:00:00.000+0000",
"bookingCreationTime": "2021-12-10T11:00:00.000+0000",
"personalRequest": "No people around me please."
},
{
"bookedPlaceId": 12,
"reservationId": 11,
"timeSlot": "2022-02-11T10:00:00.000+0000",
"bookingCreationTime": "2021-12-10T11:00:00.000+0000",
"personalRequest": "No people around me please."
},
{
"bookedPlaceId": 48,
"reservationId": 47,
"timeSlot": "2022-02-11T10:00:00.000+0000",
"bookingCreationTime": "2021-12-10T11:00:00.000+0000",
"personalRequest": "No people around me please."
},
{
"bookedPlaceId": 49,
"reservationId": 47,
"timeSlot": "2022-02-11T11:00:00.000+0000",
"bookingCreationTime": "2021-12-10T11:00:00.000+0000",
"personalRequest": "No people around me please."
},
{
"bookedPlaceId": 50,
"reservationId": 47,
"timeSlot": "2022-02-11T12:00:00.000+0000",
"bookingCreationTime": "2021-12-10T11:00:00.000+0000",
"personalRequest": "No people around me please."
}
]

Here some additional curls:
 to get all areas which can be booked:
curl --location --request GET 'http://localhost:8081/api/manage/reservation/'
example response:
[
{
"reservationId": 2,
"reservationName": "Stolik 123",
"reservationStartDate": "2022-02-11T10:00:00.000+0000",
"reservationEndDate": "2022-02-11T18:00:00.000+0000",
"reservationSpaceId": 11,
"reservationCreateDate": "2021-12-27T01:36:51.419+0000",
"userData": {
"username": "superadmin",
"lastname": "adminlastname",
"email": "admin@admin.pl"
},
"timeSlots": [
{
"id": 9,
"timeSlot": "2022-02-11T16:00:00.000+0000",
"booked": false
},
{
"id": 10,
"timeSlot": "2022-02-11T17:00:00.000+0000",
"booked": false
},
{
"id": 3,
"timeSlot": "2022-02-11T10:00:00.000+0000",
"booked": true
},
{
"id": 4,
"timeSlot": "2022-02-11T11:00:00.000+0000",
"booked": true
},
{
"id": 5,
"timeSlot": "2022-02-11T12:00:00.000+0000",
"booked": true
},
{
"id": 6,
"timeSlot": "2022-02-11T13:00:00.000+0000",
"booked": true
},
{
"id": 7,
"timeSlot": "2022-02-11T14:00:00.000+0000",
"booked": true
},
{
"id": 8,
"timeSlot": "2022-02-11T15:00:00.000+0000",
"booked": true
}
]
},
{
"reservationId": 11,
"reservationName": "Stolik 123",
"reservationStartDate": "2022-02-11T10:00:00.000+0000",
"reservationEndDate": "2022-02-11T18:00:00.000+0000",
"reservationSpaceId": 11,
"reservationCreateDate": "2021-12-27T01:43:25.026+0000",
"userData": {
"username": "superadmin",
"lastname": "adminlastname",
"email": "admin@admin.pl"
},
"timeSlots": [
{
"id": 13,
"timeSlot": "2022-02-11T11:00:00.000+0000",
"booked": false
},
{
"id": 14,
"timeSlot": "2022-02-11T12:00:00.000+0000",
"booked": false
},
{
"id": 15,
"timeSlot": "2022-02-11T13:00:00.000+0000",
"booked": false
},
{
"id": 16,
"timeSlot": "2022-02-11T14:00:00.000+0000",
"booked": false
},
{
"id": 17,
"timeSlot": "2022-02-11T15:00:00.000+0000",
"booked": false
},
{
"id": 18,
"timeSlot": "2022-02-11T16:00:00.000+0000",
"booked": false
},
{
"id": 19,
"timeSlot": "2022-02-11T17:00:00.000+0000",
"booked": false
},
{
"id": 12,
"timeSlot": "2022-02-11T10:00:00.000+0000",
"booked": true
}
]
}]

but if we would like to get more info about timeslot: then:
curl --location --request GET 'http://localhost:8081/api/manage/reservation/2/timeslot/3'
Response - only detailed info:
{
"id": 3,
"timeSlot": "2022-02-11T10:00:00.000+0000",
"bookingCreationTime": "2021-12-10T11:00:00.000+0000",
"bookingCancelTime": null,
"personalRequest": "No people around me please.",
"userDtoData": {
"username": "superadmin",
"lastname": "adminlastname",
"email": "admin@admin.pl"
},
"booked": true
}

Batch reservation for admin, eg.
I have picked whole week and want to create time slots for particular table from 10 to 20
in one request
