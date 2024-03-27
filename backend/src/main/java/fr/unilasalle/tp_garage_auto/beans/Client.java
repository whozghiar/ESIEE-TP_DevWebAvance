package fr.unilasalle.tp_garage_auto.beans;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Builder
@Valid
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
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Vehicule> vehicules;
}