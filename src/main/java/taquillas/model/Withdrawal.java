package taquillas.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class Withdrawal {

	@Id
	@GeneratedValue
	private long id;

	private LocalDateTime at;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@NotNull
	private int quantity;

	@ManyToOne
	@JoinColumn(name = "item_id")
	private Item item;

}
