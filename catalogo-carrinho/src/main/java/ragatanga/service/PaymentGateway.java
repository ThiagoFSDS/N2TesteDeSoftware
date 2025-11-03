package ragatanga.service;


public interface PaymentGateway {
    PaymentResult process(PaymentRequest request);
}


class PaymentRequest {
    public final String cartId;
    public final double amount;
    public PaymentRequest(String cartId, double amount){ this.cartId = cartId; this.amount = amount; }
}


class PaymentResult {
    public final boolean approved;
    public final String code;
    public PaymentResult(boolean approved, String code){ this.approved = approved; this.code = code; }
}