package com.finche.db.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "contract")
public class Contract {

    @Id
    private String id;

    private String name;

    private String location;

    private Company company;

    private Date startDate;

    private Date endDate;

    @Version
    private Long version;
}
