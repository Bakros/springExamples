package com.example.DBExample.Services;

import com.example.DBExample.Entities.Singer;
import com.example.DBExample.Repositories.SingerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class AllServices {

    @Autowired
    SingerRepo singerRepo;

    public Optional<Singer> findOne(Long id){

        Singer singer = new Singer();
        singer.setBirthDate(LocalDate.of(2020, 1, 8));
        singer.setFirstName("Seba");
        singer.setLastName("H");

        singerRepo.save(singer);

        return singerRepo.findById(id);
    }

}
