package br.com.compassuol.pb.challenge.ecommerce.entities;

import br.com.compassuol.pb.challenge.ecommerce.enums.OrderStatus;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "order_table")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;

    @Enumerated(EnumType.STRING)
    private List<OrderStatus> status;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customer;

    @Column(name = "date")
    private Date date;

    @OneToMany(mappedBy = "order")
    private List<Payment> payments;

    public Order() {
    }

    public Order(Customer customer) {
        this.status.add(OrderStatus.CREATED);
        this.customer = customer;
        this.date = new Date();
    }

    public Integer getId() {
        return orderId;
    }

    public void setId(Integer orderId) {
        this.orderId = orderId;
    }

    public List<OrderStatus> getStatus(String s) {
        return status;
    }

    public void setStatus(List<OrderStatus> status) {
        this.status = status;
    }

    public Customer getCustomer(String customerA) {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getDate(String date) {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
