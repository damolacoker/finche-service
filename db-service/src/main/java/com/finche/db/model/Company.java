package com.finche.db.model;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@AllArgsConstructor(onConstructor = @__(@JsonCreator))
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "company")
public class Company {

    @Id
    @JsonIgnore
    private String id;

    @JsonProperty("companyName")
    private String name;

    @JsonProperty("comapnyWebsite")
    private String website;

    @JsonProperty("companyEmail")
    private String companyEmail;

    @JsonProperty("contactPerson")
    private String contactPerson;

    @JsonProperty("contactNumber")
    private String contactNumber;

    @JsonProperty("yearsOfOperation")
    private String yearsOfOperations;

    @JsonProperty("user")
    @JsonIgnore
    private User user;

    @JsonProperty("designation")
    private String designation;

    @Version
    @JsonIgnore
    private Long version;
}
