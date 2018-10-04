package com.ops.base.education.project.Service;
import com.ops.base.education.project.domain.Event;
import java.util.Collection;
import java.util.List;
public interface EventsService {
  public Event createEvent(Event event);
  public Collection<Event> getEvents(Long userId);
  public Event updateEvent(Long userId);
  public Event deleteEvent(Long userId);
  List<Event> getAllEvents();
}
