package vn.fsoft.humanaged.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@SQLDelete(sql = "UPDATE project SET is_deleted = 'true' WHERE id=?")
@Where(clause = "is_deleted=false")
// @FilterDef(name = "deletedProductFilter", parameters = @ParamDef(name =
// "isDeleted", type = "boolean"))
// @Filter(name = "deletedProductFilter", condition = "deleted = :isDeleted")
public class Project {

    @Id
    @GenericGenerator(name = "projectIdGen", parameters = @org.hibernate.annotations.Parameter(name = "prefix", value = "P"), strategy = "vn.fsoft.humanaged.util.IdGenerator")
    @GeneratedValue(generator = "projectIdGen")
    private String id;

    @Nationalized
    private String name;

    private LocalDate startDate;

    private LocalDate endDate;

    @Nationalized
    private String description;

    @Enumerated(EnumType.STRING)
    private ProjectState state;

    @JsonIgnore
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<ProjectMember> projectMembers;

    private boolean isDeleted = Boolean.FALSE;

    // @CreatedDate
    private LocalDateTime createdDate;
}