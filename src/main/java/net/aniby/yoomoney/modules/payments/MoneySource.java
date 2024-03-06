package net.aniby.yoomoney.modules.payments;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class MoneySource {
    @SerializedName("allowed")
    private boolean allowed;
    @SerializedName("csc_required")
    private boolean cscRequired;
    @SerializedName("item")
    private MoneySource.Item item;

    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @Getter
    public static class Item {
        @SerializedName("id")
        private int id;
        @SerializedName("pan_fragment")
        private String panFragment;
        @SerializedName("type")
        private String type;
    }
}
