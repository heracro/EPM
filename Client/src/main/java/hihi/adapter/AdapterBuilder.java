package hihi.adapter;

import hihi.application.config.ModuleConfig;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
public class AdapterBuilder {

    private ModuleConfig moduleConfig;
    private Integer parentUid;

    public AdapterBuilder setModuleConfig(ModuleConfig moduleConfig) {
        this.moduleConfig = moduleConfig;
        return this;
    }

    public AdapterBuilder setParentUid(Integer parentUid) {
        this.parentUid = parentUid;
        return this;
    }

    public WarehouseAdapter build() {
        if (moduleConfig.isPrimary()) {
            return new WarehouseAdapter(moduleConfig);
        } else if (moduleConfig.isDependant() && parentUid != null) {
            return new WarehouseAdapter(moduleConfig, parentUid);
        } else {
            throw new IllegalStateException("Illegal adapter configuration");
        }
    }
}
