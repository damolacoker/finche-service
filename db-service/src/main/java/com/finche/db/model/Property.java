package com.finche.db.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.finche.db.model.enums.PropertyType;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.Id;
import javax.persistence.Version;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "property")
public class Property {

    @Id
    private String id;

    private String name;

    private PropertyType propertyType;

    private String address;

    private String country;

    private String state;

    private String postCode;

    private Integer gpsLocation;

    private String summary;

    private List<Documents> documents;

    @Version
    private Long version;
}
