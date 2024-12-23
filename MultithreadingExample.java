// NumberPrinter.java
class NumberPrinter extends Thread {
    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println("Number: " + i);
            try {
                Thread.sleep(100); // Sleep for 100ms to simulate concurrent execution
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

// SquarePrinter.java
class SquarePrinter extends Thread {
    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println("Square: " + (i * i));
            try {
                Thread.sleep(100); // Sleep for 100ms to simulate concurrent execution
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

// MultithreadingExample.java
public class MultithreadingExample {
    public static void main(String[] args) {
        NumberPrinter numberPrinter = new NumberPrinter();
        SquarePrinter squarePrinter = new SquarePrinter();
        
        // Start both threads
        numberPrinter.start();
        squarePrinter.start();
    }
}
