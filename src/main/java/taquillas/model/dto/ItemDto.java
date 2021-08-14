package taquillas.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ItemDto {
	private long id;
	private String codigo;
	private String denominacion;
	private int existencias;
	private int taquillaNumber;
}
