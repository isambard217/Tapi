package com.ops.base.education.project.api;
import com.ops.base.education.project.Service.EventsService;
import com.ops.base.education.project.domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/teacher_events")
public class TeacherEvents {
  private final EventsService eventsService;
  @Autowired
  public TeacherEvents(EventsService eventsService) {
    this.eventsService = eventsService;
  }
  @GetMapping
  public List<Event> getEvents(@RequestHeader String auth){
    return this.eventsService.getAllEvents();
  }
}
