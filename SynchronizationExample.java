// Counter.java
class Counter {
    private int count = 0;
    
    public synchronized void increment() {
        count++;
    }
    
    public int getCount() {
        return count;
    }
}

// CounterIncrementer.java
class CounterIncrementer extends Thread {
    private Counter counter;
    
    public CounterIncrementer(Counter counter) {
        this.counter = counter;
    }
    
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            counter.increment();
        }
    }
}

// SynchronizationExample.java
public class SynchronizationExample {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        
        // Create three threads to increment the counter
        Thread t1 = new CounterIncrementer(counter);
        Thread t2 = new CounterIncrementer(counter);
        Thread t3 = new CounterIncrementer(counter);
        
        // Start all three threads
        t1.start();
        t2.start();
        t3.start();
        
        // Wait for all threads to finish
        t1.join();
        t2.join();
        t3.join();
        
        System.out.println("Final Counter Value: " + counter.getCount());
    }
}
