package net.aniby.yoomoney.modules.forms;

import lombok.Getter;
import net.aniby.yoomoney.requests.BodyRequestField;
import net.aniby.yoomoney.requests.MethodRequest;

import java.util.List;

@Getter
public class QuickPay extends MethodRequest {
    public static final List<String> PAYMENT_TYPES = List.of("PC", "AC");

    @BodyRequestField("receiver")
    private final String receiver;
    @BodyRequestField("quickpay-form")
    private final String quickPayForm = "button";
    @BodyRequestField("paymentType")
    private final List<String> paymentTypes; // PC / AC
    @BodyRequestField("sum")
    private final double amount;
    @BodyRequestField("label")
    private final String label;
    @BodyRequestField("successURL")
    private final String successURL;

    /**
     * Creates <a href="https://yoomoney.ru/docs/payment-buttons/using-api/forms">YooMoney form</a> builder
     * @param amount         Transfer amount (the amount debited from the sender)
     * @param paymentTypes   Payment methods. Possible values:
     *                       <br/>
     *                       - <code>PC</code> for a payment from a YooMoney wallet;
     *                       <br/>
     *                       - <code>AC</code> for a payment from a bank card.
     *                       <br/>
     *                       Can be used both
     * @param label          The label that a site or app assigns to a certain transfer. For instance, a code or order identifier may be used for this label
     * @param successURL     URL where the user is redirected after the transfer
     */
    public QuickPay(String receiver, double amount, List<String> paymentTypes, String label, String successURL) {
        this.receiver = receiver;
        this.paymentTypes = paymentTypes;
        this.amount = amount;
        this.label = label;
        this.successURL = successURL;
    }

    /**
     * Creates <a href="https://yoomoney.ru/docs/payment-buttons/using-api/forms">YooMoney form</a> builder
     * @param amount         Transfer amount (the amount debited from the sender)
     * @param paymentTypes   Payment methods. Possible values:
     *                       <br/>
     *                       - <code>PC</code> for a payment from a YooMoney wallet;
     *                       <br/>
     *                       - <code>AC</code> for a payment from a bank card.
     *                       <br/>
     *                       Can be used both
     * @param label          The label that a site or app assigns to a certain transfer. For instance, a code or order identifier may be used for this label
     */
    public QuickPay(String receiver, double amount, List<String> paymentTypes, String label) {
        this.receiver = receiver;
        this.paymentTypes = paymentTypes;
        this.amount = amount;
        this.label = label;
        this.successURL = null;
    }

    /**
     * Creates <a href="https://yoomoney.ru/docs/payment-buttons/using-api/forms">YooMoney form</a> builder
     * @param amount         Transfer amount (the amount debited from the sender)
     * @param paymentTypes   Payment methods. Possible values:
     *                       <br/>
     *                       - <code>PC</code> for a payment from a YooMoney wallet;
     *                       <br/>
     *                       - <code>AC</code> for a payment from a bank card.
     *                       <br/>
     *                       Can be used both
     */
    public QuickPay(String receiver, double amount, List<String> paymentTypes) {
        this.receiver = receiver;
        this.paymentTypes = paymentTypes;
        this.amount = amount;
        this.label = null;
        this.successURL = null;
    }

    /**
     * Creates <a href="https://yoomoney.ru/docs/payment-buttons/using-api/forms">YooMoney form</a> builder
     * <br/>
     * Used both payment types
     * @param amount         Transfer amount (the amount debited from the sender)
     */
    public QuickPay(String receiver, double amount) {
        this.receiver = receiver;
        this.paymentTypes = PAYMENT_TYPES;
        this.amount = amount;
        this.label = null;
        this.successURL = null;
    }
}
