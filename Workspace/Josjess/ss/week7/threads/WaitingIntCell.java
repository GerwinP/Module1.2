package ss.week7.threads;

import java.util.concurrent.locks.ReentrantLock;


public class WaitingIntCell implements IntCell {
    private int value = 0;
    private boolean unconsumedValue = false;

    public void setValue(int valueArg) {
        while(unconsumedValue){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        unconsumedValue = true;
        this.value = valueArg;
        notify();
    }

    public int getValue() {
        while (!unconsumedValue) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        unconsumedValue = false;
        notify();
        return value;
    }
}
