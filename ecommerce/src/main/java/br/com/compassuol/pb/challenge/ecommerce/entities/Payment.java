package br.com.compassuol.pb.challenge.ecommerce.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Integer paymentId;

    @NotNull
    @Column(name = "payment_method")
    private String paymentMethod;
    @NotNull
    @Column(name = "payment_date")
    private Date paymentDate;
    @ManyToOne
    @JoinColumn(name = "order_id")
    @NotNull
    private Order order;

    public Payment() {
    }


    public Payment(String paymentMethod, Order order) {
        this.paymentMethod = paymentMethod;
        this.paymentDate = new Date();
    }

    public Payment(Integer paymentId, Date paymentDate, Order order) {
        this.paymentId = paymentId;
        this.paymentDate = paymentDate;

        this.order = order;
    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId +
                ", paymentDate=" + paymentDate +
                ", order=" + order +
                '}';
    }
}
