package com.mnzit.idor.poc.hasher.service.impl;

import com.mnzit.idor.poc.hasher.service.IdHasher;
import lombok.AllArgsConstructor;
import org.hashids.Hashids;

/**
 * @author Manjit Shakya
 * @email manjit.shakya@f1soft.com
 */
@AllArgsConstructor
public class IdHashIdsImpl implements IdHasher {

    private final Hashids hashids;

    @Override
    public String encode(Long id) {
        return hashids.encode(id);
    }

    @Override
    public Long decode(String hashId) {
        return hashids.decode(hashId)[0];
    }
}
