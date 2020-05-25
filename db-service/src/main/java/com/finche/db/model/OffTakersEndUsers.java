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
@Document(collection = "offtakersendusers")
public class OffTakersEndUsers {

    @JsonProperty("profile")
    private String profile;

    @JsonProperty("financialCapability")
    private String financialCapability;

    @JsonProperty("goveranceStructure")
    private String goveranceStructure;

    @JsonProperty("history")
    private String history;

    @JsonProperty("consumptionData")
    private String consumptionData;

    @JsonProperty("diligence")
    private String diligence;

    @JsonProperty("contractStrenght")
    private String contractStrenght;

    @JsonProperty("companyReputation")
    private String companyReputation;
}
