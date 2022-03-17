package vn.fsoft.humanaged.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.fsoft.humanaged.domain.Province;

@Repository
public interface IProvinceRepository extends JpaRepository<Province, Integer>{
    
}
