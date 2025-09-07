package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "notes")
public class Note {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // getters y setters
}
