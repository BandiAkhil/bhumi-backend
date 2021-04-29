package com.bhumi.backend.dao;

import com.bhumi.backend.entity.Vote;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteDAO extends JpaRepository<Vote, Long>, VoteCumstomDAO {
}
