package final_group_project;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class JapaneseBank extends Bank implements Converter, ActionListener{

	JTextField textField[] = new JTextField[8];
	static LinkedList<String> record = new LinkedList<String>();
	private static final DecimalFormat df = new DecimalFormat("0.00");
	
	public JapaneseBank(double total, double USDollar, double australianDollar, double canadianDollar, double yen, double euro, double peso, double poundSterling, double dong, double interestRate)
	{
		super(total, USDollar, australianDollar, canadianDollar, yen, euro, peso, poundSterling, dong, interestRate);
	}
	
	public void deposit(double money)
	{
		double total = getTotal() + money;
		setTotal(total);
		
		record.add("Action: Deposit " + money + "yen" );
	}
	
	public void withdraw(double money)
	{
		if(money > getTotal())
		{
			System.out.println("Your saving is less than " + money + "." + " This action failed");
		}
		else
		{
			double total = getTotal() - money;
			setTotal(total);
			
			record.add("Action: Withraw " + money + "yen");
		}
	}
	
	public void calculateInterest(int year)
	{				
		System.out.println("Interest rate is " + getInterestRate());
		double result = calculateInterest(year, getTotal(), getInterestRate());
		System.out.println("The amount of saving will be " + df.format(result) + " yen in " + year + " years");
	}
		
	private double calculateInterest(int year, double money, double rate)
	{
		double amount, balance = 0;
		
		if (year > 0)
		{
			amount = calculateInterest(year - 1, money, rate);
			balance = amount * rate;
			amount = amount + balance;
			money = amount;
			df.setRoundingMode(RoundingMode.DOWN);
								
			System.out.println("Compound interest year " + year + ": " + df.format(balance) + " yen");
			System.out.println("Amount of Saving: " + df.format(amount) + " yen");
		}
		return money;
	}
	
	public void record()
	{
		if (record.isEmpty())
		{
			System.out.println("Any action haven't happened yet.");

		}
		else 
		{
			
			for(String action : record)
			{
				System.out.println(action);
			}
			
			printRecord();
		}
	}
	
	public static void printRecord()
	{
		try 
		{
			PrintWriter outPutFile = new PrintWriter("bank_record.txt");
			
			for (String action : record)
			{
				outPutFile.println(action);
			}
			
			outPutFile.close();
			
			System.out.println("Your bank information successfully print out in the file");
		}
		catch(FileNotFoundException e)
		{
			System.out.println(e);
		}
	}
	
	public void description()
	{	
		
		JFrame frame = new JFrame();
		frame.setTitle("Description of Bank Account");
		
		
		JLabel savingLabel = new JLabel("Your Saving: " + getTotal());
		JLabel rateLabel = new JLabel("Currency rate for 1 yen: ");
		JLabel label2 = new JLabel("US Dollar: " + getUSDollar());
		JLabel label3 = new JLabel("Australia Dollar: " + getAustralianDollar());
		JLabel label4 = new JLabel("Candian Dollar: " + getCanadianDollar());
		JLabel label5 = new JLabel("Euro: " + getEuro());
		JLabel label6 = new JLabel("Mexican Peso: " + getPeso());
		JLabel label7 = new JLabel("Pound Sterlimg: " + getPoundSterling());
		JLabel label8 = new JLabel("Dong: " + getDong());
		JLabel interestLabel = new JLabel("Interest Rate: " + getInterestRate());
		
		savingLabel.setBounds(50, 50, 150, 50);
		rateLabel.setBounds(50, 100, 150, 50);
		label2.setBounds(50, 150, 150, 50);
		label3.setBounds(50, 200, 150, 50);
		label4.setBounds(50, 250, 150, 50);
		label5.setBounds(50, 300, 150, 50);
		label6.setBounds(50, 350, 150, 50);
		label7.setBounds(50, 400, 150, 50);
		label8.setBounds(50, 450, 150, 50);
		interestLabel.setBounds(50, 500, 150, 50);
		
		frame.add(savingLabel);
		frame.add(rateLabel);
		frame.add(interestLabel);
		frame.add(label2);
		frame.add(label3);
		frame.add(label4);
		frame.add(label5);
		frame.add(label6);
		frame.add(label7);
		frame.add(label8);
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	frame.setSize(300, 650);
		frame.setLayout(null);
		frame.setVisible(true);

	}
		
	public double converter(double money, double rate)
	{
		double currency;
		currency = money * rate;
		return currency;
	}
	
	public void transitToOtherBank(double money, int bank, Bank bank2)
	{
		double balance = 0;
		double total = getTotal();
		
		if (money > getTotal())
		{
			System.out.println("The balance is less than " + money + "." + " This action failed");
		}
		else 
		{
			total = total - money;
			setTotal(total);
		
			switch(bank)
			{
			case 1: balance = converter(money, getUSDollar());
			bank2.transitFromOtherBank(money, balance, 4);
				record.add("Action: Transit " + money + " yen to American bank. It is " + balance + " US dollar");
				System.out.println("It is " + balance + " US dollar");
				break;
			case 2: balance = converter(money, getAustralianDollar());
			bank2.transitFromOtherBank(money, balance, 4);
				record.add("Action: Transit " + money + " yen to Austoralian bank. It is " + balance + " Australian dollar");
				System.out.println("It is " + balance + " Australian dollar");
				break;
			case 3: balance = converter(money, getCanadianDollar());
			bank2.transitFromOtherBank(money, balance, 4);
				record.add("Action: Transit " + money + " yen to Canadian bank. It is " + balance + "Canadian dollar");
				System.out.println("It is " + balance + " Canadian dollar");
				break;
			case 5: balance = converter(money, getEuro());
			bank2.transitFromOtherBank(money, balance, 4);
				record.add("Action: Transit " + money + " yen to Euro bank. It is " + balance + " euro");
				System.out.println("It is " + balance + " euro");
				break;
			case 6: balance = converter(money, getPeso());
			bank2.transitFromOtherBank(money, balance, 4);
				record.add("Action: Transit " + money + " yen to Mexican bank. It is " + balance + " Mexican peso");
				System.out.println("It is " + balance + " Mexcian peso");
				break;
			case 7: balance = converter(money, getPoundSterling());
			bank2.transitFromOtherBank(money, balance, 4);
				record.add("Action: Transit " + money + " yen to British bank. It is " + balance + " pound sterling");
				System.out.println("It is " + balance + " pound sterling");
				break;
			case 8: balance = converter(money, getDong());
			bank2.transitFromOtherBank(money, balance, 4);
				record.add("Action: Transit " + money + " yen to Vietaminese bank. It is " + balance + " dong");
				System.out.println("It is " + balance + " dong");
				break;
			}
		}
	}
	
	public void transitFromOtherBank(double money, double balance, int bank)
	{
		double total = getTotal() + balance;
		setTotal(total);
	
		switch(bank)
		{
		case 1: 
			record.add("Action: Transit " + balance + " yen from American bank. It is "	+ money + " US dollar"); 
			break;
		case 2: 
			record.add("Action: Transit " + balance + " dollar from Australian bank. It is " + money + " Australian dollar");
			break;
		case 3:
			record.add("Action: Transit " + balance + " dollar from Canadian bank. It is " + money + " Canadian dollar");
			break;
		case 5: 
			record.add("Action: Transit " + balance + " dollar from Euro bank. It is " + money + " euro");
			break;
		case 6: 
			record.add("Action: Transit " + balance + " dollar from Mexican bank. It is " + money + " Mexican peso");
			break;
		case 7:
			record.add("Action: Transit " + balance + " dollar from British bank. It is " + money + " pound sterling");
			break;
		case 8: 
			record.add("Action: Transit " + balance + " dollar from Vietnamese bank. It is " + money + " dong");
			break;
		}
		System.out.println("Transit successfully!");
	}
	
	public void showTotal()
	{
		System.out.println("Saving: " + getTotal());
	}
	
	public int compareTo(Bank bank2)
	{
		if(getTotal() != bank2.getTotal())
		{
			return (int) (getTotal() - bank2.getTotal());
		}
		else
		{
			return 0;
		}
	}
	
	public void GUI()
	{
		JFrame frame;
		frame = new JFrame("Converter from Yen to");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(500, 600);
		frame.setLayout(null);
		
		JLabel label1 = new JLabel("Yen: ");
		JLabel label2 = new JLabel("US Dollar: ");
		JLabel label3 = new JLabel("Australia Dollar: ");
		JLabel label4 = new JLabel("Candian Dollar: ");
		JLabel label5 = new JLabel("Euro: ");
		JLabel label6 = new JLabel("Mexican Peso: ");
		JLabel label7 = new JLabel("Pound Sterlimg: ");
		JLabel label8 = new JLabel("Dong: ");
		
		label1.setBounds(100, 50, 100, 25);
		label2.setBounds(100, 100, 100, 25);
		label3.setBounds(100, 150, 100, 25);
		label4.setBounds(100, 200, 100, 25);
		label5.setBounds(100, 250, 100, 25);
		label6.setBounds(100, 300, 100, 25);
		label7.setBounds(100, 350, 100, 25);
		label8.setBounds(100, 400, 100, 25);
		
		frame.add(label1);
		frame.add(label2);
		frame.add(label3);
		frame.add(label4);
		frame.add(label5);
		frame.add(label6);
		frame.add(label7);
		frame.add(label8);
		
		JButton button = new JButton("Convert");
		button.setBounds(175, 450, 100, 25);
		button.setFocusable(false);
		frame.add(button);
		button.addActionListener(this);
		
		JTextField textfield1 = new JTextField();
		JTextField textfield2 = new JTextField();
		JTextField textfield3 = new JTextField();
		JTextField textfield4 = new JTextField();
		JTextField textfield5 = new JTextField();
		JTextField textfield6 = new JTextField();
		JTextField textfield7 = new JTextField();
		JTextField textfield8 = new JTextField();
		
		textField[0] = textfield1;
		textField[1] = textfield2;
		textField[2] = textfield3;
		textField[3] = textfield4;
		textField[4] = textfield5;
		textField[5] = textfield6;
		textField[6] = textfield7;
		textField[7] = textfield8;
		
		textField[0].setBounds(220, 50, 150, 25);
		textField[0].setEditable(false);
		textField[1].setBounds(220, 100, 150, 25);
		textField[1].setEditable(false);
		textField[2].setBounds(220, 150, 150, 25);
		textField[2].setEditable(false);
		textField[3].setBounds(220, 200, 150, 25);
		textField[3].setEditable(false);
		textField[4].setBounds(220, 250, 150, 25);
		textField[4].setEditable(false);
		textField[5].setBounds(220, 300, 150, 25);
		textField[5].setEditable(false);
		textField[6].setBounds(220, 350, 150, 25);
		textField[6].setEditable(false);
		textField[7].setBounds(220, 400, 150, 25);
		textField[7].setEditable(false);
		
		frame.add(textField[0]);
		frame.add(textField[1]);
		frame.add(textField[2]);
		frame.add(textField[3]);
		frame.add(textField[4]);
		frame.add(textField[5]);
		frame.add(textField[6]);
		frame.add(textField[7]);
		
		frame.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String str;
		double balance;
		double money;	
			Random random = new Random();
			money = random.nextInt(100000);
			for(int i = 0; i <= 8 ; i++)
			{
				switch(i)
				{
				case 0: str = Double.toString(money);
				textField[0].setText(str);
				break;
				case 1: balance = converter(money, getUSDollar());
				str = Double.toString(balance);
				textField[1].setText(str);
				break;
				case 2: balance = converter(money, getAustralianDollar());	
				str = Double.toString(balance);
				textField[2].setText(str);
				break;
				case 3: balance = converter(money, getCanadianDollar());
				str = Double.toString(balance);
				textField[3].setText(str);
				break;
				case 4: balance = converter(money, getEuro());
				str = Double.toString(balance);
				textField[4].setText(str);
				break;
				case 5: balance = converter(money, getPeso());
				str = Double.toString(balance);
				textField[5].setText(str);
				break;
				case 6: balance = converter(money, getPoundSterling());
				str = Double.toString(balance);
				textField[6].setText(str);
				break;
				case 7: balance = converter(money, getDong());
				str = Double.toString(balance);
				textField[7].setText(str);
				break;
				}
			}	
		}

	public static void main(String[] argus)
	{
		int n = 8;
		Bank bank[] = new Bank[n];
		
		try {
			File inputFile = new File("bank_information.txt");
			Scanner scan = new Scanner(inputFile);
			
			
			while(scan.hasNextLine())
			{
				String line = scan.nextLine();
				Scanner readLine = new Scanner(line);
	
				
				while(readLine.hasNext())
				{
					readLine.useDelimiter(":");
					String word = readLine.next();
					String total = readLine.next();
					double money = Double.parseDouble(total);
					
					switch(word)
					{
					case "US": bank[0] = new USBank(money, 1, 1.48, 0.75, 138.51, 0.96, 19.26, 0.83, 24520, 0.02);
					break;
//					case "Australian": bank[1] = new AustralianBank(money, 0.68, 1, 0.91, 93.06, 0.65, 13.11, 0.56, 1670143, 0.005);
//					break;
//					case "Canadian": bank[2] = new CanadianBank(money, 0.75, 1.09, 1, 101.86, 0.71, 14.35, 0.62, 28276.10, 0.01);
//					break;
					case "Yen": bank[3] = new JapaneseBank(money, 0.0072, 0.011, 0.0096, 1, 0.0096, 0.14, 0.006, 179.27, 0.05);
					break;
//					case "Euro": bank[4] = new EuroBank(money, 1.04, 1.53 , 1.40, 142.65, 1 , 20.11, 0.86, 25598.76, 0.04);
//					break;
//					case "Peso": bank[5] = new MexicanBank(money, 0.052, 0.076, 0.07, 7.1, 0.05, 1, 0.043, 1273.14, 0.03);
//					break;
//					case "Pound": bank[6] = new BritishBank(money, 1.21, 1.78 , 1.62, 165.31, 1.16, 23.30, 1, 29657.58, 0.009);
//					break;
//					case "Dong": bank[7] = new VetaminsBank(money, 0.000041, 0.00006, 0.000055, 0.0056, 0.000039, 0.0023, 0.00079, 1, 0.02;
//					break;
					}
				}
				readLine.close();
			}
			scan.close();
		}		
		catch(FileNotFoundException e)
		{
			System.out.println(e);
		}
		
		boolean exit = false;
		boolean exit1 = false;
		String choice;
		int bankChoice;
		int year;
		double amount;
		Scanner reader = new Scanner(System.in);
		Scanner amountReader = new Scanner(System.in);	
		
		System.out.println("Welcome to Hazuki's Japanese Bank.");

		while(!exit)
		{
			
			System.out.println("Choose the option:");
			System.out.println("1: Deposit   2: Withdraw      3: Transit to Another Bank    4: Display Description     5: Convert Randamlly");
			System.out.println("6: Interest  7: Show Saving   8: Display & Print Record     9: Exit");
			choice = reader.next();
			
			switch(choice)
			{
			case "1": System.out.println("Enter the amount to deposit: ");
			amount = amountReader.nextDouble();
			bank[3].deposit(amount);
			break;
			case "2": System.out.println("Enter the amount to withdraw: ");
			amount = amountReader.nextDouble();
			bank[3].withdraw(amount);
			break;
			case "3": while(!exit1)
			{
				System.out.println("Choose the bank you would like to transit to:"); 
				System.out.println("1: US dollar  2: Australian Dollar  3: Canadian Dollar  5: Euro  6: Mexican Peso  7: Pound Sterling  8: Dong  9:Exit");
				choice = reader.next();
				
				switch(choice)
				{
				case "1":System.out.println("Enter the amount to transit: ");
				amount = reader.nextDouble();
				bankChoice = Integer.parseInt(choice);
				bank[3].transitToOtherBank(amount, bankChoice, bank[0]);
				exit1 = true;
				break;
				case "2":System.out.println("Enter the amount to transit: ");
				amount = reader.nextDouble();
				bankChoice = Integer.parseInt(choice);
				bank[3].transitToOtherBank(amount, bankChoice, bank[1]);
				exit1 = true;
				break;
				case "3":System.out.println("Enter the amount to transit: ");
				amount = reader.nextDouble();
				bankChoice = Integer.parseInt(choice);
				bank[3].transitToOtherBank(amount, bankChoice, bank[2]);
				exit1 = true;
				break;
				case "5":System.out.println("Enter the amount to transit: ");
				amount = reader.nextDouble();
				bankChoice = Integer.parseInt(choice);
				bank[3].transitToOtherBank(amount, bankChoice, bank[4]);
				exit1 = true;
				break;
				case "6":System.out.println("Enter the amount to transit: ");
				amount = reader.nextDouble();		
				bankChoice = Integer.parseInt(choice);
				bank[3].transitToOtherBank(amount, bankChoice, bank[5]);	
				exit1 = true;
				break;
				case "7":System.out.println("Enter the amount to transit: ");
				amount = reader.nextDouble();	
				bankChoice = Integer.parseInt(choice);
				bank[3].transitToOtherBank(amount, bankChoice, bank[6]);
				exit1 = true;
				break;
				case "8":System.out.println("Enter the amount to transit: ");
				amount = reader.nextDouble();
				bankChoice = Integer.parseInt(choice);
				bank[3].transitToOtherBank(amount, bankChoice, bank[7]);
				exit1 = true;
				break;
				case "9":exit1 = true;
				break;
				default:System.out.println("Error!! Your choice is not valid. Try enter correct choice option.");
				break;
				}
			}	
			exit1 = false;
			break;
			case "4": bank[3].description();
			break;
			case "5": bank[3].GUI();
			break;
			case "6": System.out.println("Enter the period in years: ");
			year = reader.nextInt();
			bank[3].calculateInterest(year);
			break;
			case "7": bank[3].showTotal();
			break;
			case "8": bank[3].record();
			break;
			case "9": System.out.println("Thank you!");
			exit = true;
			break;
			default: System.out.println("Error!! Your choice is not valid. Try enter correct choice option.");
			break;
			}
		}		
		reader.close();	
		amountReader.close();
		
		try 
		{
			File outFile = new File("bank_information.txt");
			PrintWriter outPutFile = new PrintWriter(outFile);
			
			outPutFile.println("US: " + bank[0].getTotal());
//			outPutFile.println("Canadian: " + bank[1].getTotal());
//			outPutFile.println("Australian: " + bank[2].getTotal());
			outPutFile.println("Yen: " + bank[3].getTotal());
//			outPutFile.println("Euro: " + bank[4].getTotal());
//			outPutFile.println("Peso: " + bank[5].getTotal());
//			outPutFile.println("Pound: " + bank[6].getTotal());
//			outPutFile.println("Dong: " + bank[7].getTotal());
			
			outPutFile.close();
		}	
		catch(FileNotFoundException e)
		{
			System.out.println(e);
		}
		
		System.exit(0);
	}

}
