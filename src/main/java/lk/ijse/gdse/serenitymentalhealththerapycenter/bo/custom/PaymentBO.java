package lk.ijse.gdse.serenitymentalhealththerapycenter.bo.custom;


import lk.ijse.gdse.serenitymentalhealththerapycenter.bo.SuperBO;
import lk.ijse.gdse.serenitymentalhealththerapycenter.dto.PaymentDTO;

import java.util.ArrayList;

public interface PaymentBO extends SuperBO {
    String getNextPaymentId() throws Exception;
    ArrayList<PaymentDTO> getAllPayments() throws Exception;
    boolean savePayment(PaymentDTO paymentDTO) throws Exception;
    boolean updatePayment(PaymentDTO paymentDTO) throws Exception;
    boolean deletePayment(String paymentId) throws Exception;
    PaymentDTO searchPayment(String paymentId) throws Exception;
}
