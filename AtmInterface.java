import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;
import java.util.ArrayList;
class Account{
	static int transactionNo = 0;
	private ArrayList<Transaction> al = new ArrayList<Transaction>();
	private long balance = 0;
	int pin = 1234;
	class Transaction{
    		LocalDate currentDate;
    		LocalTime currentTime;
		int id;
		long amount;
		String type;
		Transaction(String type,long amount){
			transactionNo++;
			currentDate = LocalDate.now();
			currentTime = LocalTime.now();
			this.type = type;
			this.amount = amount;
			id = transactionNo;
		}
		public String toString(){
			return "{{Transaction id : "+id+"},{Transaction type : "+type+"},{Amount : "+amount+"},{Date : "+currentDate+"},{Time : "+currentTime+"}}";
		}
	}
	boolean withdraw(long amount){
		if(balance<amount){
			return false;
		}
                Transaction transaction = new Transaction("withdraw",amount);
                balance = balance-amount;
                recordStatement(transaction);
		return true;
        }
	boolean deposit(long amount){
		Transaction transaction = new Transaction("deposit",amount);
		balance = balance+amount;
                recordStatement(transaction);
		return true;
	}
	public long getBalance(){
		return balance;
	}
	public void setPin(int pin){
		this.pin = pin;
	}
	private void recordStatement(Transaction transaction){
		al.add(transaction);
	}
	public void getStatement(){
		System.out.println(al);
	}
}

class AtmInterface{
	static Account acc = new Account();
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int choice = 7;		//Thala for no reason
		do{
			System.out.print("\nPress i to insert your card : ");
                	char insert = sc.next().charAt(0);
                	if(insert=='i'){
                        	System.out.println("Card inserted successfully..");
                        	if(choice==0){
					System.out.print("Enter your pin(default pin -> 1234) : ");
                        	}else{
					System.out.print("Enter your pin : ");
				}
				int userPin = sc.nextInt();
                        	if(userPin!=acc.pin){
                                	System.out.println("Incorrect PIN, please try again!");
					continue;
                        	}else{
                                	System.out.println("Success..");
                        	}
                	}else{
				System.out.println("Card not inserted properly, please try again!");
				continue;
			}
			System.out.println("\n*********** ATM_SERVICES *************");
			System.out.println("1 : Check Account Balance");
			System.out.println("2 : Cash Withdrawal");
			System.out.println("3 : Cash Deposit ");
			System.out.println("4 : Change PIN");
			System.out.println("5 : Transaction History(Bank statement)");
			System.out.println("0 : Exit\n");
			System.out.print("Enter your choice : ");
			choice = sc.nextInt();
			switch(choice){
				case 0 : {break;}
				case 1 : {
						System.out.println("Current balance : "+acc.getBalance());
						System.out.println("Operation Successful..");
						break;
					 }
				case 2 : {
						System.out.print("Enter amount : ");
						long amount = sc.nextLong();
						if(acc.withdraw(amount)){
							System.out.println("Transaction Successful..");
						}else{
							System.out.println("Transaction Failed!");
						}
						break;		
					 }
				case 3 : {
                                                System.out.print("Enter amount : ");
                                                long amount = sc.nextLong();
                                                if(acc.deposit(amount)){
                                                        System.out.println("Transaction Successful..");
                                                }else{
                                                        System.out.println("Transaction Failed!");
                                                }
                                                break;
                                         }
	
				case 4 : {
						System.out.print("Enter new PIN : ");
                                                int pin = sc.nextInt();
						acc.setPin(pin);
						System.out.println("Operation Successful..");
						break;
					 }
				case 5 : {
						acc.getStatement();
						break;
					 }
				default : {
						System.out.println("Invalid input, try again!");
					  } 

			
			}

		}while(choice!=0);
	}
}
