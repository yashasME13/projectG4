package com.avirantEnterprises.information_collector.repository.course;

import com.avirantEnterprises.information_collector.model.course.UserForCourse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositoryForCourse extends JpaRepository<UserForCourse, Long> {
}