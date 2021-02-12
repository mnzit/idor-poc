package com.mnzit.idor.poc.converter;

import com.fasterxml.jackson.databind.util.StdConverter;
import com.mnzit.idor.poc.hasher.service.IdHasher;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Manjit Shakya
 * @email manjit.shakya@f1soft.com
 */
@Slf4j
@Component
@AllArgsConstructor
public class HashToLongIdConverter extends StdConverter<String, Long> {

    private final IdHasher idHasher;

    @Override
    public Long convert(String hash) {
        log.info("Hash : {}", hash);
        return idHasher.decode(hash);
    }
}
