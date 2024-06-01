package ru.rastyapin.rest.template.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

    private Long id;
    private String name;
    private String lastName;
    private Byte age;

}
