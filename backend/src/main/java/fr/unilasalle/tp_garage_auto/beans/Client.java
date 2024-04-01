package fr.unilasalle.tp_garage_auto.beans;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
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
    @NotEmpty(message = "Le nom ne peut pas être vide")
    @NotNull(message = "Le nom ne peut pas être nul")
    @Size(min = 2, max = 50, message = "Le nom doit être compris entre 2 et 50 caractères")
    private String nom;

    @NotBlank(message = "Le prénom est obligatoire")
    @NotEmpty(message = "Le prénom ne peut pas être vide")
    @NotNull(message = "Le prénom ne peut pas être nul")
    @Size(min = 2, max = 50, message = "Le prénom doit être compris entre 2 et 50 caractères")
    private String prenom;

    @NotBlank(message = "Le téléphone est obligatoire")
    @NotEmpty(message = "Le téléphone ne peut pas être vide")
    @NotNull(message = "Le téléphone ne peut pas être nul")
    @Size(min = 10, max = 10, message = "Le téléphone doit être composé de 10 chiffres")
    @Pattern(regexp = "0[1-9][0-9]{8}", message = "Le téléphone doit être composé de 10 chiffres")
    private String telephone;

    @NotBlank(message = "L'email est obligatoire")
    @NotEmpty(message = "L'email ne peut pas être vide")
    @NotNull(message = "L'email ne peut pas être nul")
    @Email(message = "L'email doit être valide")
    private String email;

    // Relation avec Véhicule : un client peut avoir plusieurs véhicules
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference("client-vehicule")
    private Set<Vehicule> vehicules;

    @Transient
    private Long vehiculeId;
}