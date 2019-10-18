package com.stackroute.muzix.service;

import com.stackroute.muzix.domain.Track;
import com.stackroute.muzix.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzix.exceptions.TrackNotFoundException;

import java.util.List;

public interface TrackService {
    public Track saveTrack(Track track) throws TrackAlreadyExistsException;
    public Track updateTrackComment(Track track) throws TrackNotFoundException;
    public Track removeTrack(Track track) throws TrackNotFoundException;
    public Iterable<Track> getAllTrackDetails();
    public  List<Track> getTrackByName(String trackName) throws TrackNotFoundException;
    public Track autoSaveCommandLine(Track track);
    public Track autoSaveListener(Track track);
}
