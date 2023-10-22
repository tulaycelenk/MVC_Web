package com.rungroup.web.customexceptions;

public class ClubNotFoundException extends RuntimeException{
    public ClubNotFoundException(String message){
        super(message);
    }
}
