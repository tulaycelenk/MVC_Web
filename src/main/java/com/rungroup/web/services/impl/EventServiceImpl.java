package com.rungroup.web.services.impl;

import com.rungroup.web.customexceptions.ClubNotFoundException;
import com.rungroup.web.dtos.EventDto;
import com.rungroup.web.models.Club;
import com.rungroup.web.models.Event;
import com.rungroup.web.repositories.ClubRepository;
import com.rungroup.web.repositories.EventRepository;
import com.rungroup.web.services.EventService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.rungroup.web.mappers.EventMapper.mapToEvent;
import static com.rungroup.web.mappers.EventMapper.mapToEventDto;

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

    @Override
    public List<EventDto> findAllEvents() {
        List<Event> events= eventRepository.findAll();
        return events.stream()
                .map((event)->mapToEventDto(event))
                .collect(Collectors.toList());
    }
    @Override
    public EventDto findEventById(long eventId) {
        Optional<Event> event= eventRepository.findById(eventId);
        if(event.isPresent())
            return mapToEventDto(event.get());
        event.orElseThrow(() ->  new ClubNotFoundException("Event is not found "));
        return new EventDto();
    }

    @Override
    public void updateEvent(EventDto eventDto) {
       Event event =mapToEvent(eventDto);
       eventRepository.save(event);
    }

    @Override
    public void deleteEventById(long eventId) {
        eventRepository.deleteById(eventId);
    }


}
