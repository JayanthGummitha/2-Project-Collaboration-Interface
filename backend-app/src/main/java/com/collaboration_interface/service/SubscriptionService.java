package com.collaboration_interface.service;

import com.collaboration_interface.modal.PlanType;
import com.collaboration_interface.modal.Subscription;
import com.collaboration_interface.modal.User;

public interface SubscriptionService {

    Subscription createSubscription(User user);
    Subscription getUserSubscription(Long userId)throws Exception;
    Subscription upgradeSubscription(Long userId, PlanType planType);

    boolean isValid(Subscription subscription);


}
