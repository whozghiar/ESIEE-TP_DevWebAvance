package fr.unilasalle.tp_garage_auto.beans;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@ToString (exclude = {"client", "rendezVous"})
@Data
@Builder
@Valid
@EqualsAndHashCode(exclude = {"client", "rendezVous"})
public class Vehicule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "La marque est obligatoire")
    @NotNull(message = "La marque ne peut pas être nulle")
    @NotEmpty(message = "La marque ne peut pas être vide")
    private String marque;

    @NotBlank(message = "Le modèle est obligatoire")
    @NotNull(message = "Le modèle ne peut pas être nul")
    @NotEmpty(message = "Le modèle ne peut pas être vide")
    private String modele;

    @NotBlank(message = "L'immatriculation est obligatoire")
    @NotNull(message = "L'immatriculation ne peut pas être nulle")
    @NotEmpty(message = "L'immatriculation ne peut pas être vide")
    @Pattern(regexp = "^[A-Z]{2}-[0-9]{3}-[A-Z]{2}$", message = "L'immatriculation doit être au format XX-000-XX")
    private String immatriculation;

    @NotNull(message = "L'année ne peut pas être nulle")
    private int annee;

    // Relation avec RendezVous : un véhicule peut avoir plusieurs rendez-vous
    @OneToMany(mappedBy = "vehicule", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference("vehicule-rendezVous")
    private Set<RendezVous> rendezVous;

    // Relation avec Client
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    @JsonBackReference("client-vehicule")
    private Client client;


}
