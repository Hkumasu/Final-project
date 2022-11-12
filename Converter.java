package final_group_project;

public interface Converter {
	public void converter(double money, double rate); //convert the currency to another base on the currency rate. This method will use in transit method
	public void transit(double money, int bank1, int bank2); //use switch state or if statement 
}
