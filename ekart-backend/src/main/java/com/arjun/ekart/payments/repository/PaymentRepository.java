package com.arjun.ekart.payments.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.arjun.ekart.payments.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long>{

	Optional<Payment> findByRazorpayOrderId(String razorpayOrderId);


}
