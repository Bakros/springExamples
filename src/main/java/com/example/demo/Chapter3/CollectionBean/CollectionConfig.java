package com.example.demo.Chapter3.CollectionBean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CollectionConfig {
    @Bean
    public List<Song> list(){
        return List.of(
                new Song("Not the end"),
                new Song("Rise Up")
        );
    }

    // If there isn't singles beans @Autowired try to Inject the List<Song> but
    // like there are 2 of them it gets confused and throws an error.
    // That is why the following piece of code is commented.
    /*
    @Bean
    public List<Song> list2(){
        return List.of(
                new Song("Not the end"),
                new Song("Rise Up")
        );
    }*/

    @Bean
    public Song song1(){
      return new Song("Here's to hoping");
    }
    @Bean
    public Song song2(){
        return new Song("Wishing the best for you");
    }
    @Bean
    public Song song3(){
        return new Song("Wishing the best for you");
    }

}
