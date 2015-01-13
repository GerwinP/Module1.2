package ss.week5;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapUtils<K, V> implements java.util.Map<K, V>{ 
	 
	//5.1
	public static <K, V> boolean isOneOnOne(Map<K, V> map){
		int occurrences = 0;
		int aantalFalse = 0;
		for(V value: map.values()){
			for(K key: map.keySet()){
				if(map.get(key).equals(value)){
					occurrences++;
				}
			}
			if(occurrences != 1){
				aantalFalse++;
			}
		}
		return(aantalFalse == 0);
	}
	
	//5.2
	public static <K, V> boolean isSurjectiveOnRange(Map<K, V> map){
		int aantalNull = 0;
		for(V value: map.values()){
			if(value == null ){
				aantalNull++;
			}
		}
		return(aantalNull == 0);
	}
	
	//5.4
	public static <K, V> boolean compatible(Map<K, V> mapA, Map<K, V> mapB){
		int aantalFalse = 0;
		for(V value: mapA.values()){
			if(!(mapB.containsKey(value))){
				aantalFalse++;
			}
		}
		return (aantalFalse == 0);
	}
	
	public static <K, V> Map<K, V> compose(Map<K, V> mapA, Map<K, V> mapB){
		Map<K , V> mapR = new HashMap<K, V>();
		if(compatible(mapA, mapB) == true){
			for(K key: mapA.keySet()){
				mapR.put(key, mapB.get(mapA.get(key)));
			}
		}
		return mapR;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean containsKey(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsValue(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V get(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<K> keySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V put(K arg0, V arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public V remove(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Collection<V> values() {
		// TODO Auto-generated method stub
		return null;
	}

}
