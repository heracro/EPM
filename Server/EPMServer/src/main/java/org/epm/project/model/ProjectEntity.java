package org.epm.project.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.epm.common.model.EntityListener;
import org.epm.common.model.IEntity;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "projects")
@NoArgsConstructor
@EntityListeners(EntityListener.class)
public class ProjectEntity extends ProjectData implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long privateId;

    @PrePersist
    @PreUpdate
    public void setDefaults() {
        if (getWorkingHoursCount() == null) setWorkingHoursCount(0);
    }

    @Override
    public String toString() {
        return super.toString().substring(0, super.toString().length()-2)
                + ", privateId: " + getPrivateId() + "}";
    }
}
