package com.example.demo.Chapter3.CollectionBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import java.util.List;
import static java.lang.System.out;

@Component
public class CollectingBean {
   // @Autowired @Qualifier("list")    //This annotation search for an specific Bean.
    @Autowired //This annotation rescue all the Bean of type "Song" and collect them into the List. But it doesn't recover the Bean with List<Song>
    List<Song> songList;
    public void printCollections(){
        songList.forEach(s -> out.println(s.getTitle()));
    }
}
