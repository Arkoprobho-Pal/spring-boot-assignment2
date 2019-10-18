package com.stackroute.muzix.seed;

import com.stackroute.muzix.domain.Track;
import com.stackroute.muzix.service.TrackService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource("application.properties")
public class ListenerSeed implements ApplicationListener<ContextRefreshedEvent> {
    TrackService trackService;
    @Value("${id1}")
    private int trackId;
    @Value("${name1}")
    private String trackName;
    @Value("${comment1}")
    private String comments;

    public ListenerSeed(TrackService trackService) {
        this.trackService = trackService;
    }
    @Autowired
    Environment environment;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
       // trackService.autoSaveListener(new Track(3,"Booter Bhobiisyot","Kartick Da!! is LOVE"));


    trackService.autoSaveListener(new Track(trackId,trackName,comments));
   // trackService.autoSaveListener(new Track());

    }
}
