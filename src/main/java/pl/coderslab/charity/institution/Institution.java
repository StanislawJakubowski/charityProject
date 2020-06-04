package pl.coderslab.charity.institution;

import pl.coderslab.charity.donation.Donation;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "institutions")
public class Institution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Proszę wprowadzić nazwę instytucji")
    private String name;

    @NotBlank(message = "Proszę wprowadzić opis instytucji")
    private String description;

    //TODO caheck if cascade should be remove
    @OneToMany(mappedBy = "institution", cascade = CascadeType.REMOVE)
    private List<Donation> donations = new ArrayList<>();

    public Institution() {
    }

    public Institution(@NotBlank String name, @NotBlank String description) {
        this.name = name;
        this.description = description;
    }

    public List<Donation> getDonations() {
        return donations;
    }

    public void setDonations(List<Donation> donations) {
        this.donations = donations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
