package com.rungroup.web.services.impl;

import com.rungroup.web.dtos.EventDto;
import com.rungroup.web.models.Club;
import com.rungroup.web.models.Event;
import com.rungroup.web.repositories.ClubRepository;
import com.rungroup.web.repositories.EventRepository;
import com.rungroup.web.services.EventService;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {
    private EventRepository eventRepository;
    private ClubRepository clubRepository;

    public EventServiceImpl(EventRepository eventRepository, ClubRepository clubRepository ){
        this.clubRepository=clubRepository;
        this.eventRepository=eventRepository;
    }
    @Override
    public void createEvent(Long clubId, EventDto eventDto) {

        Club club = clubRepository.findById(clubId).get();
        Event event= mapToEvent(eventDto);
        event.setClub(club);
        eventRepository.save(event);
    }


    private Event mapToEvent(EventDto eventDto){
        return Event.builder()
                .id(eventDto.getId())
                .name(eventDto.getName())
                .startTime(eventDto.getStartTime())
                .endTime(eventDto.getEndTime())
                .type(eventDto.getType())
                .photoUrl(eventDto.getPhotoUrl())
                .createdOn(eventDto.getCreatedOn())
                .updatedOn(eventDto.getUpdatedOn())
                .build();
    }

}
