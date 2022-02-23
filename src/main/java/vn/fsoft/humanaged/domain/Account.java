package vn.fsoft.humanaged.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account")
public class Account {

    @Id
    @Column (name="account_name")
    private String accountName;

    private String password;

    @Enumerated(EnumType.STRING)
    private SystemRole role;

    @JsonIgnore
    @OneToOne(mappedBy = "account")
    private Employee employee;
}