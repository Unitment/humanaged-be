package vn.fsoft.humanaged.service;

import org.springframework.stereotype.Service;
import vn.fsoft.humanaged.domain.District;
import vn.fsoft.humanaged.domain.Province;
import vn.fsoft.humanaged.domain.Ward;

import java.util.List;

@Service
public interface ILocationService {
    List<Province> getAllProvince();

    List<District> getAllDistrictByProvinceId(int id);

    List<Ward> getAllWardByDistrictId(int id);
}