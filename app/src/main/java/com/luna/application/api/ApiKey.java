package com.luna.application.api;

public interface ApiKey {

    String BIRTH_COMPUTER = "http://apis.juhe.cn/birthEight/query?year=%s&month=%s&day=%s&hour=%s&key=%s";

    String QR_CODE        = "http://apis.juhe.cn/qrcode/api?type=%s&fgcolor=%s&w=%s&m=%s&text=%s&key=%s";

    String SEARCH_PHONE   = "http://apis.juhe.cn/mobile/get?phone=%s&key=%s";

    /** 新闻头条 */
    String NEWS           = "http://v.juhe.cn/toutiao/index?type=%s&page=%s&page_size=%s&key=%s";

    String KEY_ONE        = "b456ab5a68fb51c242edff6501cb0905";

    String KEY_TWO        = "45377b7f6660c4f780b28d843d5e3da6";

    String KEY_THREE      = "c1c3bee3a9febeec4bd7d99c61ed8faf";

}
