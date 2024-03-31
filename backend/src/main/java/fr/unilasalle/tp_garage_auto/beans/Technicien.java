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
@ToString (exclude = "rendezVous")
@Data
@Builder
@Valid
@EqualsAndHashCode(exclude = "rendezVous")
public class Technicien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    @NotBlank(message = "Le prénom est obligatoire")
    private String prenom;


    // Relation avec RendezVous : un technicien peut avoir plusieurs rendez-vous
    @OneToMany(mappedBy = "technicien", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("technicien-rendezVous")
    private Set<RendezVous> rendezVous;



}
