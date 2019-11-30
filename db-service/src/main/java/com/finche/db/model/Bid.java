package com.finche.db.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "bid")
public class Bid {

    @Id
    private String id;

    private BigDecimal principal;

    private EnergyType energyType;

    private Date projectToStart;

    private  String summary;

    private String milestone;

    @Version
    private Long version;
}
