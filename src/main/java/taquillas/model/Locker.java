package taquillas.model;

import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Locker {

	@Id
	@GeneratedValue
	private long id;

	@NotNull
	@Column(unique = true)
	@Min(1)
	@Max(20)
	private int number;

	@NotNull
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "locker_role", joinColumns = @JoinColumn(name = "locker_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> rolesAutorizados;

	@JsonIgnore
	@OneToMany(mappedBy = "locker", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Item> items;

}
