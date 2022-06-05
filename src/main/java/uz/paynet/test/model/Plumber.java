package uz.paynet.test.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "plumber")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Plumber {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private long Id;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "active")
    private boolean active;
    @OneToMany(mappedBy = "plumber",fetch = FetchType.EAGER)
    private List<Building> buildings;
}
