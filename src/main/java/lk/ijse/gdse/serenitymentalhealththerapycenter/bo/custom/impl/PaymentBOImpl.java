package lk.ijse.gdse.serenitymentalhealththerapycenter.bo.custom.impl;
import lk.ijse.gdse.serenitymentalhealththerapycenter.bo.custom.PaymentBO;
import lk.ijse.gdse.serenitymentalhealththerapycenter.dao.DAOFactory;
import lk.ijse.gdse.serenitymentalhealththerapycenter.dao.custom.PatientDAO;
import lk.ijse.gdse.serenitymentalhealththerapycenter.dao.custom.PaymentDAO;
import lk.ijse.gdse.serenitymentalhealththerapycenter.dto.PatientDTO;
import lk.ijse.gdse.serenitymentalhealththerapycenter.dto.PaymentDTO;
import lk.ijse.gdse.serenitymentalhealththerapycenter.entity.Payment;

import java.util.ArrayList;

public class PaymentBOImpl implements PaymentBO {

    PaymentDAO paymentDAO = DAOFactory.getInstance().getDAO(DAOFactory.DaoType.PAYMENT);

    @Override
    public String getNextPaymentId() throws Exception {
        return paymentDAO.getNextPaymentId();
    }

    @Override
    public ArrayList<PaymentDTO> getAllPayments() throws Exception {
        ArrayList<PaymentDTO> paymentDTOS = new ArrayList<>();
        ArrayList<Payment> payments = (ArrayList<Payment>) paymentDAO.getAll();

        for (Payment payment : payments) {
            paymentDTOS.add(new PaymentDTO(
                    payment.getId(),
                    payment.getSessionId(),
                    payment.getPatientName(),
                    payment.getAmount(),
                    payment.getPaymentMethod(),
                    payment.getPaymentDate(),
                    payment.getStatus(),
                    payment.getPaidAmount(),
                    payment.getBalance()
            ));
        }
        return paymentDTOS;
    }

    @Override
    public boolean savePayment(PaymentDTO paymentDTO) throws Exception {
        return paymentDAO.save(new Payment(
                paymentDTO.getId(),
                paymentDTO.getSessionId(),
                paymentDTO.getPatientName(),
                paymentDTO.getAmount(),
                paymentDTO.getPaymentMethod(),
                paymentDTO.getPaymentDate(),
                paymentDTO.getStatus(),
                paymentDTO.getPaidAmount(),
                paymentDTO.getBalance()
        ));
    }

    @Override
    public boolean updatePayment(PaymentDTO paymentDTO) throws Exception {
        return paymentDAO.update(new Payment(
                paymentDTO.getId(),
                paymentDTO.getSessionId(),
                paymentDTO.getPatientName(),
                paymentDTO.getAmount(),
                paymentDTO.getPaymentMethod(),
                paymentDTO.getPaymentDate(),
                paymentDTO.getStatus(),
                paymentDTO.getPaidAmount(),
                paymentDTO.getBalance()
        ));
    }

    @Override
    public boolean deletePayment(String paymentId) throws Exception {
        return paymentDAO.delete(paymentId);
    }

    @Override
    public PaymentDTO searchPayment(String paymentId) throws Exception {
        Payment payment = paymentDAO.search(paymentId);

        if (payment == null) {
            return null;
        }
        return new PaymentDTO(
                payment.getId(),
                payment.getSessionId(),
                payment.getPatientName(),
                payment.getAmount(),
                payment.getPaymentMethod(),
                payment.getPaymentDate(),
                payment.getStatus(),
                payment.getPaidAmount(),
                payment.getBalance()
        );
    }
}
