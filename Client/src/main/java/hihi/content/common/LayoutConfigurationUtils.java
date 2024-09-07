package hihi.content.common;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;

public class LayoutConfigurationUtils {

    @SuppressWarnings("unchecked")
    public static <T extends Enum<T>> void bindFieldToProperty(Object field, Object property) {
        if (field instanceof TextField textField && property instanceof StringProperty stringProperty) {
            textField.textProperty().bindBidirectional(stringProperty);
        } else if (field instanceof TextField textField && property instanceof IntegerProperty integerProperty) {
            textField.textProperty().bindBidirectional(integerProperty, new NumberStringConverter());
        } else if (field instanceof ComboBox && property instanceof ObjectProperty) {
            bindComboBoxToProperty((ComboBox<T>) field, (ObjectProperty<T>) property);
        }
    }

    @SuppressWarnings("unchecked")
    private static <T extends Enum<T>> void bindComboBoxToProperty(ComboBox<T> comboBox, ObjectProperty<T> property) {
        comboBox.setItems(
                (ObservableList<T>) FXCollections.observableArrayList(property.getBean().getClass().getEnumConstants())
        );
        comboBox.valueProperty().bindBidirectional(property);
    }

}
