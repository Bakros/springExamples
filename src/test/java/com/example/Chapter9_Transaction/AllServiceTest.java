/*
Freeware License, some rights reserved

Copyright (c) 2023 Iuliana Cosmina

Permission is hereby granted, free of charge, to anyone obtaining a copy 
of this software and associated documentation files (the "Software"), 
to work with the Software within the limits of freeware distribution and fair use. 
This includes the rights to use, copy, and modify the Software for personal use. 
Users are also allowed and encouraged to submit corrections and modifications 
to the Software for the benefit of other users.

It is not allowed to reuse,  modify, or redistribute the Software for 
commercial use in any way, or for a user's educational materials such as books 
or blog articles without prior permission from the copyright holder. 

The above copyright notice and this permission notice need to be included 
in all copies or substantial portions of the software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS OR APRESS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package com.example.Chapter9_Transaction;


import com.example.demo.Chapter9_Transaction.config.TransactionCfg;
import com.example.demo.Chapter9_Transaction.entities.Album;
import com.example.demo.Chapter9_Transaction.ex.TitleTooLongException;
import com.example.demo.Chapter9_Transaction.services.AllService;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.PersistenceException;
import org.hibernate.cfg.Environment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.jdbc.SqlMergeMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Created by iuliana.cosmina on 19/07/2022
 */
@Testcontainers
@SqlMergeMode(SqlMergeMode.MergeMode.MERGE)
@Sql({ "classpath:testcontainers/drop-schema.sql", "classpath:testcontainers/create-schema.sql" })
@SpringJUnitConfig(classes = {AllServiceTest.TestContainersConfig.class})
public class AllServiceTest extends  TestContainersBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(AllServiceTest.class);

    @Autowired
    AllService service;

    @Test
    @DisplayName("should return all singers and albums")
    void testFindAll(){
       var singers = service.findAllWithAlbums().peek(s -> {
           LOGGER.info(s.toString());
           if (s.getAlbums() != null) {
               s.getAlbums().forEach(a -> LOGGER.info("\tAlbum:" + a.toString()));
           }
       }).toList();
        assertEquals(3, singers.size());
    }

    @Test
    @SqlGroup({
            @Sql(scripts = {"classpath:testcontainers/add-nina.sql"},
                    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
    })
    @DisplayName("should perform a rollback because PersistenceException")
    void testRollbackRuntimeUpdate() {
        var singer = service.findByIdWithAlbums(5L).orElse(null);
        assertNotNull(singer);

        singer.setFirstName("Eunice Kathleen");
        singer.setLastName("Waymon");

        var album = new Album();
        album.setTitle("Little Girl Blue");
        album.setReleaseDate(LocalDate.of(1959,2, 20));
        album.setSinger(singer);
        var albums = Set.of(album);

        assertThrows(PersistenceException.class ,
                () -> service.saveSingerWithAlbums(singer, albums),
                "PersistenceException not thrown!");

        var nina = service.findByIdWithAlbums(5L).orElse(null);
        assertAll( "nina was not updated" ,
                () -> assertNotNull(nina),
                () -> assertNotEquals("Eunice Kathleen", nina.getFirstName()),
                () -> assertNotEquals("Waymon", nina.getLastName())
        );
    }

    @Test
    @SqlGroup({
            @Sql(scripts = {"classpath:testcontainers/add-nina.sql"},
                    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
    })
    @DisplayName("should perform a rollback because TitleTooLongException")
    void testRollbackCheckedUpdate() {
        var singer = service.findByIdWithAlbums(5L).orElse(null);
        assertNotNull(singer);

        singer.setFirstName("Eunice Kathleen");
        singer.setLastName("Waymon");

        var album = new Album();
        album.setTitle(""" 
            Sit there and count your fingers
            What can you do?
            Old girl you're through
            Sit there, count your little fingers
            Unhappy little girl blue
            """);
        album.setReleaseDate(LocalDate.of(1959,2, 20));
        album.setSinger(singer);
        var albums = Set.of(album);

        assertThrows(TitleTooLongException.class ,
                () -> service.saveSingerWithAlbums(singer, albums),
                "TitleTooLongException not thrown!");

        var nina = service.findByIdWithAlbums(5L).orElse(null);
        assertAll( "nina was not updated" ,
                () -> assertNotNull(nina),
                () -> assertNotEquals("Eunice Kathleen", nina.getFirstName()),
                () -> assertNotEquals("Waymon", nina.getLastName())
        );
    }

    @Test
    @DisplayName("should count singers")
    void testCount() {
        var singers = service.findAllWithAlbums().collect(Collectors.toSet());
        var count = service.countSingers();

        assertEquals(count, singers.size() );
    }

    @Configuration
    @Import(TransactionCfg.class)
    public static class TestContainersConfig {
        @Autowired
        Properties jpaProperties;

        @PostConstruct
        public void initialize() {
            jpaProperties.put(Environment.FORMAT_SQL, true);
            jpaProperties.put(Environment.USE_SQL_COMMENTS, true);
            jpaProperties.put(Environment.SHOW_SQL, true);
            jpaProperties.put(Environment.STATEMENT_BATCH_SIZE, 30);
        }
    }
}
