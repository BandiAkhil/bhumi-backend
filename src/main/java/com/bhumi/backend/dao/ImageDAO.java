package com.bhumi.backend.dao;

import com.bhumi.backend.entity.Image;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageDAO extends JpaRepository<Image, Long>, ImageCumstomDAO {
}
