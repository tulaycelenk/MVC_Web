package com.rungroup.web.controllers;

import com.rungroup.web.dtos.ClubDto;
import com.rungroup.web.dtos.EventDto;
import com.rungroup.web.models.Event;
import com.rungroup.web.services.ClubService;
import com.rungroup.web.services.EventService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EventController {
    private EventService eventService;
    private ClubService clubService;

    public EventController(EventService eventService,ClubService clubService) {
        this.clubService = clubService;
        this.eventService = eventService;
    }
    @GetMapping("/events")
    public String eventList(Model model){
        List<EventDto> events = eventService.findAllEvents();
        model.addAttribute("events", events);
        return "events-list";
    }
    @GetMapping("/events/{eventId}")
    public String eventsDetail(@PathVariable("eventId") Long eventId,Model model ){
        EventDto eventDto = eventService.findEventById(eventId);
        model.addAttribute("event", eventDto);
        return "events-detail";
    }

    @GetMapping("/events/{clubId}/new")
    public String createEventForm(@PathVariable("clubId") Long clubId, Model model){
        Event event = new Event();
        ClubDto club= clubService.findClubById(clubId);
        model.addAttribute("event",event);
        model.addAttribute("club",club);
        return "clubs-create";
    }
    @PostMapping("/events/{clubId}")
    public String createEvent(@PathVariable("clubId") Long clubId, @ModelAttribute("event") EventDto eventDto,
                              BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("event",eventDto);
            return "events-edit";
        }
        eventService.createEvent(clubId, eventDto);
        return "redirect:/clubs/"+ clubId;
    }
    @GetMapping("/events/{eventId}/edit")
    public String editEventForm(@PathVariable("eventId") long eventId, Model model){
        EventDto eventDto= eventService.findEventById(eventId);
        model.addAttribute("event",eventDto);
        return "events-edit";
    }

    //get the club id and update it
    //ModelAttribute retrieves a "club" object from the model
    @PostMapping("/events/{eventId}/edit")
    public String updateEvent(@ModelAttribute("eventId") Long eventId,
                             @Valid @ModelAttribute("event") EventDto event,
                             BindingResult result,Model model ){
        if(result.hasErrors()){
            model.addAttribute("event",event);
            return "events-edit";
        }
        EventDto eventDto=eventService.findEventById(eventId);
        event.setId(eventId);
        event.setClub(eventDto.getClub());
        //clubDto is updated with the provided clubId
        eventService.updateEvent(eventDto);
        return "redirect:/events";
    }

    @GetMapping("/events/{eventId}/delete")
    public String deleteEvent(@PathVariable("eventId") Long eventId,Model model){
        eventService.deleteEventById(eventId);
        return "redirect:/events";
    }

}
