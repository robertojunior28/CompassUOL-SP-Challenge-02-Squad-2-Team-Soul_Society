package br.com.compassuol.pb.challenge.ecommerce.repository;

import br.com.compassuol.pb.challenge.ecommerce.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}