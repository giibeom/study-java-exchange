package woowaapplication.pair.exchange.currency.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Nations {

    KOREA("KRW", "한국"),
    PHILIPPINES("PHP", "필리핀"),
    JAPAN("JPY", "일본"),
    USA("USD", "미국"),
    ;

    private final String currency;
    private final String nationName;

    public static String getApiResponseKey(Nations src, Nations dst) {
        StringBuilder sb = new StringBuilder();
        sb.append(src.getCurrency());
        sb.append(dst.getCurrency());

        return sb.toString();
    }

    public String getFullName() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.nationName);
        sb.append("(");
        sb.append(this.currency);
        sb.append(")");

        return sb.toString();
    }

}
