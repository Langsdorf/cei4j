package br.com.outlook.langsdorf.cei4j.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Stock {

	private String name;
	private String code;

	private int quantity = 0;

	private double lastPrice;
	private double currentValue;
	private double appliedValue = 0.0;
	private double averagePrice = 0.0;

	private double profit;
	private double profitValue;

	public void setProfit() {
		this.profit = ((getCurrentValue() / getAppliedValue()) - 1) * 100;
		setProfitValue();
	}

	public void setProfitValue() {
		this.profitValue = (getProfit() / 100) * getAppliedValue();
	}

}
