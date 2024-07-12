package org.epm.tag.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.epm.common.model.IEntity;
import org.epm.project.model.ProjectEntity;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "tags")
@NoArgsConstructor
public class TagEntity extends TagData implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long privateId;
    Integer id;
    private String name;

    @ManyToMany
    @JoinTable(
            name = "project_tag",
            joinColumns = @JoinColumn(name = "tag_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id"))
    private Set<ProjectEntity> projects = new HashSet<>();
}
