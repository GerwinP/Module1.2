package ss.week5;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MapUtils {

	//Opdracht p-5.1
	public static <K,V> boolean isOneOnOne(Map<K,V> f){
		boolean isOneOnOne = true;
		for(Object key : f.keySet()){
			Object value = f.get(key);
			for(Object keyCompare : f.keySet()){
				if(key != keyCompare && f.get(keyCompare) == value){
					isOneOnOne = false;
				}
			}
		}
		return isOneOnOne;
	}
	
	//Opdracht p-5.2
	public static <K,V> boolean isSurjectiveOnRange(Set<V> range, Map<K,V> f){
		boolean isSurjectiveOnRange = true;
		for(Object value : range){
			for(Object key : f.keySet()){
				Object valueCompare = f.get(key);
				if(value != valueCompare){
					isSurjectiveOnRange = false;
				}
			}
		}
		return isSurjectiveOnRange;
	}
	
	//Opdracht p-5.3
	public static <K,V> Map<V,Set<K>> inverse(Map<K,V> f){
		Map<V,Set<K>> fInverse = new HashMap<V, Set<K>>();
		for(K key : f.keySet()){
			V value = f.get(key);
			if(fInverse.containsKey(value)){
				Set<K> inverseValue = fInverse.get(value);
				inverseValue.add(key);
				fInverse.put(value, inverseValue);
			}else{
				Set<K> newValue = new HashSet<K>();
				newValue.add(key);
				fInverse.put(value, newValue);
			}
		}
		return fInverse;
	}
	
	public static <K,V> Map<V,K> inverseBijection(Set<V> range, Map<K,V> f){
		Map<V,K> fInverseBijection = new HashMap<V, K>();
		if(isOneOnOne(f) && isSurjectiveOnRange(range, f)){
			for(K key : f.keySet()){
				V value = f.get(key);
				fInverseBijection.put(value, key);
			}
		}
		return fInverseBijection;
	}
	
	//Opdracht p-5.4
	public static <K,V> boolean isCompatible(Map<K,V> f, Map<K,V> g){
		boolean compatible = false;
		for(K key : f.keySet()){
			V value = f.get(key);
			for(K keyG : g.keySet()){
				if(value != keyG){
					compatible = false;
				}
			}
		}
		return compatible;
	}
	
	public static <K,V> Map<K,V> compose(Map<K,V> f, Map<K,V> g){
		Map<K,V> mapR = new HashMap<K,V>();
		if(isCompatible(f,g)){
			for(K key : f.keySet()){
				mapR.put(key, g.get(f.get(key)));
			}
		}
		return mapR;
	}
}
