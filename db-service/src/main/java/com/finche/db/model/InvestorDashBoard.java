package com.finche.db.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "investordashboard")
public class InvestorDashBoard {

    @Id
    private String id;
}
