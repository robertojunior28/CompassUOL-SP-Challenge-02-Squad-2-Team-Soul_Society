package br.com.compassuol.pb.challenge.ecommerce.services;

import br.com.compassuol.pb.challenge.ecommerce.entities.Payment;
import br.com.compassuol.pb.challenge.ecommerce.entities.Product;
import br.com.compassuol.pb.challenge.ecommerce.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PaymentDaoService {

    private PaymentRepository paymentRepository;
    @Autowired
    public PaymentDaoService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public List<Payment> retrieveAllProducts() {
        return paymentRepository.findAll();
    }

    public Payment save(Payment product) {
        return paymentRepository.save(product);
    }

    public Payment findById(Integer id){
        return paymentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Product not found with ID: " + id));
    }

    public Payment updateProduct(Integer id,Payment updatedPayment){


        return paymentRepository.save();
    }

    public void deleteById(int id) {
        paymentRepository.deleteById(id);
    }
}
