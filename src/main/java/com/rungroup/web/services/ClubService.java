package com.rungroup.web.services;

import com.rungroup.web.dtos.ClubDto;
import com.rungroup.web.models.Club;

import java.util.List;

public interface ClubService {

    List<ClubDto> findAllClubs();

    Club saveClub(ClubDto clubDto);

    ClubDto findClubById(long clubId);

    void updateClub(ClubDto clubDto);

    void delete(Long clubId);
    List<ClubDto> searchClubs(String query);
}
