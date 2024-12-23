import java.util.concurrent.CopyOnWriteArrayList;

// ListUpdater.java
class ListUpdater extends Thread {
    private CopyOnWriteArrayList<Integer> list;
    
    public ListUpdater(CopyOnWriteArrayList<Integer> list) {
        this.list = list;
    }
    
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            list.add(i);
            System.out.println("Added: " + i);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

// ConcurrentListExample.java
public class ConcurrentListExample {
    public static void main(String[] args) throws InterruptedException {
        // Using CopyOnWriteArrayList for thread-safe list operations
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();
        
        // Create two threads to update the list concurrently
        Thread t1 = new ListUpdater(list);
        Thread t2 = new ListUpdater(list);
        
        // Start both threads
        t1.start();
        t2.start();
        
        // Wait for both threads to finish
        t1.join();
        t2.join();
        
        // Print the final list after updates
        System.out.println("Final List: " + list);
    }
}
