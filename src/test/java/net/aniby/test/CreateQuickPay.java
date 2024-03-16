package net.aniby.test;

import net.aniby.yoomoney.client.YooMoneyClient;
import net.aniby.yoomoney.modules.account.AccountInfo;
import net.aniby.yoomoney.modules.forms.QuickPay;

import java.io.IOException;

public class CreateQuickPay {
    public static void main(String[] args) throws IOException {
        YooMoneyClient client = new YooMoneyClient(
                "",
                ""
        );
        AccountInfo accountInfo = client.getAccountInfo();
        String url = client.createQuickPayForm(new QuickPay(
                accountInfo.getAccount(), 5, QuickPay.PAYMENT_TYPES, "That's cool 5 rubles"
        ));
        System.out.println(url);
    }
}
