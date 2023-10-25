package com.rungroup.web.controllers;

import com.rungroup.web.dtos.ClubDto;
import com.rungroup.web.models.Club;
import com.rungroup.web.services.ClubService;
import groovyjarjarantlr4.v4.codegen.model.SrcOp;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
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
    @GetMapping("/clubs/{clubId}/delete")
    public String deleteClub(@PathVariable("clubId") Long clubId ){
        clubService.delete(clubId);
        return "redirect:/clubs";
    }
    @GetMapping("/clubs/search")
    public String searchClub(@RequestParam(value = "query") String query , Model model) {
        List<ClubDto> clubDtos = clubService.searchClubs(query);
        model.addAttribute("clubs",clubDtos);
        return "clubs-list";

    }
    @GetMapping("/clubs/new") // = @RequestMapping(value="/clubs/new", method={RequestMethod.GET})
    public String createClubForm(Model model){
        Club club = new Club();
        model.addAttribute("club",club);
        return "clubs-create";
    }
    @PostMapping("/clubs/new")
    public String saveClub(@Valid @ModelAttribute("club") ClubDto clubDto, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("club", clubDto);
            return "clubs-create";
        }
        clubService.saveClub(clubDto);
        return "redirect:/clubs";
    }

    @GetMapping("/clubs/{clubId}")
    public String clubDetail(@PathVariable("clubId") long clubId, Model model){
        ClubDto clubDto= clubService.findClubById(clubId);
        model.addAttribute("club",clubDto);
        return "clubs-detail";
    }
    @GetMapping("/clubs/{clubId}/edit")
    public String editClubForm(@PathVariable("clubId") long clubId, Model model){
        ClubDto club= clubService.findClubById(clubId);
        model.addAttribute("club",club);
        return "clubs-edit";
    }

    //get the club id and update it
    //ModelAttribute retrieves a "club" object from the model
    @PostMapping("/clubs/{clubId}/edit")
    public String updateClub(@ModelAttribute("clubId") Long clubId,
                             @Valid @ModelAttribute("club") ClubDto clubDto,
                             BindingResult result, Model model ){

        if(result.hasErrors()){
            model.addAttribute("club",clubDto);
            return "clubs-edit";
        }
        clubDto.setId(clubId);
        //clubDto is updated with the provided clubId
        clubService.updateClub(clubDto);
        return "redirect:/clubs";
    }

}
