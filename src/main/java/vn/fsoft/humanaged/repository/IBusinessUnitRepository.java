package vn.fsoft.humanaged.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.fsoft.humanaged.domain.BusinessUnit;

public interface IBusinessUnitRepository extends JpaRepository<BusinessUnit, Integer> {
}