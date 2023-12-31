package com.rungroup.web.services.impl;

import com.rungroup.web.customexceptions.ClubNotFoundException;
import com.rungroup.web.dtos.ClubDto;
import com.rungroup.web.models.Club;
import com.rungroup.web.repositories.ClubRepository;
import com.rungroup.web.services.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.rungroup.web.mappers.ClubMapper.mapToClub;
import static com.rungroup.web.mappers.ClubMapper.mapToClubDto;


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

    @Override
    public Club saveClub(ClubDto clubDto) {
        Club club= mapToClub(clubDto);
        return clubRepository.save(club);
    }

    @Override
    public ClubDto findClubById(long clubId) {

        Optional<Club> club = clubRepository.findById(clubId);
        if (club.isPresent())
            return mapToClubDto(club.get());
        club.orElseThrow(() ->  new ClubNotFoundException("Club is not found "));
        return new ClubDto();
    }


    @Override
    public void updateClub(ClubDto clubDto) {
        Club club =mapToClub(clubDto);
        clubRepository.save(club);
    }

    @Override
    public void delete(Long clubId) {
        clubRepository.deleteById(clubId);
    }

    @Override
    public List<ClubDto> searchClubs(String query) {
        List<Club> clubs = clubRepository.searchClubs(query);
        return clubs.stream().map(club -> mapToClubDto(club)).collect(Collectors.toList());
    }


}
