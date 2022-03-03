package vn.fsoft.humanaged.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Nationalized;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BusinessUnit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Nationalized
    private String name;

    @OneToOne
    @JoinColumn(name = "bulId")
    private Employee bul;
}