package org.epm.common.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
@SuperBuilder
public abstract class AbstractModuleData {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "table_gen")
    @TableGenerator(name = "table_gen", allocationSize = 1)
    @EqualsAndHashCode.Exclude
    private Long id;

}
