package vn.fsoft.humanaged.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import vn.fsoft.humanaged.domain.BusinessUnit;
import vn.fsoft.humanaged.repository.IBusinessUnitRepository;
import vn.fsoft.humanaged.service.IBusinessUnitService;

import java.util.List;
import java.util.Optional;

public class BusinessService implements IBusinessUnitService {

    @Autowired
    private IBusinessUnitRepository businessUnitRepository;

    @Override
    public List<BusinessUnit> getAll() {
        return null;
    }

    @Override
    public Optional<BusinessUnit> getById(Integer key) {
        return Optional.empty();
    }

    @Override
    public BusinessUnit save(BusinessUnit entity) {
        return null;
    }

    @Override
    public void deleteById(Integer key) {

    }
}