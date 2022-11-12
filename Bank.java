package final_group_project;

public abstract class Bank {
	
	private double total;
	private double USDollar;
	private double austoralianDollar;
	private double canadianDollar;
	private double yen;
	private double euro;
	private double peso;
	private double poundSterling;
	private double dong;
	
	public Bank(double total, double USDollar, double austoralianDollar, double canadianDollar, double yen, double euro, double peso, double poundSterling, double dong)
	{
		this.total = total;
		this.USDollar = USDollar;
		this.austoralianDollar = austoralianDollar;
		this.canadianDollar = canadianDollar;
		this.yen = yen;
		this.euro = euro;
		this.peso = peso;
		this.poundSterling = poundSterling;
		this.dong = dong;
	}
	
	public final double getTotal()
	{
		return total;
	}
	
	public final double getUSDollar()
	{
		return USDollar;
	}
	
	public final double getAustoralianDollar()
	{
		return austoralianDollar;
	}
	
	public final double getCanadianDollar()
	{
		return canadianDollar;
	}
	
	public final double getYen()
	{
		return yen;
	}
	
	public final double getEuro()
	{
		return euro;
	}
	
	public final double getPeso()
	{
		return peso;
	}
	
	public final double getPoundSterling()
	{
		return poundSterling;
	}
	
	public final double getDong()
	{
		return dong;
	}
	
	public abstract void deposit(double money);
	public abstract void withdrow(double money);
	public abstract void record(); //use data structure here to record the action
	public abstract void description(); //use reading file here to get the bank description like currency rate and saving
}
