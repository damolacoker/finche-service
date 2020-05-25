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
@Document(collection = "implementationfinancing")
public class ImplementationFinancing {

    @JsonProperty("projectCost")
    private String projectCost;

    @JsonProperty("implementationSchedule")
    private String implementationSchedule;

    @JsonProperty("additionalSource")
    private String additionalSource;
}
