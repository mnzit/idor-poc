# Prevent IDOR in Spring Application

_pom.xml_
```xml
 <dependency>
    <groupId>org.hashids</groupId>
    <artifactId>hashids</artifactId>
    <version>1.0.3</version>
</dependency>
```
_IdHasher.java_
```java
public interface IdHasher {

    public String encode(Long id);

    public Long decode(String hashId);
}

```
>Currently i have used HashIds so, you can change you hasher just by extending the IdHasher Interface

_IdHashIdsImpl.java_
```java
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
```

_AppConfig.java_
```java
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
        registry.addFormatterForFieldAnnotation(new UnhashFormatterFactory(idHasher()));
    }
}
```

_Unhash.java_
```java
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Unhash {
}
```


_IsHash.java_
```java
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsHashValidator.class)
@Documented
public @interface IsHash {

    String message() default "Field value should be from list of ";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
```

_HashToLongParamFormatter.java_
```java
@Slf4j
@AllArgsConstructor
public class HashToLongParamFormatter implements Formatter<Long> {

    private final IdHasher idHasher;


    @Override
    public Long parse(String hash, Locale locale) throws ParseException {
        log.debug("hash : {}", hash);

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
```

_UnhashFormatterFactory.java_
```java
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
```

> Jackson converters comes in handly to convert Long id to hash

_LongIdToHashConverter.java_ and _HashToLongIdConverter.java_
```java
@Component
@AllArgsConstructor
public class LongIdToHashConverter extends StdConverter<Long, String> {

    private final IdHasher idHasher;

    @Override
    public String convert(Long id) {
        return idHasher.encode(id);
    }
}

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
```