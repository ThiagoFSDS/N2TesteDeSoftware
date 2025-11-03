package ragatanga.service;


public class FakePaymentGateway implements PaymentGateway {
    private final boolean approve;
    private final long delayMillis;


    public FakePaymentGateway(boolean approve, long delayMillis) {
        this.approve = approve; this.delayMillis = delayMillis;
    }


    @Override
    public PaymentResult process(PaymentRequest request) {
        try { Thread.sleep(delayMillis); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        if (approve) return new PaymentResult(true, "APPROVED");
        return new PaymentResult(false, "DECLINED");
    }
}