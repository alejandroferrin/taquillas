package taquillas.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LockerDto {
	private long id;
	private int number;
	private List<String> roles;
	private List<String> items;
}
