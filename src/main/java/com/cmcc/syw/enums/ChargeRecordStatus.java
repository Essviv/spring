package com.cmcc.syw.enums;

import java.util.HashMap;
import java.util.Map;

public enum ChargeRecordStatus {
    INVALIDED(-1, "已失效"),//二维码黑白名单用
    UNUSED(0, "未使用"),
    WAIT(1, "待充值"),
    PROCESSING(2, "已发送充值请求"),
    COMPLETE(3, "充值成功"),
    FAILED(4, "充值失败");

    private Integer code;

    private String message;

    ChargeRecordStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static Map<String, String> toMap() {
        Map<String, String> map = new HashMap<String, String>();
        for (ChargeRecordStatus item : ChargeRecordStatus.values()) {
            map.put(item.getCode().toString(), item.getMessage());
        }
        return map;
    }

    public static ChargeRecordStatus fromValue(int code) {
        for (ChargeRecordStatus chargeRecordStatus : ChargeRecordStatus.values()) {
            if (chargeRecordStatus.getCode() == code) {
                return chargeRecordStatus;
            }
        }

        return null;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
