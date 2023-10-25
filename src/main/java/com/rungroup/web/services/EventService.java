package com.rungroup.web.services;

import com.rungroup.web.dtos.EventDto;

import java.util.List;

public interface EventService {
    void createEvent(Long clubId, EventDto eventDto);

    List<EventDto> findAllEvents();

    EventDto findEventById(long eventId);

    void updateEvent(EventDto eventDto);

    void deleteEventById(long eventId);
}
