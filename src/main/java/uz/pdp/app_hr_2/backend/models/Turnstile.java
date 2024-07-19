package uz.pdp.app_hr_2.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import uz.pdp.app_hr_2.backend.models.enums.TurnstileAction;

import java.security.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Turnstile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private User employee;

    @Enumerated(EnumType.STRING)
    private TurnstileAction action;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Timestamp timestamp;
}
