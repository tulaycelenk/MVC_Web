package com.rungroup.web.services.impl;

import com.rungroup.web.dtos.ClubDto;
import com.rungroup.web.models.Club;
import com.rungroup.web.repositories.ClubRepository;
import com.rungroup.web.services.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClubServiceImpl implements ClubService {

    //Bring repository in bc all the repository code is going to be used within the service
    private ClubRepository clubRepository;

    @Autowired
    public ClubServiceImpl(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    @Override
    public List<ClubDto> findAllClubs() {
        List<Club> clubs=clubRepository.findAll();
        return clubs.stream().map((club)->mapToClubDto(club)).collect(Collectors.toList());
    }

    //we create our mapper method
    private ClubDto mapToClubDto(Club club){
        ClubDto clubDto = ClubDto.builder()
                .id(club.getId())
                .title(club.getTitle())
                .photoUrl(club.getPhotoUrl())
                .content(club.getContent())
                .cratedOn(club.getCratedOn())
                .updatedOn(club.getUpdatedOn())
                .build();
        return clubDto;
    }
}
