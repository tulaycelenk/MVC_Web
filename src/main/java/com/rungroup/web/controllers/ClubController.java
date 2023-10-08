package com.rungroup.web.controllers;

import com.rungroup.web.dtos.ClubDto;
import com.rungroup.web.services.ClubService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ClubController {

    private ClubService clubService;

    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

//    @GetMapping("/clubs")
//    public ModelAndView listClubs(){
//        List<ClubDto> clubs = clubService.findAllClubs();
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("clubs-list");
//        modelAndView.addObject("clubs", clubs);
//        return modelAndView;
//    }

    @GetMapping("/clubs")
    public String listClubs(Model model){
        List<ClubDto> clubs = clubService.findAllClubs();
        model.addAttribute("clubs",clubs);
        return "clubs-list";
    }
}
