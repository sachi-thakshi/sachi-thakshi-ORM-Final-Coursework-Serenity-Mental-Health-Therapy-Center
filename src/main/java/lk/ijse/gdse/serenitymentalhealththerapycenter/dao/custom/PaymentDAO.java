package lk.ijse.gdse.serenitymentalhealththerapycenter.dao.custom;


import lk.ijse.gdse.serenitymentalhealththerapycenter.dao.CrudDAO;
import lk.ijse.gdse.serenitymentalhealththerapycenter.entity.Payment;

public interface PaymentDAO extends CrudDAO <Payment, String>{
    String getNextPaymentId() throws Exception;
}
