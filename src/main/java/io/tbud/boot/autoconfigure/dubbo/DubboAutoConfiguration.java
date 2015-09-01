package io.tbud.boot.autoconfigure.dubbo;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.ProviderConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.AnnotationBean;
import com.alibaba.dubbo.rpc.Exporter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * Created by mind on 9/1/15.
 */
@Slf4j
@Configuration
@ConditionalOnClass(Exporter.class)
@EnableConfigurationProperties({DubboAnnotation.class, DubboApplication.class, DubboProtocol.class, DubboRegistry.class, DubboProvider.class})
public class DubboAutoConfiguration {

    @Autowired
    private DubboAnnotation dubboAnnotation;

    @Autowired
    private DubboApplication dubboApplication;

    @Autowired
    private DubboProtocol dubboProtocol;

    @Autowired
    private DubboProvider dubboProvider;

    @Autowired
    private DubboRegistry dubboRegistry;

    @PostConstruct
    public void init() {
        annotationBean();
    }


    /**
     * 设置Application信息
     *
     * @return
     */
    @Bean
    public ApplicationConfig applicationConfig() {
        log.debug("ApplicationConfig:{}", dubboApplication);
        ApplicationConfig applicationConfig = new ApplicationConfig();

        applicationConfig.setName(dubboApplication.getName());
        applicationConfig.setLogger(dubboApplication.getLogger());

        return applicationConfig;
    }

    public AnnotationBean annotationBean() {
        log.debug("AnnotationBean:{}", dubboAnnotation);

        AnnotationBean annotationBean = new AnnotationBean();
        annotationBean.setPackage(dubboAnnotation.getPackageName());

        return annotationBean;
    }

    @Bean
    public ProtocolConfig protocolConfig() {
        log.debug("ProtocolConfig:{}", dubboProtocol);
        ProtocolConfig protocolConfig = new ProtocolConfig();

        protocolConfig.setName(dubboProtocol.getName());
        protocolConfig.setPort(dubboProtocol.getPort());
        protocolConfig.setAccesslog(String.valueOf(dubboProtocol.isAccessLog()));

        return protocolConfig;
    }

    @Bean
    public ProviderConfig providerConfig(ApplicationConfig applicationConfig,
                                         RegistryConfig registryConfig,
                                         ProtocolConfig protocolConfig) {
        log.debug("ProviderConfig:{}", dubboProvider);
        ProviderConfig providerConfig = new ProviderConfig();

        providerConfig.setTimeout(dubboProvider.getTimeout());
        providerConfig.setRetries(dubboProvider.getRetries());
        providerConfig.setFilter(dubboProvider.getFilter());
        providerConfig.setDelay(dubboProvider.getDelay());

        providerConfig.setApplication(applicationConfig);
        providerConfig.setRegistry(registryConfig);
        providerConfig.setProtocol(protocolConfig);

        return providerConfig;
    }

    @Bean
    public RegistryConfig registryConfig() {
        log.debug("RegistryConfig:{}", dubboRegistry);
        RegistryConfig registryConfig = new RegistryConfig();

        registryConfig.setProtocol(dubboRegistry.getProtocol());
        registryConfig.setAddress(dubboRegistry.getAddress());
        registryConfig.setRegister(dubboRegistry.isRegister());
        registryConfig.setSubscribe(dubboRegistry.isSubscribe());

        return registryConfig;
    }
}
