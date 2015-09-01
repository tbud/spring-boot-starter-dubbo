package io.tbud.boot.autoconfigure.dubbo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by mind on 9/1/15.
 */
@Data
@ConfigurationProperties(prefix = "dubbo.annotation")
public class DubboAnnotation {
    /**
     * annotation package name
     */
    private String packageName;
}
