package com.huy.mse.config;

import com.huy.mse.common.JsonPropertySourceFactory;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:config.json", factory = JsonPropertySourceFactory.class)
@ConfigurationProperties
@Getter
@Setter
public class JsonProperties {
    private int defaultPageNumber;
    private int defaultPageSize;
    private int defaultMaxPageSize;
}
