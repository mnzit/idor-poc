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
public class LongIdListToHashConverter extends StdConverter<List<Long>, List<String>> {

    private final IdHasher idHasher;

    @Override
    public List<String> convert(List<Long> hashes) {

        final List<String> hashIds = hashes
                .stream()
                .map(idHasher::encode)
                .collect(Collectors.toList());

        return hashIds;
    }
}
