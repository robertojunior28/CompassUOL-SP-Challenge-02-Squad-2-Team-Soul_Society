package br.com.compassuol.pb.challenge.ecommerce.services;

import br.com.compassuol.pb.challenge.ecommerce.entities.Payment;
import br.com.compassuol.pb.challenge.ecommerce.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

//    public List<Payment> retrieveAllPayment() {
//        return paymentRepository.findAll();
//    }

    public Payment save(Payment payment) {
        return paymentRepository.save(payment);
    }

//    public Payment findById(Integer id){
//        return paymentRepository.findById(id)
//                .orElseThrow(() -> new NoSuchElementException("Payment not found with ID: " + id));
//    }
//
//    public Payment updateProduct(Integer id,Payment updatedPayment){
//
//
//        return paymentRepository.save(updatedPayment);
//    }
//
//    public void deleteById(int id) {
//        paymentRepository.deleteById(id);
//    }
}
