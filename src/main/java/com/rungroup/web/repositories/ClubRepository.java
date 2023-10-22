package com.rungroup.web.repositories;

import com.rungroup.web.models.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClubRepository extends JpaRepository<Club,Long> {
    Optional<Club> findByTitle(String url);
    @Query("SELECT c from Club c WHERE lower(c.title) LIKE lower(CONCAT('%', :query,'%' ))")
    List<Club> searchClubs(String query);
 }
