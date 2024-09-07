package hihi.content.shop;

import hihi.content.common.contentDetails.ContentDetailsLayoutController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ToString(callSuper = true)
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

    public ShopDetailsLayoutController(Shop shop) {
        super("Shop", shop);
        log.info("\033[92m ProjectDetailsLayoutController() \033[m");
        setContent();
    }

    public void initialize() {
        super.initialize();
        log.info("\033[92m initialize() \033[0m");
    }

    public void setContent() {
        log.info("\033[92m setContent({})\033[0m", content);
        nameField.textProperty().bindBidirectional(content.nameProperty());
        websiteField.textProperty().bindBidirectional(content.websiteProperty());
        addressField.textProperty().bindBidirectional(content.addressProperty());
        emailField.textProperty().bindBidirectional(content.emailProperty());
        phoneField.textProperty().bindBidirectional(content.phoneProperty());
        memoField.textProperty().bindBidirectional(content.memoProperty());
    }

}
