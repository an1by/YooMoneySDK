package net.aniby.yoomoney.modules.account;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LinkedCard {
    @SerializedName("pan_fragment")
    private final String panFragment;
    @SerializedName("type")
    private final String type;
}
