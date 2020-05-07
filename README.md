spring-webflux-angular-reactjs
==========================

Using Functionnal programming to stream ETF Funds prices variations in real time using Spring Webflux in the backend and Angular/ReactJs in the front end.

Used APIs,

- Java 8
- Spring Boot 2.0.2 [spring-boot-starter-webflux]
- Spring Data Mongodb 2.0.7 [mongodb-reactive]
- Lombok 1.18.6
- Angular 4.2
- ReactJs 16.9


To start the backend project 
``` 
   localhost:8080/api/companies/transactions/stream/QAI
```

Basic steps to start the react project 

``` 
npm create-react-app react-frontend
npm install font-awesome --save
npm install bootstrap@4.1.3 --save
npm install @amcharts/amcharts4 --save
```




Basic steps to start the angular project 

``` 
ng new angular-frontend
npm install font-awesome --save
npm install bootstrap@4.1.3 --save
npm install jquery --save
npm install @amcharts/amcharts4 --save
ng generate component company
ng generate service company

```

> This project is in the development stage

