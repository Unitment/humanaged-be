package vn.fsoft.humanaged.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vn.fsoft.humanaged.domain.*;
import vn.fsoft.humanaged.service.IBusinessUnitService;
import vn.fsoft.humanaged.service.IEmployeeService;
import vn.fsoft.humanaged.service.ILocationService;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class Config implements CommandLineRunner {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Autowired
    ILocationService locationService;

    @Autowired
    IEmployeeService employeeService;

    @Autowired
    IBusinessUnitService businessUnitService;

    private static final Logger logger = LoggerFactory.getLogger(Config.class);

    //import data in the first run
    @Override
    public void run(String... args) throws Exception {
        if (locationService.getProvinceById(1).isEmpty()) {
            ObjectMapper objectMapper = new ObjectMapper();
            List<Province> provinces = objectMapper.readValue(new File("src/main/resources/province.json"), new TypeReference<List<Province>>() {
            });
            List<District> districts = objectMapper.readValue(new File("src/main/resources/district.json"), new TypeReference<List<District>>() {
            });
            List<Ward> wards = objectMapper.readValue(new File("src/main/resources/ward.json"), new TypeReference<List<Ward>>() {
            });

            logger.info("Init data");
            locationService.addLocationData(provinces, districts, wards);

            Employee admin = new Employee("0", "Administrator", LocalDate.now(), Gender.OTHER, "admin@fsoft.com.vn",
                    "admin@gmail.com",
                    "https://firebasestorage.googleapis.com/v0/b/humanaged-d9db7.appspot.com/o/profile.svg?alt=media&token=16bc5a31-510f-4250-bbfc-57d79f078710",
                    "028 3736 2310",
                    "Việt Nam",
                    locationService.getProvinceById(3).get(),
                    locationService.getDistrictById(60).get(),
                    locationService.getWardById(950).get(),
                    "FPT Complex, Khu đô thị FPT City",
                    Status.WORKING,
                    false,
                    LocalDateTime.now(),
                    new Account("admin", "$2a$12$krWhSqI50OiV6IRsZ3yFZOhDwYTYH0jH6WuCe74BDd89e2IvdBmlW", SystemRole.ROLE_ADMIN));
            employeeService.save(admin);

            Employee bul = new Employee(
                    "1",
                    "Nguyễn Thái Ngọc Anh",
                    LocalDate.now(),
                    Gender.MALE,
                    "anhntn7@fsoft.com.vn",
                    "",
                    "https://firebasestorage.googleapis.com/v0/b/humanaged-d9db7.appspot.com/o/profile.svg?alt=media&token=16bc5a31-510f-4250-bbfc-57d79f078710",
                    "0983859991",
                    "Việt Nam",
                    null,
                    null,
                    null,
                    "",
                    Status.WORKING,
                    false,
                    LocalDateTime.now(),
                    new Account("anhntn7", "$2a$12$mmWropDDpvPa7AbMJjs8HuGg3eljCBEcqxSC/St7/iC6XAEFGm9ny", SystemRole.ROLE_ADMIN));

            employeeService.save(bul);

            BusinessUnit g3 = new BusinessUnit(0, "FSO.DN.C.FDN.G3", employeeService.getById("E00002").get());
            businessUnitService.save(g3);

            logger.info("Done");
        }
    }
}