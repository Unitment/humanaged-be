package vn.fsoft.humanaged.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    private String accountName;

    private String password;

    @Enumerated(EnumType.STRING)
    private SystemRole role;

    @OneToOne(mappedBy = "account")
    @JsonIgnore
    private Employee employee;

    public Account(String accountName, String password, SystemRole role) {
        this.accountName = accountName;
        this.password = password;
        this.role = role;
    }
}