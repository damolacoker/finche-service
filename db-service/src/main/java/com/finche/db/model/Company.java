package com.finche.db.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "company")
public class Company {

    @Id
    private String id;

    private String name;

    private String website;

    private String contactPerson;

    private Integer contactNumber;

    private Integer yearsOfOperations;

    private User user;

    private  String designation;

    @Version
    private Long version;
}
