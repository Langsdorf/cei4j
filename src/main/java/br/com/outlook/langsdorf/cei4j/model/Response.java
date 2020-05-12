package br.com.outlook.langsdorf.cei4j.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Response {

	public String username;
	public List<Stock> stocks;

}
