package major.mutation.plugin.coverage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;

public class TrackingLinkedHashMap<K, V extends List<E>, E> extends LinkedHashMap<K, V> {

	private HashMap<K, Long> keyMap;
	private List<K> keyList;
	private HashSet<E> valueSet;

	public TrackingLinkedHashMap() {
		valueSet = new LinkedHashSet<>();
		keyMap = new HashMap<>();
		keyList = new ArrayList<>();
	}

	public V put(K key, V value, long runTime) {
		keyMap.put(key, runTime);
		keyList.add(key);
		for (E e : value) {
			valueSet.add(e);
		}
		return put(key, value);
	}

	public HashSet<E> getValues() {
		return valueSet;
	}

	public List<K> getKeys() {
		keyList.sort(new Comparator<K>() {
			@Override
			public int compare(K a, K b) {
				long first = keyMap.get(a);
				long second = keyMap.get(b);
				if (first == second) return 0;
				return first < second ? -1 : 1;
			}
		});
		return keyList;
	}

}
