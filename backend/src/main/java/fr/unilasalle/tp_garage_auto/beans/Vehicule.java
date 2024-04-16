package fr.unilasalle.tp_garage_auto.beans;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString (exclude = {"client"})
@Data
@Builder
@Valid
@EqualsAndHashCode(exclude = {"client"})
public class Vehicule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "La marque est obligatoire")
    @NotNull(message = "La marque ne peut pas être nulle")
    @NotEmpty(message = "La marque ne peut pas être vide")
    @Schema(description = "Marque du véhicule", example = "Renault")
    private String marque;

    @NotBlank(message = "Le modèle est obligatoire")
    @NotNull(message = "Le modèle ne peut pas être nul")
    @NotEmpty(message = "Le modèle ne peut pas être vide")
    @Schema(description = "Modèle du véhicule", example = "Clio")
    private String modele;

    @NotBlank(message = "L'immatriculation est obligatoire")
    @NotNull(message = "L'immatriculation ne peut pas être nulle")
    @NotEmpty(message = "L'immatriculation ne peut pas être vide")
    @Pattern(regexp = "^[A-Z]{2}-[0-9]{3}-[A-Z]{2}$", message = "L'immatriculation doit être au format XX-000-XX")
    @Schema(description = "Immatriculation du véhicule", example = "AB-123-CD")
    private String immatriculation;

    @NotNull(message = "L'année ne peut pas être nulle")
    @Min(value = 1920, message = "L'année doit être supérieure ou égale à 1920")
    @Max(value = 2025, message = "L'année doit être inférieure ou égale à 2025")
    @Schema(description = "Année du véhicule", example = "2021")
    private Integer annee;


    /*
    // Relation avec RendezVous : un véhicule peut avoir plusieurs rendez-vous
    @OneToMany(mappedBy = "vehicule", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference("vehicule-rendezVous")
    private Set<RendezVous> rendezVous;
     */

    // Relation avec Client
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    //@JsonBackReference("client-vehicule")
    private Client client;


}
