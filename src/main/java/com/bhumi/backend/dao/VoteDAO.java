package com.bhumi.backend.dao;

import com.bhumi.backend.modal.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteDAO extends JpaRepository<Vote, Long>, VoteCumstomDAO {
}
