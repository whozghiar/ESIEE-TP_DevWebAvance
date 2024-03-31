package fr.unilasalle.tp_garage_auto.beans;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString (exclude = "vehicules")
@Data
@Builder
@Valid
@EqualsAndHashCode(exclude = "vehicules")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom est obligatoire")
    private String nom;
    @NotBlank(message = "Le prénom est obligatoire")
    private String prenom;
    @NotBlank(message = "Le téléphone est obligatoire")
    private String telephone;
    @NotBlank(message = "L'email est obligatoire")
    private String email;

    // Relation avec Véhicule : un client peut avoir plusieurs véhicules
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference("client-vehicule")
    private Set<Vehicule> vehicules;
}