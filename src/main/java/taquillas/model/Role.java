package taquillas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Role {
	@Id
	@GeneratedValue
	private long id;

	@NotNull
  @Column(unique = true)
	//@Enumerated(EnumType.STRING)
	//private RoleName roleName;
	private String roleName;
}
