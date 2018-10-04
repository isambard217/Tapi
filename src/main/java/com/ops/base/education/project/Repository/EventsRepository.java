package com.ops.base.education.project.Repository;
import com.ops.base.education.project.domain.Event;
import org.springframework.data.repository.CrudRepository;
public interface EventsRepository extends CrudRepository<Event, Long> {}
