package fr.unilasalle.tp_garage_auto.beans;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    private String date;

    @NotBlank(message = "Le type de service est obligatoire")
    @NotNull(message = "Le type de service ne peut pas être nul")
    @NotEmpty(message = "Le type de service ne peut pas être vide")
    private String typeService;

    // Relation avec Véhicule
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicule_id")
    @JsonBackReference("vehicule-rendezVous")
    private Vehicule vehicule;

    // Relation avec Technicien
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "technicien_id")
    @JsonBackReference("technicien-rendezVous")
    private Technicien technicien;
}