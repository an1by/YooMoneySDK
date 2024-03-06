package net.aniby.yoomoney.modules.goods;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class DigitalGoods {
    @SerializedName("article")
    private List<Article> articles;
    @SerializedName("bonus")
    private List<Bonus> bonuses;

    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @Getter
    public static class Article {
        @SerializedName("merchantArticleId")
        private String merchantArticleId;
        @SerializedName("serial")
        private String serial;
        @SerializedName("secret")
        private String secret;
    }
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @Getter
    public static class Bonus {
        @SerializedName("serial")
        private String serial;
        @SerializedName("secret")
        private String secret;
    }
}
