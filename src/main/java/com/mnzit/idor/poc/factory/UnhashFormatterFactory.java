package com.mnzit.idor.poc.factory;

import com.mnzit.idor.poc.annotation.Unhash;
import com.mnzit.idor.poc.formater.HashToLongParamFormatter;
import com.mnzit.idor.poc.hasher.service.IdHasher;
import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Manjit Shakya
 * @email manjit.shakya@f1soft.com
 */
public class UnhashFormatterFactory implements AnnotationFormatterFactory<Unhash> {

    private final IdHasher idHasher;

    public UnhashFormatterFactory(IdHasher idHasher) {
        this.idHasher = idHasher;
    }

    @Override
    public Set<Class<?>> getFieldTypes() {
        Set<Class<?>> set = new HashSet<Class<?>>();
        set.add(Long.class);
        return set;
    }

    @Override
    public Printer<?> getPrinter(Unhash unhash, Class<?> aClass) {
        return new HashToLongParamFormatter(idHasher);
    }

    @Override
    public Parser<?> getParser(Unhash unhash, Class<?> aClass) {
        return new HashToLongParamFormatter(idHasher);
    }
}
