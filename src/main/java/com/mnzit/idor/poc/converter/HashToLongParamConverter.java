package com.mnzit.idor.poc.converter;

import com.mnzit.idor.poc.hasher.service.IdHasher;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;

@AllArgsConstructor
public class HashToLongParamConverter implements Converter<String, Long> {

    private final IdHasher idHasher;

    @Override
    public Long convert(String token) {

        if(StringUtils.isNumeric(token)){
            throw new RuntimeException("Exception occured");
        }

        final Long decoded = idHasher.decode(token);
        return decoded;
    }
}