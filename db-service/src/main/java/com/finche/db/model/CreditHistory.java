package com.finche.db.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.finche.db.model.enums.PaymentMethod;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "credithistory")
public class CreditHistory {

    @Id
    private String id;

    private Integer loanNo;

    private Date date;

    private String institution;

    private BigDecimal amount;

    private PaymentMethod paymentMethod;
}
