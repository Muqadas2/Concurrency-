import java.util.concurrent.atomic.AtomicInteger;

// BankAccount.java
class BankAccount {
    private AtomicInteger balance = new AtomicInteger(0);
    
    // Deposit method
    public void deposit(int amount) {
        balance.addAndGet(amount);
    }
    
    // Withdraw method
    public void withdraw(int amount) {
        balance.addAndGet(-amount);
    }
    
    // Get the balance
    public int getBalance() {
        return balance.get();
    }
}

// BankClient.java
class BankClient extends Thread {
    private BankAccount account;
    
    public BankClient(BankAccount account) {
        this.account = account;
    }
    
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            int amount = (int) (Math.random() * 100);
            if (Math.random() > 0.5) {
                account.deposit(amount);
                System.out.println(Thread.currentThread().getName() + " deposited: " + amount);
            } else {
                account.withdraw(amount);
                System.out.println(Thread.currentThread().getName() + " withdrew: " + amount);
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

// BankTransactionExample.java
public class BankTransactionExample {
    public static void main(String[] args) throws InterruptedException {
        BankAccount account = new BankAccount();
        
        // Create three client threads that will perform transactions
        Thread client1 = new BankClient(account);
        Thread client2 = new BankClient(account);
        Thread client3 = new BankClient(account);
        
        // Start the client threads
        client1.start();
        client2.start();
        client3.start();
        
        // Wait for all client threads to finish
        client1.join();
        client2.join();
        client3.join();
        
        // Print the final account balance after all transactions
        System.out.println("Final Account Balance: " + account.getBalance());
    }
}
