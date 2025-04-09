package com.substring.foodie.controller;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;
import com.substring.foodie.entity.Order;
import com.substring.foodie.entity.enums.PaymentStatus;
import com.substring.foodie.exception.ResourceNotFoundException;
import com.substring.foodie.playload.PaymentVerifyDto;
import com.substring.foodie.repository.OrderRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/payments")
//@CrossOrigin("http://localhost:4200")
public class PaymentController {


    @Value("${razorpay.key_id}")
    private String razorpayKeyId;
    @Value("${razorpay.key_secret}")
    private String razorpayKeySecret;

    @Autowired
    private OrderRepository orderRepository;


    @PostMapping("/verify/{orderId}")
    public ResponseEntity<?> verifyPayment(
            @PathVariable String orderId,
            @RequestBody PaymentVerifyDto paymentVerifyDto
    ) throws RazorpayException {

        // razorpay order instance bana lete hia

        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + orderId));


        RazorpayClient client = new RazorpayClient(razorpayKeyId, razorpayKeySecret);

        JSONObject options = new JSONObject();
        options.put("razorpay_order_id", paymentVerifyDto.razorpayOrderId());
        options.put("razorpay_payment_id", paymentVerifyDto.razorpayPaymentId());
        options.put("razorpay_signature", paymentVerifyDto.razorpaySignature());
        boolean isVerified = Utils.verifyPaymentSignature(options, razorpayKeySecret);
        if (isVerified) {
            order.setPaymentStatus(PaymentStatus.PAID);
            order.setPaymentId(paymentVerifyDto.razorpayPaymentId());
            orderRepository.save(order);
            return ResponseEntity.ok(Map.of("message","Payment verified successfully"));
        } else {
            order.setPaymentStatus(PaymentStatus.NOT_PAID);
            orderRepository.save(order);
            return ResponseEntity.ok(Map.of("message","Payment verification failed"));
        }


    }


    @PostMapping("/{orderId}")
    public ResponseEntity<?> createPaymentOrder(
            @PathVariable String orderId
    ) throws RazorpayException {

        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + orderId));

        // razorpay order instance bana lete hia
        RazorpayClient razorpayClient = new RazorpayClient(razorpayKeyId, razorpayKeySecret);

        JSONObject jsonObject = new JSONObject();
        ;

        jsonObject.put("amount", order.getTotalAmount() * 100); // amount in paise
        jsonObject.put("currency", "INR");
        jsonObject.put("receipt", order.getId());
        JSONObject noteObject = new JSONObject();
        noteObject.put("about", "This is payment done by " + order.getUser().getName());
        jsonObject.put("notes", noteObject);

        com.razorpay.Order razorpay = razorpayClient.orders.create(jsonObject);

        Map<String, String> response = Map.of(
                "id", razorpay.get("id").toString(),
                "currency", razorpay.get("currency").toString(),
                "amount", razorpay.get("amount").toString(),
                "status", razorpay.get("status").toString(),
                "orderId", order.getId().toString()
        );

        order.setRazorpayOrderId(razorpay.get("id"));
        orderRepository.save(order);
        return ResponseEntity.ok(response);


    }

}
