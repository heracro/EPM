package hihi.content.common.contentDetails;

import hihi.content.common.ContentLayoutController;
import hihi.content.common.dataModel.AbstractContent;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ToString(callSuper = true)
public abstract class ContentDetailsLayoutController<
        Content extends AbstractContent>
        extends ContentLayoutController {

    @FXML
    protected Label uidField;

    public ContentDetailsLayoutController(String moduleName) {
        super(moduleName);
        log.info("\033[96m ContentDetailsLayoutController({}) \033[m", moduleName);
    }

    public abstract void setContent(Content content);

    public void initialize() {
        super.initialize();
    }

    @SuppressWarnings("unchecked")
    protected <T> void bindFieldToProperty(Object field, Object property) {
        if (field instanceof TextField && property instanceof StringProperty) {
            ((TextField) field).textProperty().bindBidirectional((StringProperty) property);
        } else if (field instanceof TextField && property instanceof IntegerProperty) {
            ((TextField) field).textProperty().bindBidirectional((IntegerProperty) property, new NumberStringConverter());
        } else if (field instanceof ComboBox && property instanceof ObjectProperty) {
            bindComboBoxToProperty((ComboBox<T>) field, (ObjectProperty<T>) property);
        }
    }

    @SuppressWarnings("unchecked")
    protected <T> void bindComboBoxToProperty(ComboBox<T> comboBox, ObjectProperty<T> property) {
        comboBox.setItems((ObservableList<T>) FXCollections.observableArrayList(property.getBean().getClass().getEnumConstants()));
        comboBox.valueProperty().bindBidirectional(property);
    }

}
