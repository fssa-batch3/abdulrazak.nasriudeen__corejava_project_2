# Reparo

[Milestones](https://github.com/fssa-batch3/abdulrazak.nasriudeen__corejava_project_2/milestones)

## Table of Contents

1. [Introduction](#introduction)
    - Purpose
    - Features
    - User Persona
2. [Prerequisites](#prerequisites)
    - Software Requirements
    - Database Setup
3. [Project Setup](#project-setup)
    - Java Project Creation
    - Reparo Dependencies
4. [Database](#database)
    - Entity-Relationship Diagram (ERD)
    - Database Tables
5. [Modules](#modules)
    - User Module
    - Workshop Module 
    - Vehicle Module
    - Booking Module 
    - Service Module 

6. [Validations](#validations)
    - Input Validations
    - User Validations
    - Workshop Validations
    - Vehicle Validations
    - Booking Validations
    - Service Validations 

7. [Testing](#testing)
    - Unit Testing
8. [Exception Handling](#exception-handling)
    - Common Error Messages
    - Exception Handling
9. [Future Improvements](#future-improvements)
    - Planned Features
    - Roadmap
10. [Resources](#resources)
    - External Libraries
    - References

## Introduction

### Purpose
The purpose of this application is to get a connection with the nearBy workhops and the desired user whenever he wants his vehicle to be serviced.

### Features

- Register, Login, Update, Delete User.
- Register, Login, Update, Search, Delete Workshop.
- add , get  Vehicle .
- Create , update , Search , get , Availability of Workshop , close Booking.
- Create , update ,  get ,  search Services.


### User Persona

- User
- Admin
- WorkShop

## Prerequisites

### Software Requirements

- Java Development Kit (JDK)
- MySQL Database Server
- Integrated Development Environment

### Database Setup

- Table scripts: [Script](database_setup.sql)

## Project Setup

### Java Project Creation

- Create a new Java project
- Set up a MySQL database

### Library Dependencies

- JDBC,
- MySQL Connector,
- JUnit,

## Database

### Entity-Relationship Diagram (ERD)
[![HbuUGIe.md.png](https://iili.io/HbuUGIe.md.png)](https://freeimage.host/i/HbuUGIe)

### Database Tables

#### Table: user. 

| Field           | Type          | Null | Key | Default           | Extra |
|-----------------|---------------|------|-----|-------------------|-------|
| id              | INT           | NO   | PRI | auto_increment    |       |
| name            | varchar(100)  | NO   |     |                   |       |
| number          | varchar(15)   | NO   |     |                   |       |
| password        | varchar(100)  | NO   |     |                   |       |
| email           | varchar(100)  | NO   |     |                   |       |
| profile_image   | varchar(2048) | NO   |     |                   |       |
| registered_date | timestamp     | NO   |     | CURRENT_TIMESTAMP |       |

#### Table: WorkShop.

| Field           | Type          | Null | Key | Default           | Extra |
|-----------------|---------------|------|-----|-------------------|-------|
| id              | INT           | NO   | PRI | auto_increment    |       |
| name            | varchar(100)  | NO   |     |                   |       |
| number          | varchar(15)   | NO   |     |                   |       |
| password        | varchar(100)  | NO   |     |                   |       |
| email           | varchar(100)  | NO   |     |                   |       |
| profile_image   | varchar(2048) | NO   |     |                   |       |
| registered_date | timestamp     | NO   |     | CURRENT_TIMESTAMP |       |
| address         | varchar(100)  | NO   |     |                   |       |
| city            | varchar(15)   | NO   |     |                   |       |
| state           | varchar(15)   | NO   |     |                   |       |
| workshop_type   | INT           | NO   |     |                   |       |

#### Table: Vehicle.

| Field           | Type          | Null | Key | Default           | Extra |
|-----------------|---------------|------|-----|-------------------|-------|
| id              | INT           | NO   | PRI | auto_increment    |       |
| model           | varchar(15)   | NO   |     |                   |       |
| company         | varchar(15)   | NO   |     |                   |       |
| Vehicle_number  | varchar(15)   | NO   |     |                   |       |
| year            | INT           | NO   |     |                   |       |
| user_id         | INT           | NO   | for |                   |       |
| vehicle_type    | INT           | NO   |     |                   |       |

#### Table: Booings.

| Field           | Type          | Null | Key | Default           | Extra |
|-----------------|---------------|------|-----|-------------------|-------|
| booking_id      | INT           | NO   | PRI | auto_increment    |       |
| vehicle_id      | INT           | NO   | for |                   |       |
| workshop_id     | INT           | yes  | for |                   |       |
| request_status  | TINYINT(1)    | NO   |     |                   |       |
| accept_status   | TINYINT(1)    | NO   |     |                   |       |
| is_live         | TINYINT(1)    | NO   |     |                   |       |
| problem         | varchar(45)   | NO   |     |                   |       |
| address         | varchar(45)   | NO   |     |                   |       |
| city            | varchar(15)   | NO   |     |                   |       |
| state           | varchar(15)   | NO   |     |                   |       |

#### Table: Services.

| Field        | Type          | Null | Key | Default        | Extra |
|--------------|---------------|------|-----|----------------|-------|
| services_id  | INT           | NO   | PRI | auto_increment |       |
| workshoop_id  | INT            | NO   | For |                |       |
| totalamount   | INT            | NO   |     |                |       |
| accept_status | tinyint(1)    | NO   |     |                |       |
| is_live       | tinyint(1)    | NO   |     |                |       |

#### Table: Service_List.

| Field              | Type          | Null | Key | Default        | Extra |
|--------------------|---------------|------|-----|----------------|-------|
| id          | INT           | NO   | PRI | auto_increment |       |
| service_id            | INT           | NO   |  for   |                |       |
| service_name       | varchar(20)  | NO   |     |                |       |
| price      | INT | NO   |     |                |       |


#### Table: Ratings.

| Field          | Type       | Null | Key | Default        | Extra |
|----------------|------------|------|-----|----------------|-------|
| id       | INT        | NO   | PRI | auto_increment |       |
| customer_id      | INT        | NO   |     |                |       |
| workshop_id     | INT        | NO   |     |                |       |
| service_id      | INT | NO   |     |                |       |
| feedback        | varchar(1000) | NO   |     |                |       |
| ratings        | INT | NO   |     |                |       |
| invite_message | TEXT       | NO   |     |                |       |

## Modules

### User Module :

- Add a user:
    - Allows to register new users.
- Update user details:
    - Enables to modify user information such as name, password, etc.
- Remove a user:
    - Allows to remove a user.
 
### Workshop Module :

- Add a Workshop:
    - Allows to register new Workshop.
- View Workshop details:
    - Display detailed information about a specific Workshop when selected.
- Update Workshop details:
    - Enables to modify Workshop information such as name, password, etc.
- Remove a Workshop:
    - Allows to remove a Workshop.

### vehicle Module :

- Add vehicle:
    - Allow user to add their vehicle when they wat a  service.
- View vehicles:
    - Display their vehicle .


### Booking Module :

- Cretes a booking 
    - Allow users to create a booking.
- View a booking:
    - user able to view their booking while they searching for a workshop 
- Update booking:
    - user able to  update their booking status.
- search workshops:
    - user able to  search a workshop near their location. 
- select Workshop :
    - Enables enables user to select a workshop near their location.

### service Module :

- Create a Service List :
    - Allow users to know what service ahould be done for their  vehicle.
- update Service List :
    - WorkShop able to update the price for the service needed.
- View Services : 
    - Enable user to view their service  details.


## Validations

### User Validations :

- String  Validation
- Number Validation
- password Validation
- User validation
- Profile Image Url Validation

### Vehicle  Validations :

- Vehicle Number Validation
- Vehicle Year Validation
- Vehicle Validation
- vehicle Type Validation
  
### Workshop Validations :

- Workshop type Validation
- address Validation
- Workshop Validation

### booking Validations :

- Booking Validation
- address validation
- problem Validation
 
