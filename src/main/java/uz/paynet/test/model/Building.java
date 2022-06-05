package uz.paynet.test.model;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "building")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Building {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private long Id;
    @Column(name = "address")
    private String address;
    @Column(name = "active")
    private boolean active;
    @JoinColumn (name="building_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Plumber plumber;

}
