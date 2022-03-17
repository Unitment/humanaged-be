package vn.fsoft.humanaged.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.fsoft.humanaged.domain.BusinessUnit;

@Repository
public interface IBusinessUnitRepository extends JpaRepository<BusinessUnit, Integer> {
}