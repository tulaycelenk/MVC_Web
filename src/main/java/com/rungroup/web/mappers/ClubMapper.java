package com.rungroup.web.mappers;

import com.rungroup.web.dtos.ClubDto;
import com.rungroup.web.models.Club;

import java.util.stream.Collectors;

import static com.rungroup.web.mappers.EventMapper.mapToEventDto;

public class ClubMapper {
    public static Club mapToClub(ClubDto club){
        Club clubDto = Club.builder()
                .id(club.getId())
                .title(club.getTitle())
                .photoUrl(club.getPhotoUrl())
                .content(club.getContent())
                .cratedOn(club.getCratedOn())
                .updatedOn(club.getUpdatedOn())
                .build();
        return clubDto;
    }
    //we create our mapper method
    public static ClubDto mapToClubDto(Club club){
        ClubDto clubDto = ClubDto.builder()
                .id(club.getId())
                .title(club.getTitle())
                .photoUrl(club.getPhotoUrl())
                .content(club.getContent())
                .cratedOn(club.getCratedOn())
                .updatedOn(club.getUpdatedOn())
                .events(club.getEvents().stream().map((event)-> mapToEventDto(event)).collect(Collectors.toList()))
                .build();
        return clubDto;
    }
}
