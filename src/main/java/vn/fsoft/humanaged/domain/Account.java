package vn.fsoft.humanaged.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    // @JsonIgnore
    @OneToOne(mappedBy = "account")
    private Employee employee;
}