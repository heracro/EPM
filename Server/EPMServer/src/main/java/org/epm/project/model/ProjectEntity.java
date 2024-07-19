package org.epm.project.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.epm.bom.model.BomEntity;
import org.epm.common.model.IEntity;
import org.epm.tag.model.TagEntity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "projects")
@TableGenerator(
        name = "project_gen",
        table = "id_gen",
        pkColumnName = "gen_name",
        valueColumnName = "gen_val",
        pkColumnValue = "project_id",
        allocationSize = 1)
@NoArgsConstructor
@SuperBuilder
public class ProjectEntity extends ProjectData implements IEntity {

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<TagEntity> tags = new HashSet<>();

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<BomEntity> boms;

    @PrePersist
    @PreUpdate
    public void setDefaults() {
        if (getWorkingHoursCount() == null) setWorkingHoursCount(0);
    }

}
