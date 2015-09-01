package io.tbud.spring.boot.starter.dubbo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by hanqingbin on 15/7/28.
 */
@Data
@Component
@ConfigurationProperties(prefix = "dubbo.application")
public class DubboApplication {

    private String name = "sample";

    private String logger = "slf4j";
}
