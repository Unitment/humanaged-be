package vn.fsoft.humanaged.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.fsoft.humanaged.domain.Ward;

@Repository
public interface IWardRepository extends JpaRepository<Ward, Integer>{
    
}
