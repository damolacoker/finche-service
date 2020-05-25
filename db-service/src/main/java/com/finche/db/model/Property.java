package com.finche.db.model;

import com.fasterxml.jackson.annotation.*;
import com.finche.db.model.enums.PropertyType;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import javax.persistence.Version;
import java.util.List;

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
@Document(collection = "property")
public class Property {

    @Id
    @JsonIgnore
    private String id;

    @JsonProperty("propertyName")
    private String name;

    @JsonProperty("propertyImage")
    private String propertyImage;

    @JsonProperty("summary")
    private String summary;

    @JsonProperty("companyName")
    private String companyName;

    @JsonProperty("companyLogo")
    private String companyLogo;

    @JsonProperty("status")
    private String status;

    @JsonProperty("propertyType")
    private PropertyType propertyType;

    @JsonProperty("propertyAddress")
    private String address;

    @JsonProperty("country")
    private String country;

    @JsonProperty("state")
    private String state;

    @JsonProperty("postcode")
    private String postCode;

    @JsonProperty("gps")
    private Integer gpsLocation;

    @JsonProperty("3dRenders")
    private String ThreeDRender;

    @JsonProperty("c/o")
    private String co;

    @Version
    @JsonIgnore
    private Long version;
}
