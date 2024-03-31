package fr.unilasalle.tp_garage_auto.beans;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
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
    private String date;

    @NotBlank(message = "Le type de service est obligatoire")
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