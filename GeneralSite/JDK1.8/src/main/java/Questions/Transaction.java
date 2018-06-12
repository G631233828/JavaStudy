package Questions;

public class Transaction {

	private Trader trader;
	private Integer year;
	private Double money;
	public Trader getTrader() {
		return trader;
	}
	public void setTrader(Trader trader) {
		this.trader = trader;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	@Override
	public String toString() {
		return "Transaction [trader=" + trader + ", year=" + year + ", money=" + money + "]";
	}
	public Transaction(Trader trader, Integer year, Double money) {
		super();
		this.trader = trader;
		this.year = year;
		this.money = money;
	}
	
	
}
