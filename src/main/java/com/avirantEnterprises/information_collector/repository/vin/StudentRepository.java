package com.avirantEnterprises.information_collector.repository.vin;

import com.avirantEnterprises.information_collector.model.vin.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
