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
package com.example.demo.Chapter8_SpringJPA_Ex.service;

import com.example.demo.Chapter8_SpringJPA_Ex.view.SingerSummary;
import com.example.demo.Chapter8_SpringJPA_Ex.view.SingerSummaryRecord;

import java.util.stream.Stream;

/**
 * Created by iuliana.cosmina on 02/07/2022
 */
public interface SingerSummaryService {

    String ALL_SINGER_SUMMARY_JPQL_QUERY ="""
            select new com.apress.prospring6.eight.view.SingerSummary(
            s.firstName, s.lastName, a.title) from Singer s
            left join s.albums a
            where a.releaseDate=(select max(a2.releaseDate) from Album a2 where a2.singer.id = s.id)
            """;

    String ALL_SINGER_SUMMARY_RECORD_JPQL_QUERY ="""
            select s.firstName, s.lastName, a.title from Singer s
            left join s.albums a 
            where a.releaseDate=(select max(a2.releaseDate) from Album a2 where a2.singer.id = s.id)
            """;

    Stream<SingerSummary> findAll();
    Stream<SingerSummaryRecord> findAllAsRecord();
}
