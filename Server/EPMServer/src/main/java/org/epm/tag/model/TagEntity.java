package org.epm.tag.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.epm.common.model.EntityListener;
import org.epm.common.model.IEntity;
import org.epm.project.model.ProjectEntity;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "tags")
@TableGenerator(
        name = "tag_gen",
        table = "id_gen",
        pkColumnName = "gen_name",
        valueColumnName = "gen_val",
        pkColumnValue = "tag_id",
        allocationSize = 1)
@NoArgsConstructor
@EntityListeners(EntityListener.class)
public class TagEntity extends TagData implements IEntity {

    @ManyToMany(mappedBy = "tags")
    private Set<ProjectEntity> projects = new HashSet<>();

}
