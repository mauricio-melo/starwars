package com.mmelo.starwars.domain;

import com.mmelo.starwars.commons.Auditable;
import lombok.*;
import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "planet")
public class Planet extends Auditable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "climate")
    @ElementCollection
    private List<String> climate;

    @Column(name = "terrain")
    @ElementCollection
    private List<String> terrain;

    @Builder.Default
    @Column(name = "flg_enabled")
    private Boolean enabled = true;
}
