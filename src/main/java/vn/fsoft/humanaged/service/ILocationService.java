package vn.fsoft.humanaged.service;

import org.springframework.stereotype.Service;
import vn.fsoft.humanaged.domain.District;
import vn.fsoft.humanaged.domain.Province;
import vn.fsoft.humanaged.domain.Ward;

import java.util.List;
import java.util.Optional;

@Service
public interface ILocationService {
    List<Province> getAllProvince();

    List<District> getAllDistrictByProvinceId(int id);

    List<Ward> getAllWardByDistrictId(int id);

    Optional<Province> getProvinceById(int id);

    Optional<District> getDistrictById(int id);

    Optional<Ward> getWardById(int id);

    void addLocationData(List<Province> provinces, List<District> districts, List<Ward> wards);
}