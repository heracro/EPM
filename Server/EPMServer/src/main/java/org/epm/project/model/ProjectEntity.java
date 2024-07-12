package org.epm.project.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.epm.common.model.IEntity;

@Slf4j
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "projects")
@NoArgsConstructor
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
