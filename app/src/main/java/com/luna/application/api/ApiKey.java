package com.luna.application.api;

public interface ApiKey {

    String KEY            = "b456ab5a68fb51c242edff6501cb0905";

    String BIRTH_COMPUTER = "http://apis.juhe.cn/birthEight/query?year=%s&month=%s&day=%s&hour=%s&key=%s";

    String QR_CODE        = "http://apis.juhe.cn/qrcode/api?type=%s&fgcolor=%s&w=%s&m=%s&text=%s&key=%s";
}
