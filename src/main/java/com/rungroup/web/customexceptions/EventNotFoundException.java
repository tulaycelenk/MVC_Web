package com.rungroup.web.customexceptions;


public class EventNotFoundException extends RuntimeException {
    public EventNotFoundException(String message){
        super(message);
    }

}
