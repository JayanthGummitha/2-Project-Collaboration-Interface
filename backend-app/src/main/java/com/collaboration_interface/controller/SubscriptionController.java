package com.collaboration_interface.controller;

import com.collaboration_interface.modal.PlanType;
import com.collaboration_interface.modal.Subscription;
import com.collaboration_interface.modal.User;
import com.collaboration_interface.service.SubscriptionService;
import com.collaboration_interface.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subscription")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;
    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public ResponseEntity<Subscription> getUSerSubscription(
            @RequestHeader("Authorization" )String jwt) throws Exception{
        User user=userService.findUserProfileByJwt(jwt);

        Subscription subscription=subscriptionService.getUserSubscription(user.getId());

        return new ResponseEntity<>(subscription, HttpStatus.OK);
    }

    @PatchMapping("/upgrade")
    public ResponseEntity<Subscription> UpgradeSubscription(
            @RequestHeader("Authorization" )String jwt,
            @RequestParam PlanType planType ) throws Exception{
        User user=userService.findUserProfileByJwt(jwt);

        Subscription subscription=subscriptionService.upgradeSubscription(user.getId(),planType);
        return new ResponseEntity<>(subscription, HttpStatus.OK);
    }

}
