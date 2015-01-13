package ss.week7.threads;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class SynchronizedIntCell implements IntCell {
    private int value = 0;
    private boolean unconsumedValue = false;
    private Lock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();

    public void setValue(int valueArg) {
        lock.lock();
        try {
            while (unconsumedValue) {
                notFull.await();
            }
            unconsumedValue = true;
            value = valueArg;
            notEmpty.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public int getValue() {
        lock.lock();
        try {
            while (!unconsumedValue) {
                notEmpty.await();
            }
            notFull.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            unconsumedValue = false;
            lock.unlock();
        }
        return value;
    }
}
