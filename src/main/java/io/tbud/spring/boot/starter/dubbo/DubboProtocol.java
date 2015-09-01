package io.tbud.spring.boot.starter.dubbo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by hanqingbin on 15/7/28.
 */
@Data
@Component
@ConfigurationProperties(prefix = "dubbo.protocol")
public class DubboProtocol {

    /**
     * 接口协议
     */
    private String name = "dubbo";
    /**
     * 暴露服务的端口
     */
    private int port = 20880;

    /**
     * 是否记录接口日志
     */
    private boolean accessLog = true;
}
