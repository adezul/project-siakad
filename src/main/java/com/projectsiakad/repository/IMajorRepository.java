package com.projectsiakad.repository;

import com.projectsiakad.model.Major;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMajorRepository extends JpaRepository<Major,String> {
}
