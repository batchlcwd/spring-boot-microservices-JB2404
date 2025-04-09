import { HttpClient } from '@angular/common/http';
import { Component, inject } from '@angular/core';

declare var Razorpay: any; // Declare Razorpay from the global window object

@Component({
  selector: 'app-cart',
  imports: [],
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.css',
})
export class CartComponent {
  amount = 100;
  orderId = '07d2c2bc-0306-485a-bd29-c9e85afe2766';

  private http = inject(HttpClient);

  createPaymentOrder() {
    this.http
      .post(`http://localhost:8080/api/v1/payments/${this.orderId}`, {})
      .subscribe({
        next: (response: any) => {
          console.log('Payment order created successfully:', response);
          //now use chheckoutjs

          const options = {
            key: `rzp_test_RIW86SSPDVvXjh`,
            amount: response.amount,
            currency: response.currency,
            name: 'Substring Technologies',
            description: 'Test Transaction',
            image:
              'https://www.learncodewithdurgesh.com/_next/image?url=%2F_next%2Fstatic%2Fmedia%2Flcwd_logo.45da3818.png&w=1080&q=75',
            order_id: response.id,
            prefill: {
              //We recommend using the prefill parameter to auto-fill customer's contact information especially their phone number
              name: 'Gaurav Kumar', //your customer's name
              email: 'gaurav.kumar@example.com',
              contact: '9000090000', //Provide the customer's phone number for better conversion rates
            },
            theme: {
              color: '#3399cc',
            },
            handler: (response: any) => {
              console.log(response.razorpay_payment_id);
              console.log(response.razorpay_order_id);
              console.log(response.razorpay_signature);
              alert('Payment successful');
              this.verifyPayment({
                razorpay_payment_id: response.razorpay_payment_id,
                razorpay_order_id: response.razorpay_order_id,
                razorpay_signature: response.razorpay_signature,
              });
            },
          };
          const rzp = new Razorpay(options);
          rzp.on('payment.failed', function (response: any) {
            console.log(response.error.code);
            console.log(response.error.description);
            console.log(response.error.source);
            console.log(response.error.step);
            console.log(response.error.reason);
            console.log(response.error.metadata.order_id);
            console.log(response.error.metadata.payment_id);
            alert('Payment failed');
          });
          rzp.open();
        },
        error: (error) => {
          console.error('Error creating payment order:', error);
        },
      });
  }

  verifyPayment({
    razorpay_payment_id,
    razorpay_order_id,
    razorpay_signature,
  }: {
    razorpay_payment_id: string;
    razorpay_order_id: string;
    razorpay_signature: string;
  }) {
    this.http
      .post(`http://localhost:8080/api/v1/payments/verify/${this.orderId}`, {
        razorpayPaymentId: razorpay_payment_id,
        razorpayOrderId: razorpay_order_id,
        razorpaySignature: razorpay_signature,
      })
      .subscribe({
        next: (response) => {
          console.log('Payment verified successfully:', response);
          alert('Payment verified successfully. Now you can enjoy the service');
        },
        error: (error) => {
          console.error('Error verifying payment:', error);
          alert('Payment verification failed');
        },
      });
  }
}
