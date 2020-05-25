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
@Document(collection = "riskassessment")
public class RiskAssessment {

    @JsonProperty("policyRisk")
    private String policyRisk;

    @JsonProperty("environmentalRisk")
    private String environmentalRisk;

    @JsonProperty("competitiveRisk")
    private String competitiveRisk;

    @JsonProperty("reputationRisk")
    private String reputationRisk;

    @JsonProperty("politicalRisk")
    private String politicalRisk;

    @JsonProperty("macroEconomicRisk")
    private String macroEconomicRisk;

    @JsonProperty("microEconomicRisk")
    private String microEconomicRisk;
}
