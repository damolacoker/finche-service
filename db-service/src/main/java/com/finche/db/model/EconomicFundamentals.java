package com.finche.db.model;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
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
@Document(collection = "economicfundamentals")
public class EconomicFundamentals {

    @JsonProperty("capitalCost")
    private String capitalCost;

    @JsonProperty("operationalCost")
    private String operationalCost;

    @JsonProperty("lifeCycleCost")
    private String lifeCycleCost;

    @JsonProperty("anticipatedRevenues")
    private String anticipatedRevenues;

    @JsonProperty("tenor")
    private String tenor;

    @JsonProperty("startDate")
    private String startDate;
}
