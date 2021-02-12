package com.mnzit.idor.poc.hasher.service;

/**
 * @author Manjit Shakya
 * @email manjit.shakya@f1soft.com
 */
public interface IdHasher {

    public String encode(Long id);

    public Long decode(String hashId);
}
