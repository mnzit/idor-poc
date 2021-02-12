package com.mnzit.idor.poc.config;

import com.mnzit.idor.poc.factory.UnhashFormatterFactory;
import com.mnzit.idor.poc.hasher.service.IdHasher;
import com.mnzit.idor.poc.hasher.service.impl.IdHashIdsImpl;
import lombok.AllArgsConstructor;
import org.hashids.Hashids;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author Manjit Shakya
 * @email manjit.shakya@f1soft.com
 */
@Configuration
@AllArgsConstructor
public class AppConfig extends WebMvcConfigurationSupport {

    @Bean
    Hashids hashids() {
        return new Hashids("0#jwL0#jwL3BNr_Do3BNr_Do", 25);
    }


    @Bean
    IdHasher idHasher() {
        return new IdHashIdsImpl(hashids());
    }


    @Override
    protected void addFormatters(FormatterRegistry registry) {
//        registry.addConverter(new HashToLongParamConverter(idHasher()));
//        registry.addFormatter(new HashToLongParamFormatter(idHasher()));
        registry.addFormatterForFieldAnnotation(new UnhashFormatterFactory(idHasher()));
    }

}
