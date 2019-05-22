DualDBConfiguration application I have configured with two different databases how we work in real time applications.
Here I have used one database is embedded database that has been provided by SpringBoot the name called H2 and other database I have configured with IBM Informix.
Similary you can configure with any other database with minimal changes on top of this application.

Below are the list of modules which I have selected while creating the SpringBoot application from initializer.
```
    1. Web
    2. H2
    3. Jersey
    4. JPA
```
    
On top of the above modules, I have added the below dependecy to support IBM informix.
```xml
                <dependency>
			<groupId>com.ibm.informix</groupId>
			<artifactId>jdbc</artifactId>
			<version>4.10.8.1</version>
		</dependency>
```
To know how to configure general Embedded H2 Database in Springboot application you can refer this link. https://github.com/Nallamachu/EmbeddedH2Database/tree/master/EmbeddedH2Database

But, there are slightly changes when compare to that application and the new DualDBConfiguration application. But, you can refer the same link to create the table and in H2 database.

Before configuring the IBM Informix database into this application, we need to create a table in Informix Database. You can use the below queries to create the table.

```sql
DROP TABLE informix.todo_items;

CREATE TABLE informix.todo_items
(
   id           serial         NOT NULL,
   name         varchar(50)    NOT NULL,
   description  varchar(200),
   todo_date    date,
   status       varchar(20)    NOT NULL
)
EXTENT SIZE 32
NEXT SIZE 32
LOCK MODE PAGE;

ALTER TABLE informix.todo_items
   ADD CONSTRAINT PRIMARY KEY (id)
   CONSTRAINT u100_1;

GRANT SELECT, INDEX, DELETE, UPDATE, INSERT ON informix.todo_items TO public;

COMMIT;
```

You can check the application.configuration file and Two different config files how I have configured different database configuration and accessing them.
