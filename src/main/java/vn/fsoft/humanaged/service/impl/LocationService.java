package vn.fsoft.humanaged.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.fsoft.humanaged.domain.District;
import vn.fsoft.humanaged.domain.Province;
import vn.fsoft.humanaged.domain.Ward;
import vn.fsoft.humanaged.repository.IDistrictRepository;
import vn.fsoft.humanaged.repository.IProvinceRepository;
import vn.fsoft.humanaged.repository.IWardRepository;
import vn.fsoft.humanaged.service.ILocationService;

import java.util.List;

@Service
public class LocationService implements ILocationService {

    @Autowired
    private IDistrictRepository districtRepository;

    @Autowired
    private IProvinceRepository provinceRepository;

    @Autowired
    private IWardRepository wardRepository;

    @Override
    public List<Province> getAllProvince() {
        return provinceRepository.findAll();
    }

    @Override
    public List<District> getAllDistrictByProvinceId(int id) {
        return districtRepository.getAllByProvinceId(id);
    }

    @Override
    public List<Ward> getAllWardByDistrictId(int id) {
        return wardRepository.getAllByDistrictId(id);
    }
}