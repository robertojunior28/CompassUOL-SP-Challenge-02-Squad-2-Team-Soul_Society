package br.com.compassuol.pb.challenge.ecommerce.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

import java.util.Set;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", nullable = false, unique = true)
    private Integer customerId;

    @Column(name = "name", nullable = false)
    @Size(min = 3)
    private String name;

    //@CPF(message = "CPF inválido")
    @Column(name = "CPF", nullable = false, unique = true)
    private String cpf;

    @Email
    @Column(name = "email", nullable = false, unique = true)
    private String email;


    @Column(name = "active", nullable = false)
    private Boolean active;

    @OneToMany(mappedBy = "customer")
    private Set<Order> orders;


    public Customer() {
    }

    public Customer(String name, String cpf, String email, Boolean active) {
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.active = active;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}