package com.ops.base.education.project.Service;
import com.ops.base.education.project.Repository.EventsRepository;
import com.ops.base.education.project.domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Service
public class ApiUsersEventsService implements EventsService {
  private EventsRepository eventsRepository;
  private ApiUsersService apiUsersService;
  @Autowired
  public ApiUsersEventsService(EventsRepository eventsRepository,
                               ApiUsersService apiUsersService) {
    this.eventsRepository = eventsRepository;
    this.apiUsersService = apiUsersService;
  }
  @Override
  public Event createEvent(Event event) {
    return this.eventsRepository.save(event);
  }
  @Override
  public Collection<Event> getEvents(Long userId) {
    return this.apiUsersService.getApiUserById(userId).getEvents();
  }
  @Override
  public Event updateEvent(Event event) {
    Event eventToUpdate = this.eventsRepository.findById(event.getId()).get();
    eventToUpdate.setHandled(true);
    return this.eventsRepository.save(eventToUpdate);
  }
  @Override
  public boolean deleteEvent(Long eventId) {
    Event eventToDelete = this.eventsRepository.findById(eventId).get();
    this.eventsRepository.deleteById(eventToDelete.getId());
    return true;
  }
  @Override
  public List<Event> getAllEvents() {
    return (ArrayList<Event>) this.eventsRepository.findAll();
  }

  @Override
  public Event findEventById(Long eventId) {
    return this.eventsRepository.findById(eventId).get();
  }
}
