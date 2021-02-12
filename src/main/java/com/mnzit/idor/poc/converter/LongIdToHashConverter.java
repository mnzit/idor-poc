package com.mnzit.idor.poc.converter;

import com.fasterxml.jackson.databind.util.StdConverter;
import com.mnzit.idor.poc.hasher.service.IdHasher;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Manjit Shakya
 * @email manjit.shakya@f1soft.com
 */
@Component
@AllArgsConstructor
public class LongIdToHashConverter extends StdConverter<Long, String> {

    private final IdHasher idHasher;

    @Override
    public String convert(Long id) {
        return idHasher.encode(id);
    }
}
