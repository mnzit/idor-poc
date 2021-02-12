package com.mnzit.idor.poc.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mnzit.idor.poc.converter.LongIdToHashConverter;
import lombok.*;

import java.io.Serializable;

/**
 * @author Manjit Shakya
 * @email manjit.shakya@f1soft.com
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Student implements Serializable {

    @JsonSerialize(converter = LongIdToHashConverter.class)
    private Long id;
    private String name;

}
