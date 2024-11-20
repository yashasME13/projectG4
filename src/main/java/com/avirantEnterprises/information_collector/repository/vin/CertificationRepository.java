package com.avirantEnterprises.information_collector.repository.vin;

import com.avirantEnterprises.information_collector.model.vin.Certification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificationRepository extends JpaRepository<Certification, Long> {
    // You can define custom queries here if needed
}
