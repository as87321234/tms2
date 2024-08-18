package ca.mgisinc.tms2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Entity
@Table(name = "privilege")
@Getter
@Setter
@NoArgsConstructor

public class Privilege extends BaseEntity {
	
	private String name;
	@ManyToMany(mappedBy = "privileges")
	private Collection<Role> roles;
	
	public Privilege(String name) {
		this.name = name;
	}
	
}