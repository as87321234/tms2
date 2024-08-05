package ca.mgisinc.tms2.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Entity
@Table(name="role")
@Getter
@Setter
@NoArgsConstructor
public class Role extends BaseEntity {

    private String name;
    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    public Role(String name) {
        this.name = name;
    }

    @ManyToMany
    @JoinTable(
            name = "roles_privileges",
            joinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "privilege_id", referencedColumnName = "id"))
    private Collection<Privilege> privileges;

}