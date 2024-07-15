package org.epm.shop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.epm.common.model.AbstractModuleData;

@Slf4j
@Getter
@Setter
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@MappedSuperclass
public abstract class ShopData extends AbstractModuleData {

    @Column(nullable = false, unique = true)
    private Integer uid;

    @Column(nullable = false)
    private String name;

    private String webpage;
    private String address;
    private String email;

    @Column(length = 31)
    private String phone;

    @Column(columnDefinition = "TEXT")
    private String memo;

    @Transient
    private String action;


    @JsonIgnore
    public boolean isValidEntity() {
        return getName() != null
                && (getWebpage() != null || getAddress() != null
                || getEmail() != null || getPhone() != null);
    }

    @JsonIgnore
    public boolean isValidDTO() {
        return getUid() != null || getName() != null
                || getWebpage() != null || getAddress() != null
                || getEmail() != null || getPhone() != null
                || getMemo() != null || getAction() != null;
    }

    public String toString() {
        return "\n\tShop {"
                + "\n\t\tuid: " + getUid()
                + "\n\t\tid: " + getId()
                + "\n\t\tname: " + getName()
                + "\n\t\twebpage: " + getWebpage()
                + "\n\t\taddress: " + getAddress()
                + "\n\t\temail: " + getEmail()
                + "\n\t\tphone: " + getPhone()
                + "\n\t}";
    }

}
