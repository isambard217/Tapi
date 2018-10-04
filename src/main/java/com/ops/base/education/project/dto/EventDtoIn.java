package com.ops.base.education.project.dto;
import java.io.Serializable;
public class EventDtoIn implements Serializable {
    private long id;
    private String name;
    private Long apiUserId;
    private long timeStamp;
    private boolean handled;
  public EventDtoIn() {
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
  public Long getApiUserId() {
    return apiUserId;
  }
  public void setApiUserId(Long apiUserId) {
    this.apiUserId = apiUserId;
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
