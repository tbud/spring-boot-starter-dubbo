package io.tbud.boot.dubbo.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by hanqingbin on 15/7/28.
 */
@Data
@ConfigurationProperties(prefix = "dubbo.application")
public class DubboApplication {

    private String name = "sample";

    private String logger = "slf4j";
}
