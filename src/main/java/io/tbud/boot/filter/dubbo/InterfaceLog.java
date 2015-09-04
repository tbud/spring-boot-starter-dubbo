package io.tbud.boot.filter.dubbo;

import com.alibaba.dubbo.common.json.JSON;
import lombok.Data;

import java.io.Serializable;

@Data
public class InterfaceLog implements Serializable {
    private String invokeTime = String.valueOf(System.currentTimeMillis());
    private String methodName;
    private String senderName;
    private String senderHost;
    private String receiverName;
    private String receiverHost;
    private String srvGroup;
    private String version;
    private String[] paramTypes;
    private Object[] paramValues;
    private Object resultValue;
    private long costTime = 0L;
    private String exceptionMsg;

    @Override
    public String toString() {
        String slfStr;
        try {
            slfStr = JSON.json(this);
        } catch (Exception e) {
            slfStr = toSimpleString();
        }
        return slfStr;
    }

    public String toSimpleString() {
        return String.format("{\"invokeTime\": \"%s\" , \"methodName\": \"%s\", \"senderName\": \"%s\", \"senderHost\": \"%s\", \"receiverName\": \"%s\", \"receiverHost\": \"%s\", \"srvGroup\": \"%s\", \"version\": \"%s\", \"paramTypes\": [], \"paramValues\": [], \"resultValue\": {}, \"costTime\": %s, \"exceptionMsg\": \"%s\"}", this.invokeTime, this.methodName, this.senderName, this.senderHost, this.receiverName, this.receiverHost, this.srvGroup, this.version, this.costTime, this.exceptionMsg);
    }
}
