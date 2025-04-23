module lk.ijse.gdse.serenitymentalhealththerapycenter {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires com.jfoenix;
    requires java.prefs;

    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;
    requires jbcrypt;
    requires java.management;


    opens lk.ijse.gdse.serenitymentalhealththerapycenter.config to jakarta.persistence;
    opens lk.ijse.gdse.serenitymentalhealththerapycenter.entity to org.hibernate.orm.core;
    opens lk.ijse.gdse.serenitymentalhealththerapycenter.dto.tm to java.base;

    opens lk.ijse.gdse.serenitymentalhealththerapycenter to javafx.fxml;
    opens lk.ijse.gdse.serenitymentalhealththerapycenter.controller to javafx.fxml;
    exports lk.ijse.gdse.serenitymentalhealththerapycenter;
}