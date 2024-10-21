package com.project_managment.projectManagmentSystem.repository;

import com.project_managment.projectManagmentSystem.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    Subscription findByUserId(long userId);
}
