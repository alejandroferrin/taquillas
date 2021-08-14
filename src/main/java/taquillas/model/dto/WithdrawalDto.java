package taquillas.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class WithdrawalDto {
	private long id;
	private String userNumber;
	private long itemId;
	private int quantity;
	private String at;
}
