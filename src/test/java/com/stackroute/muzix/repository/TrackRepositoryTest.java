package com.stackroute.muzix.repository;

import com.stackroute.muzix.domain.Track;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataMongoTest
public class TrackRepositoryTest {
    @Autowired
    private TrackRepository trackRepository;
    private Track track;
    @Before
    public void setTrack(){
        track=new Track();
        track.setTrackId(7);
        track.setTrackName("Jeet");
        track.setComments("A great Musical and Vocal");

    }
    @Test
    public void testSaveTrack(){
        trackRepository.save(track);
        Track fetchTrack = trackRepository.findById(track.getTrackId()).get();
        Assert.assertEquals(7,fetchTrack.getTrackId());
    }
    @Test
    public void testSaveTrackFailure(){
        Track testTrack = new Track(8,"Despasito","sobe sobe sito despasito.. oooolalaa");
        trackRepository.save(track);
        Track fetchTrack = trackRepository.findById(track.getTrackId()).get();
        Assert.assertNotSame(testTrack,track);
    }
    @Test
    public void testGetAllTrackDetails(){
        Track t = new Track(8,"Despasito","sobe sobe sito despasito.. oooolalaa");
        Track t1 = new Track(7,"Jeet","A great Musical and Vocal");
        trackRepository.save(t1);
        trackRepository.save(t);

        List<Track> list = trackRepository.findAll();
        Assert.assertEquals("Jeet",list.get(0).getTrackName());




    }

}
