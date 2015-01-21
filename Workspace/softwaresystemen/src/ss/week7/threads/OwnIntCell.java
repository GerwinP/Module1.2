package ss.week7.threads;

public class OwnIntCell implements IntCell{

	public int value = 0;
	public boolean isRead = false;
	
	@Override
	public synchronized void setValue(int val) {
		value = val;
		
	}

	@Override
	public synchronized int getValue() {
		
		return value;
	}

}
