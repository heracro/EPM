package hihi.content.material;

import hihi.content.common.contentDetails.ContentDetailsLayoutController;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@ToString(callSuper = true)
@Component
public class MaterialDetailsLayoutController
        extends ContentDetailsLayoutController<Material> {

    public MaterialDetailsLayoutController() {
        super("Material");
        log.info("\033[92m MaterialDetailsLayoutController() \033[m");
    }

    @Override
    public void setContent(Material content) {
        log.info("\033[92m setContent() \033[m");
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
