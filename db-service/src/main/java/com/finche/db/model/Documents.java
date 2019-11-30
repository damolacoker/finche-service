package com.finche.db.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.finche.db.model.enums.DocumentType;
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
@Document(collection = "documents")
public class Documents {

    @Id
    private String id;

    private String name;

    private DocumentType documentType;
}
