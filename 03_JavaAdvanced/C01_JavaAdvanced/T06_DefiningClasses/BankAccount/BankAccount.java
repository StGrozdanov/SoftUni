package T06_DefiningClasses.Lab.BankAccount;

public class BankAccount {
    private static int counter = 1;
    private int id;
    private double balance;
    private static double interestRate = 0.02;

    public BankAccount(){
        this.id = counter++;
    }

    public int getId() {
        return id;
    }

    public static void setInterestRate(double interestRate) {
        BankAccount.interestRate = interestRate;
    }

    public double getInterestRate(int years) {
        return BankAccount.interestRate * years * this.balance;
    }

    public void deposit (double amount){
        this.balance += amount;
    }
}
