package vn.fsoft.humanaged.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.fsoft.humanaged.domain.BusinessUnit;
import vn.fsoft.humanaged.repository.IBusinessUnitRepository;
import vn.fsoft.humanaged.service.IBusinessUnitService;

import java.util.List;
import java.util.Optional;

@Service
public class BusinessService implements IBusinessUnitService {

    @Autowired
    private IBusinessUnitRepository businessUnitRepository;

    @Override
    public List<BusinessUnit> getAll() {
        return businessUnitRepository.findAll();
    }

    @Override
    public Optional<BusinessUnit> getById(Integer key) {
        return businessUnitRepository.findById(key);
    }

    @Override
    public BusinessUnit save(BusinessUnit entity) {
        return businessUnitRepository.save(entity);
    }

    @Override
    public void deleteById(Integer key) {
        businessUnitRepository.deleteById(key);
    }
}