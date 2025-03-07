package com.hangangFlow.hangangFlow.domain.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hangangFlow.hangangFlow.domain.user.UserRole;
//import jakarta.persistence.*;
import lombok.*;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.*;
import java.util.UUID;


@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // or GenerationType.UUID
    @Column(name = "user_uuid", columnDefinition = "BINARY(16)")
    private UUID userUuid;

    @Column(name = "user_id", unique = true)
    private String userId;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "nickname", unique = true)
    private String nickname;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

//    @JsonProperty("userUuid")
//    public UUID getUserUuid() {
//        return userUuid;
//    }

    @JsonProperty("userId")
    public String getUserId() {
        return userId;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("nickname")
    public String getNickname() {
        return nickname;
    }

    @JsonProperty("password")
    public String getPassword() {
        return password;
    }

}