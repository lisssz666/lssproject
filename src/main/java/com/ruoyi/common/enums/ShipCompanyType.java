package com.ruoyi.common.enums;

public enum ShipCompanyType {
    HAIKA("0", "海卡"), HAIPAI("1", "海运"), AIRLIFT("2", "空运"),RAILWAY("3", "铁路");

    private final String code;
    private final String info;

    ShipCompanyType(String code, String info)
    {
        this.code = code;
        this.info = info;
    }

    public String getCode()
    {
        return code;
    }

    public String getInfo()
    {
        return info;
    }
}
