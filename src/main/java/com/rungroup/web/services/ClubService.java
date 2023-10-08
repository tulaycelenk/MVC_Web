package com.rungroup.web.services;

import com.rungroup.web.dtos.ClubDto;

import java.util.List;

public interface ClubService {

    List<ClubDto> findAllClubs();
}
