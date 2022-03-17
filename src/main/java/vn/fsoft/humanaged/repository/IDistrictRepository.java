package vn.fsoft.humanaged.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.fsoft.humanaged.domain.District;

@Repository
public interface IDistrictRepository extends JpaRepository<District, Integer>{
    
}
