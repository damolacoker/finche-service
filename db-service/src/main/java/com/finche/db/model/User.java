package com.finche.db.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.finche.db.model.enums.Role;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.Version;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "user")
public class User  {

    @Id
    private String id;

    private String username;

    private String password;

    private Role role;

    @Version
    private Long version;

}
