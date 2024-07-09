package com.huy.mse.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String username;

    String password;

    String email;

    @Column(name = "phone_number")
    String phoneNumber;

    @CreationTimestamp
    @Column(name = "created_at")
    LocalDate createdAt;

//    String address;
//
//    String role;
//
//    String status;
//
//    String avatar;
//
//    String token;
//
//    String refreshToken;
//
//    String resetPasswordToken;
//
//    String resetPasswordExpires;
//
//    String emailVerificationToken;
//
//    String emailVerified;
//
//    String emailVerificationExpires;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    List<Order> orders;
}
