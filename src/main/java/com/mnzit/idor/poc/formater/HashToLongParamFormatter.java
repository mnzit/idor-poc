package com.mnzit.idor.poc.formater;

import com.mnzit.idor.poc.hasher.service.IdHasher;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

@Slf4j
@AllArgsConstructor
public class HashToLongParamFormatter implements Formatter<Long> {

    private final IdHasher idHasher;

    @Override
    public Long parse(String hash, Locale locale) throws ParseException {
        log.debug("hash : {}", hash);
        /**
         * Incase of number is passed instead of hash -1 is returned with is then catched by the Constraint Validator
         */
        if (StringUtils.isNumeric(hash)) {
            return -1L;

        }
        final Long decoded = idHasher.decode(hash);
        return decoded;
    }

    @Override
    public String print(Long id, Locale locale) {
        log.debug("id : {}", id);
        return idHasher.encode(id);
    }
}