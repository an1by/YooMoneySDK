package net.aniby.test;

import net.aniby.yoomoney.client.YooMoneyClient;
import net.aniby.yoomoney.modules.account.AccountInfo;

import java.io.IOException;

public class GetAccountInfo {
    public static void main(String[] args) throws IOException {
        YooMoneyClient client = new YooMoneyClient(
                "",
                ""
        );
        AccountInfo accountInfo = client.getAccountInfo();
        System.out.println(accountInfo.toString());
    }
}
