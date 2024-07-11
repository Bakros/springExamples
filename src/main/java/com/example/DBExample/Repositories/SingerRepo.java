package com.example.DBExample.Repositories;

import com.example.DBExample.Entities.Singer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SingerRepo extends JpaRepository<Singer,Long> {
}
