package br.com.compassuol.pb.challenge.ecommerce.entities;

import br.com.compassuol.pb.challenge.ecommerce.enums.OrderStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private List<OrderStatus> status;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customer;

    @Column(name = "date")
    private Date date;

    public Order() {
    }

    public Order(Customer customer) {
        this.status.add(OrderStatus.CREATED);
        this.customer = customer;
        this.date = new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<OrderStatus> getStatus() {
        return status;
    }

    public void setStatus(List<OrderStatus> status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
