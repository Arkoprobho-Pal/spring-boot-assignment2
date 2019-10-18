package com.stackroute.muzix.service;

import com.stackroute.muzix.domain.Track;
import com.stackroute.muzix.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzix.repository.TrackRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TrackServiceTest {
    private Track track;
    //create mock for trackRepository
    @Mock
    private TrackRepository trackRepository;
    //Inject the mocks as dependencies into TrackServiceImpl
    @InjectMocks
    private TrackServiceImpl trackService;
    List<Track> list= null;
    @Before
    public void setTrack(){
        //initialising mock object
        MockitoAnnotations.initMocks(this);
        track=new Track();
        track.setTrackId(1);
        track.setTrackName("Sennorita");
        track.setComments("sung by camilla and Shawn Menddis, one of the best hits of 2019");
        list=new ArrayList<>();
        list.add(track);
    }
    @Test
    public void saveUserTestSuccess() throws TrackAlreadyExistsException {

        when(trackRepository.save((Track)any())).thenReturn(track);
        Track savedTrack = trackService.saveTrack(track);
        Assert.assertEquals(track,savedTrack);

        //verify here verifies that userRepository save method is only called once
        verify(trackRepository,times(1)).save(track);

    }

    @Test(expected = TrackAlreadyExistsException.class)
    public void saveTrackTestFailure() throws TrackAlreadyExistsException {
        when(trackRepository.save((Track)any())).thenReturn(null);
        Track savedTrack = trackService.saveTrack(track);
        System.out.println("savedUser" + savedTrack);
        Assert.assertEquals(track,savedTrack);

    }
    @Test
    public void getAllTrackDetails(){

        trackRepository.save(track);
        //stubbing the mock to return specific data
        when(trackRepository.findAll()).thenReturn(list);
        List<Track> tracklist = trackService.getAllTrackDetails();
        Assert.assertEquals(list,tracklist);
    }

}
