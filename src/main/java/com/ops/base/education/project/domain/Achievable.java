package com.ops.base.education.project.domain;

/**
 * This interface is idea to make route get projects for student polymorphic
 * i.e. this route is suppose to return either all Template entities or a project
 * base on a Template that student has selected already.
 */
public interface Achievable {
  /**
   * The dream is to make this method get final project result for the lecturer
   * @return
   * The ratio of student project achievement to ideal achievement to solve project puzzle
   */
  public double getAchievingPercent();
}
