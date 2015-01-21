package ss.week7.threads;

public class OwnIntCell implements IntCell{

	public int value = 0;
	public boolean isNotRead;
	
	@Override
	public synchronized void setValue(int val) {
		while(isNotRead){
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		value = val;
		isNotRead = true;
		notify();
	}

	@Override
	public synchronized int getValue() {
		while(!isNotRead){
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		isNotRead = false;
		notify();
		return value;
	}

}
