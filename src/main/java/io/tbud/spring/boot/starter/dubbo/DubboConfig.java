package io.tbud.spring.boot.starter.dubbo;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.ProviderConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.AnnotationBean;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by mind on 9/1/15.
 */
@Configuration
public class DubboConfig {

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


    /**
     * 设置Application信息
     *
     * @return
     */
    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();

        applicationConfig.setName(dubboApplication.getName());
        applicationConfig.setLogger(dubboApplication.getLogger());

        return applicationConfig;
    }

    @Bean
    public AnnotationBean annotationBean() {
        AnnotationBean annotationBean = new AnnotationBean();

        annotationBean.setPackage(dubboAnnotation.getPackageName());

        return annotationBean;
    }

    @Bean
    public ProtocolConfig protocolConfig() {
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
        RegistryConfig registryConfig = new RegistryConfig();

        registryConfig.setProtocol(dubboRegistry.getProtocol());
        registryConfig.setAddress(dubboRegistry.getAddress());
        registryConfig.setRegister(dubboRegistry.isRegister());
        registryConfig.setSubscribe(dubboRegistry.isSubscribe());

        return registryConfig;
    }
}
