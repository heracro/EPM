package hihi.content.material;

import hihi.content.common.contentDetails.ContentDetailsLayoutController;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ToString(callSuper = true)
public class MaterialDetailsLayoutController
        extends ContentDetailsLayoutController<Material> {

    public MaterialDetailsLayoutController(Material material) {
        super("Material", material);
        log.info("\033[92m MaterialDetailsLayoutController() \033[m");
    }

    public void setContent() {
        log.info("\033[92m setContent() \033[m");
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
