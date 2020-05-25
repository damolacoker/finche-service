package com.finche.db.model;


import com.fasterxml.jackson.annotation.*;
import com.finche.db.model.enums.EnergyType;
import com.finche.db.model.enums.Status;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

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
@Document(collection = "project")
public class Project {

    @Id
    @JsonIgnore
    private String id;

    @JsonProperty("projectName")
    private String name;

    @JsonProperty("energyType")
    private String energyType;

    @JsonProperty("companyName")
    private String companyName;

    @JsonProperty("logo")
    private String logo;

    @JsonProperty("status")
    private String status;

    @JsonProperty("location")
    private String location;

    @JsonProperty("companyName")
    @JsonIgnore
    private EconomicFundamentals economicFundamentals;

    @JsonProperty("techRequirement")
    private String techRequirement;

    @JsonProperty("supplyChain")
    private String supplyChain;

    @JsonProperty("legalConsiderations")
    private String legalConsiderations;

    @JsonProperty("supportExplanation")
    private String supportExplanation;

    @JsonProperty("benchmark")
    private String benchmark;

    @JsonProperty("implementationTime")
    private String implementationTime;

    @JsonProperty("projectDevelopmentType")
    private String projectDevelopmentType;

    @JsonProperty("proposedApproach")
    private String proposedApproach;

    @JsonProperty("qualityAssurancePlan")
    private String qualityAssurancePlan;

    @JsonProperty("impactAssessment")
    @JsonIgnore
    private Status impactAssessment;

    @JsonProperty("keyAreas")
    private String keyAreas;

    @JsonProperty("assetsLiquidity")
    private String assetsLiquidity;

    @JsonProperty("projectPartner")
    private String projectPartner;

    @JsonProperty("insuranceInformation")
    private String insuranceInformation;

    @JsonProperty("offTakersEndUsers")
    @JsonIgnore
    private OffTakersEndUsers offTakersEndUsers;

    @JsonProperty("implementationFinancing")
    @JsonIgnore
    private ImplementationFinancing implementationFinancing;

    @JsonProperty("riskAssessment")
    @JsonIgnore
    private RiskAssessment riskAssessment;

    @JsonProperty("creation")
    @JsonIgnore
    private Date creation;

    @Version
    @JsonIgnore
    private Long version;

}
