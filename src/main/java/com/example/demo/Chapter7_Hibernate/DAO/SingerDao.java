package com.example.demo.Chapter7_Hibernate.DAO;

import com.example.demo.Chapter7_Hibernate.Entities.Singer;

import java.util.List;

public interface SingerDao {
    List<Singer> findAll();
    List<Singer> findAllWithAlbum();
    Singer findById(Long id);
    Singer save(Singer singer);
    void delete(Singer singer);
}
