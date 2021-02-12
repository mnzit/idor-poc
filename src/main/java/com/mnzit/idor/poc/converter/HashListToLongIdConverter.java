package com.mnzit.idor.poc.converter;

import com.fasterxml.jackson.databind.util.StdConverter;
import com.mnzit.idor.poc.hasher.service.IdHasher;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Manjit Shakya
 * @email manjit.shakya@f1soft.com
 */
@Component
@AllArgsConstructor
public class HashListToLongIdConverter extends StdConverter<List<String>, List<Long>> {

    private final IdHasher idHasher;

    @Override
    public List<Long> convert(List<String> hashes) {

        final List<Long> longIds = hashes
                .stream()
                .map(idHasher::decode)
                .collect(Collectors.toList());

        return longIds;
    }
}
