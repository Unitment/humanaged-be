package vn.fsoft.humanaged.config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import org.apache.tomcat.jni.Directory;
import org.json.simple.JSONArray;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import vn.fsoft.humanaged.domain.District;
import vn.fsoft.humanaged.domain.Province;
import vn.fsoft.humanaged.domain.Ward;
import vn.fsoft.humanaged.repository.IDistrictRepository;
import vn.fsoft.humanaged.repository.IProvinceRepository;
import vn.fsoft.humanaged.repository.IWardRepository;

import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Autowired
    IProvinceRepository provinceRepository ;
    @Autowired
    IDistrictRepository districtRepository;
    @Autowired
    IWardRepository wardRepository;
    @Bean
    public CommandLineRunner commandLineRunner(){
        return args -> {
            if(provinceRepository.findById(1).isEmpty()){
                ObjectMapper objectMapper = new ObjectMapper();
                List<Province> provinces = objectMapper.readValue(new File("src/main/resources/province.json"), new TypeReference<List<Province>>(){});
                List<District> districts = objectMapper.readValue(new File("src/main/resources/district.json"), new TypeReference<List<District>>(){});
                List<Ward> wards = objectMapper.readValue(new File("src/main/resources/ward.json"), new TypeReference<List<Ward>>(){});

                System.out.println("generating location data...");
                provinceRepository.saveAll(provinces);
                districtRepository.saveAll(districts);
                wardRepository.saveAll(wards);
                System.out.println("done!");
            }
        };
    }
}