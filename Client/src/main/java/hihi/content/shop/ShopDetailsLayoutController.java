package hihi.content.shop;

import hihi.content.common.contentDetails.ContentDetailsLayoutController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@ToString(callSuper = true)
@Component
public class ShopDetailsLayoutController
        extends ContentDetailsLayoutController<Shop>  {

    @FXML
    private TextField nameField;
    @FXML
    private TextField websiteField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField memoField;

    public ShopDetailsLayoutController() {
        super("Shop");
        log.info("\033[92m ProjectDetailsLayoutController() \033[m");
    }

    public void initialize() {
        super.initialize();
        log.info("\033[92m initialize() \033[0m");
    }

    @Override
    public void setContent(Shop shop) {
        log.info("\033[92m setContent({})\033[0m", shop);
        nameField.textProperty().bindBidirectional(shop.nameProperty());
        websiteField.textProperty().bindBidirectional(shop.websiteProperty());
        addressField.textProperty().bindBidirectional(shop.addressProperty());
        emailField.textProperty().bindBidirectional(shop.emailProperty());
        phoneField.textProperty().bindBidirectional(shop.phoneProperty());
        memoField.textProperty().bindBidirectional(shop.memoProperty());
    }

}
