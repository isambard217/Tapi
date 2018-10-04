package com.ops.base.education.project.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.io.Serializable;
@Entity
public class Event implements Serializable {
  @Id
  @GeneratedValue
  private long id;
  private String name;
  @ManyToOne
  @JoinColumn(name="user_id", nullable = false)
  private ApiUser apiUser;
  private long timeStamp;
  private boolean handled;
  public Event(String name, ApiUser apiUser, long timeStamp, boolean handled) {
    this.name = name;
    this.apiUser = apiUser;
    this.timeStamp = timeStamp;
    this.handled = handled;
  }
  public Event() {
  }
  public long getId() {
    return id;
  }
  public void setId(long id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  @JsonIgnore
  public ApiUser getApiUser() {
    return apiUser;
  }
  public void setApiUser(ApiUser apiUser) {
    this.apiUser = apiUser;
  }
  public long getTimeStamp() {
    return timeStamp;
  }
  public void setTimeStamp(long timeStamp) {
    this.timeStamp = timeStamp;
  }
  public boolean isHandled() {
    return handled;
  }
  public void setHandled(boolean handled) {
    this.handled = handled;
  }
}
