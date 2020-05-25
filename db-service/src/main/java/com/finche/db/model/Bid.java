package com.finche.db.model;

import com.fasterxml.jackson.annotation.*;
import com.finche.db.model.enums.EnergyType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.Version;
import java.math.BigDecimal;
import java.util.Date;

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
@Document(collection = "bid")
public class Bid {

    @Id
    @JsonIgnore
    private String id;

    @JsonProperty("projectName")
    private String name;

    @JsonProperty("principal")
    private BigDecimal principal;

    @JsonProperty("tenor")
    private String tenor;

    @JsonProperty("energyType")
    private String energyType;

    @JsonProperty("projectToStart")
    private Date projectToStart;

    @JsonProperty("summary")
    private String summary;

    @JsonProperty("milestone")
    private String milestone;

    @JsonProperty("numberOfBids")
    private String numberOfBids;

    @Version
    @JsonIgnore
    private Long version;
}
