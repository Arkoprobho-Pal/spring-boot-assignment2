package com.stackroute.muzix.service;

import com.stackroute.muzix.domain.Track;
import com.stackroute.muzix.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzix.exceptions.TrackNotFoundException;
import com.stackroute.muzix.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
@Profile("Dummy")
@Service
public class TrackDummyServiceImpl implements TrackService {
    private TrackRepository trackRepository;
    @Autowired
    public TrackDummyServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public List<Track> getTrackByName(String trackName) throws TrackNotFoundException{
        List<Track> list=trackRepository.findByName(trackName);
        if (list.isEmpty())
            throw new TrackNotFoundException("Track with the name "+trackName+" is not found");
        else
            return list;//printing the tracks of same names
    }

    @Override
    public Track autoSaveCommandLine(Track track) {
        Track track1;
        track1=trackRepository.save(track);
        return track1;
    }

    @Override
    public Track autoSaveListener(Track track) {
        Track track1;
        track1=trackRepository.save(track);
        return track1;
    }

    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistsException {
        if (trackRepository.existsById(track.getTrackId())){
            throw new TrackAlreadyExistsException("Track Already Exists in Dummy");
        }
        Track savedTrack=trackRepository.save(track);
        if (savedTrack==null){
            throw new TrackAlreadyExistsException("Track Already Exists in Dummy");
        }
        return savedTrack;
    }



    @Override
    public Track removeTrack(Track track) throws TrackNotFoundException {
        if(!trackRepository.existsById(track.getTrackId())){
            throw new TrackNotFoundException("Track Not Found in Dummy");
        }
        trackRepository.deleteById(track.getTrackId());
        // trackRepository.delete(track);
        return  track;
    }

    @Override
    public List<Track> getAllTrackDetails() {
        return trackRepository.findAll();
    }
    @Override
    public Track updateTrackComment(Track track) {
        Track updateTrack=trackRepository.findById(track.getTrackId()).get();
        updateTrack.setComments(track.getComments());
        trackRepository.save(updateTrack);
        return updateTrack;
    }
}
