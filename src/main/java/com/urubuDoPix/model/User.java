package com.urubuDoPix.model;

import com.urubuDoPix.dtos.UserDTO;
import com.urubuDoPix.enums.UserType;
import jakarta.persistence.*;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

@Entity(name = "users")
@Table(name = "users")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String firstname;
    private String lastname;
    private BigDecimal balance;
    @Column(unique = true)
    private String document;
    @Column(unique = true)
    private String email;
    private String password;
    private UserType userType;

    public User(UserDTO data){
        this.setFirstname(data.firstname());
        this.setLastname(data.lastname());
        this.setBalance(data.balance());
        this.setDocument(data.document());
        this.setUserType(data.userType());
        this.setPassword(data.password());
        this.setEmail(data.email());
    }
}
