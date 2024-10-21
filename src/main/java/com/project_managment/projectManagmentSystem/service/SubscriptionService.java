package com.project_managment.projectManagmentSystem.service;

import com.project_managment.projectManagmentSystem.model.PlanType;
import com.project_managment.projectManagmentSystem.model.Subscription;
import com.project_managment.projectManagmentSystem.model.User;

public interface SubscriptionService {
    Subscription createSubscription(User user) throws Exception;

    Subscription getUserSubscription(Long userId) throws Exception;

    Subscription upgradeSubscription(Long userId, PlanType planType) throws Exception;

    boolean isValid(Subscription subscription);
}
