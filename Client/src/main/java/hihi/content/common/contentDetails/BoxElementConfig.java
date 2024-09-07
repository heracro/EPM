package hihi.content.common.contentDetails;

import javafx.beans.property.Property;
import javafx.scene.Node;

import java.util.function.Supplier;

public record BoxElementConfig (
        Node node, String label, Supplier<Property<?>> propertySupplier
) {}
