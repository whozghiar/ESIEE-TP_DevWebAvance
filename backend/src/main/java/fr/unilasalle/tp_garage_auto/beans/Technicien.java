package fr.unilasalle.tp_garage_auto.beans;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @NotNull(message = "Le nom ne peut pas être nul")
    @NotEmpty(message = "Le nom ne peut pas être vide")
    @Size(min = 2, max = 50, message = "Le nom doit être compris entre 2 et 50 caractères")
    private String nom;

    @NotBlank(message = "Le prénom est obligatoire")
    @NotNull(message = "Le prénom ne peut pas être nul")
    @NotEmpty(message = "Le prénom ne peut pas être vide")
    @Size(min = 2, max = 50, message = "Le prénom doit être compris entre 2 et 50 caractères")
    private String prenom;


    @OneToMany(mappedBy = "technicien", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("technicien-rendezVous")
    private Set<RendezVous> rendezVous;



}
