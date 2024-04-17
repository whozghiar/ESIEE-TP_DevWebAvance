package fr.unilasalle.tp_garage_auto.beans;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"vehicule", "technicien"})
@Data
@Builder
@Valid
@EqualsAndHashCode(exclude = {"vehicule", "technicien"})
public class RendezVous {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "La date est obligatoire")
    @NotNull(message = "La date ne peut pas être nulle")
    @NotEmpty(message = "La date ne peut pas être vide")
    @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/[0-9]{4}$", message = "La date doit être au format jj/mm/aaaa")
    @Size(min = 10, max = 10, message = "La date doit être au format jj/mm/aaaa")
    @Schema(description = "Date du rendez-vous au forma dd/mm/aaaa", example = "01/01/2021")
    private String date;

    @NotBlank(message = "Le type de service est obligatoire")
    @NotNull(message = "Le type de service ne peut pas être nul")
    @NotEmpty(message = "Le type de service ne peut pas être vide")
    @Schema(description = "Type de service", example = "Révision")
    private String typeService;

    // Relation avec Véhicule
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "vehicule_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    //@JsonBackReference("vehicule-rendezVous")
    private Vehicule vehicule;

    // Relation avec Technicien
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "technicien_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    //@JsonBackReference("technicien-rendezVous")
    private Technicien technicien;
}