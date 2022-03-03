package vn.fsoft.humanaged.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.fsoft.humanaged.domain.BusinessUnit;
import vn.fsoft.humanaged.dto.BuDTO;
import vn.fsoft.humanaged.service.IBusinessUnitService;

@RestController
@RequestMapping("/api/bu")
public class BusinessUnitController {

    @Autowired
    private IBusinessUnitService businessService ;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/all")
    // @ResponseBody
    public ResponseEntity<List<BuDTO>> getAllBUs(){
        List<BusinessUnit> BUs = businessService.getAll();
        List<BuDTO> lsBU = BUs.stream()
                            .map(bu -> modelMapper.map(bu, BuDTO.class))
                            .collect(Collectors.toList());
        return new ResponseEntity<>(lsBU,HttpStatus.OK);
    }
}