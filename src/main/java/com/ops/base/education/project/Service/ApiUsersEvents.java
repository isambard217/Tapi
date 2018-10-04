package com.ops.base.education.project.Service;
import com.ops.base.education.project.Repository.EventsRepository;
import com.ops.base.education.project.domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Service
public class ApiUsersEvents implements EventsService {
  private EventsRepository eventsRepository;
  private ApiUsersService apiUsersService;
  @Autowired
  public ApiUsersEvents(EventsRepository eventsRepository,
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
  public Event updateEvent(Long userId) {
    return null;
  }
  @Override
  public Event deleteEvent(Long userId) {
    return null;
  }
  @Override
  public List<Event> getAllEvents() {
    return (ArrayList<Event>) this.eventsRepository.findAll();
  }
}
