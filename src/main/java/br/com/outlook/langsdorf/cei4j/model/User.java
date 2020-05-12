package br.com.outlook.langsdorf.cei4j.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

	private String username;
	private String password;
	private String fullname;
	private List<Stock> stocks;

}
