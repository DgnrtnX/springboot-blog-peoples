package com.blog.peoples.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.peoples.entity.PeoplesUser;

@Repository
public interface PeoplesUserRepo extends JpaRepository<PeoplesUser, Long> {

	PeoplesUser findByUserId(Long userId);

}
