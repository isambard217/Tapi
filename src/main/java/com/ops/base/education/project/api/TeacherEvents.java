package com.ops.base.education.project.api;
import com.ops.base.education.project.Service.EventsService;
import com.ops.base.education.project.domain.Event;
import com.ops.base.education.project.dto.EventDtoIn;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/teacher_events")
public class TeacherEvents {
  private final EventsService eventsService;
  private final ModelMapper modelMapper;
  @Autowired
  public TeacherEvents(EventsService eventsService, ModelMapper modelMapper) {
    this.eventsService = eventsService;
    this.modelMapper = modelMapper;
  }
  @GetMapping
  public List<Event> getEvents(@RequestHeader String auth){
    return this.eventsService.getAllEvents();
  }
  @PutMapping
  public Event updateEvent(@RequestHeader String auth,@RequestBody EventDtoIn eventDtoIn){
    Event event = this.modelMapper.map(eventDtoIn, Event.class);
    return this.eventsService.updateEvent(event);
  }
  @DeleteMapping
  public boolean deleteEvent(@RequestHeader String auth, @RequestParam Long eventId){
    return this.eventsService.deleteEvent(eventId);
  }
}
