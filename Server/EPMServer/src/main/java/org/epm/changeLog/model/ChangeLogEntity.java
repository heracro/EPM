package org.epm.changeLog.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.epm.common.model.EntityListener;
import org.epm.common.model.IEntity;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "changeLogs")
@NoArgsConstructor
@EntityListeners(EntityListener.class)
public class ChangeLogEntity extends ChangeLogData implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long privateId;

    @Override
    public String toString() {
        return super.toString().substring(0, super.toString().length()-2)
                + ", privateId: " + getPrivateId() + "}";
    }

    @PrePersist
    public void setDefaults() {
        if (getTimestamp() == null) setTimestamp(LocalDateTime.now());
    }

}
