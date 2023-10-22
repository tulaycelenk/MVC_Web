package com.rungroup.web.exceptionhandlers;

import com.rungroup.web.customexceptions.ClubNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ClubNotFoundException.class)
    public String ClubNotFoundException(ClubNotFoundException clubNotFoundException, Model model){
        model.addAttribute("errorMessage", clubNotFoundException.getMessage());
        model.addAttribute("customMessage", "");
        return "club-not-found";
    }

}
