package com.hangangFlow.hangangFlow.domain.community;


//import jakarta.persistence.*;
import com.hangangFlow.hangangFlow.domain.user.User;
import com.hangangFlow.hangangFlow.domain.park.Parks;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="community")
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "community_uuid",columnDefinition = "BINARY(16)")
    private UUID communityUuid;

    @Column(name = "article",columnDefinition = "LONGTEXT")
    private String article;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "user_uuid")
    private User user;

    @OneToOne
    @JoinColumn(name = "park_uuid")
    private Parks parks;

    @Column(name = "like_count",columnDefinition = "BIGINT")
    private int likeCount;

    @Column(name = "soft_delete")
    private LocalDateTime softDelete;

    @Builder
    public Community(String article, User user, Parks parks) {
        this.article = article;
        this.user = user;
        this.parks = parks;
    }

    public void update(String article) {
        this.article = article;
    }

    public String getUserNickname() {
        return this.user.getNickname();
    }

}
