package vn.fsoft.humanaged.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BuDTO {
    private Integer id;

    private String name;

    private EmployeeDTO bul;
}