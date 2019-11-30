package com.finche.db.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.finche.db.model.enums.EconomicFundamentals;
import com.finche.db.model.enums.EnergyType;
import com.finche.db.model.enums.Status;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "project")
public class Project {

    @Id
    private String id;

    private String name;

    private String location;

    private EnergyType energyType;

    private Date creation;

    private EconomicFundamentals economicFundamentals;

    private String equipmentAndTechnologyRequirements;

    private String equipmentSupplyChainAndSupplierCredit;

    private String legalAndRegulatoryConsiderations;

    private String fundingSupport;

    private String benchmark;

    private String timeline;

    private Status projectStatus;

    private List<Documents>  documents;

    private String insuranceInfo;

    @Version
    private Long version;

}
