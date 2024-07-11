# SpringExamples
<h3>Spring Examples with experiments and more.</h3>

<b>Example 1 - Package HTMLTable</b>
<br/>
Retrieve a List from HTML and then display it.
<br/>
<b>Description</b>: URL <a href="http://localhost:8080/students">http://localhost:8080/students </a> shows an HTML table with Students data, it sends the data to the server and then 
that information is mapped to a List<Student> object, after that the data is displayed in a Thymeleaf template.

<h3>package com.example.demo.Chapter3</h3>
<b>Package Autowired</b> <br/>
Title > Using Constructor Injection
This is an example of how @Autowired is not need it
when there is only one constructor.
<br>
Also it shows that @Autowired is mandatory on Setter Inyection.
Otherwise, the setter is not executed and the Bean is never inyected.

<h3>Package com.example.demo.Chapter6.JDBCTemplate.records</h3>
Permite levantar una DB embebida H2 a través de la clase EmbeddedDatabaseBuilder.
Utiliza otro proveedor como ConnectionPool (no Hikari).
Está pensado para levantar de forma fácil BD embebidas.

<h3>Package com.example.EmbeddedBD.DB</h3>
Permite levantar una BD H2 embebida con Spring Boot utilizando DataSourceBuilder. 
Por defecto utiliza Hiraki como ConnectionPool.

<h3>Package com.example.demo.Chapter7_Hibernate</h3>
Ejemplos con Hibernate. Se utiliza una MariaBD instalada de forma local (localhost) 

<h3>Package com.example.Chapter6 (Testing)</h3> 
Ejemplo de uso de testcontainers con MariaDB.

<h3>Package com.example.demo.Chapter6.EmbeddedH2Hikari</h3>
Ejemplo de como levantar una BD H2 embebida ocupando HikariConfig 
y HikariDataSource para levantar el DataSource asociado. Este ejemplo
también permite ejecutar algunos scripts que permiten crear el schema 
y poblar de datos la BD. En términos prácticos hace lo mismo que "com.example.demo.Chapter6.JDBCTemplate.records" 
pero con otro proveedor de DataSource. 