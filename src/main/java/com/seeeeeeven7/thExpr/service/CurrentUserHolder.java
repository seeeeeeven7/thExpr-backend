package com.seeeeeeven7.thExpr.service;

import org.springframework.stereotype.Service;
import com.seeeeeeven7.thExpr.models.User;

@Service
public class CurrentUserHolder {
    
    private static InheritableThreadLocal<User> THREAD_VARIABLES = new InheritableThreadLocal<User>() {

        @Override
        protected User initialValue() {
            return new User();
        }

    };

    public User get(){
        return THREAD_VARIABLES.get();
    }

    public void set(User user){
        THREAD_VARIABLES.set(user);
    }
    
}
