package db.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@ToString(exclude = "department")
public class Lector extends GenericEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "fullName")
    private String fullName;

    @Column(name = "salary")
    private int salary;

   @Enumerated(value = EnumType.STRING)
    @Column(name = "post")
    private Post post;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Department department;

    public Lector(String fullName, int salary, Post post, Department department) {
        this.fullName = fullName;
        this.salary = salary;
        this.post = post;
        this.department = department;
    }
}
