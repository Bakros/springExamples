package com.example.Chapter7;

import org.springframework.test.context.jdbc.Sql;
import org.

@Testcontainers
@Sql({ "classpath:testcontainers/drop-schema.sql", "classpath:testcontainers/createschema.
        sql" })
        @SpringJUnitConfig(classes = {HibernateConfig.class})
public class HibernateTest {
}
