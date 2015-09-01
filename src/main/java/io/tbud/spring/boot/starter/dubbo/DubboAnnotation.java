package io.tbud.spring.boot.starter.dubbo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by mind on 9/1/15.
 */
@Data
@Component
@ConfigurationProperties(prefix = "dubbo.protocol", ignoreUnknownFields = false)
public class DubboAnnotation {

    /**
     * annotation package name
     */
    private String packageName;
}
