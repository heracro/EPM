package hihi.content.shop;

import hihi.content.common.dataModel.AbstractContent;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString(callSuper = true)
@NoArgsConstructor
public class Shop extends AbstractContent {

    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty website = new SimpleStringProperty();
    private final StringProperty address = new SimpleStringProperty();
    private final StringProperty email = new SimpleStringProperty();
    private final StringProperty phone = new SimpleStringProperty();
    private final StringProperty memo = new SimpleStringProperty();

    public Shop(ShopDto dto) {
        uid.set(dto.getUid());
        name.set(dto.getName());
        website.set(dto.getWebsite());
        address.set(dto.getAddress());
        email.set(dto.getEmail());
        phone.set(dto.getPhone());
        memo.set(dto.getMemo());
    }

    public StringProperty nameProperty() { return name; }
    public StringProperty websiteProperty() { return website; }
    public StringProperty addressProperty() { return address; }
    public StringProperty emailProperty() { return email; }
    public StringProperty phoneProperty() { return phone; }
    public StringProperty memoProperty() { return memo; }

}
