package ss.week7.threads;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FineGrainedIntCell implements IntCell{

	private int value = 0;
	private boolean isNotRead;
	private Lock lock = new ReentrantLock();
	private Condition notFull = lock.newCondition();
	private Condition notEmpty = lock.newCondition();
	
	@Override
	public void setValue(int val) {
		lock.lock();
		try{
			while(isNotRead){
				notFull.await();
			}
			isNotRead = true;
			value = val;
			notEmpty.signal();
		} catch(InterruptedException e){
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		
	}

	@Override
	public int getValue() {
		lock.lock();
		try{
			while(!isNotRead){
				notEmpty.await();
			}
			notFull.signal();
		}catch (InterruptedException e){
			e.printStackTrace();
		}finally{
			isNotRead = false;
			lock.unlock();
		}
		
		return value;
	}

}
