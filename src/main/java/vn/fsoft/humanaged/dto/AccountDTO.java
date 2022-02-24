package vn.fsoft.humanaged.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.fsoft.humanaged.domain.SystemRole;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private String accountName;
    private SystemRole role;
}