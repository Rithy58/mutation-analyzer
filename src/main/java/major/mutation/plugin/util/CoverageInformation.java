package major.mutation.plugin.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;

public class CoverageInformation extends LinkedHashMap<String, List<Integer>> {

	private static HashSet<Integer> allMutants = new HashSet<>();

	private HashMap<String, Long> keyMap;
	private List<String> keyList;
	private HashSet<Integer> valueSet;

	public CoverageInformation() {
		valueSet = new LinkedHashSet<>();
		keyMap = new HashMap<>();
		keyList = new ArrayList<>();
	}

	public List<Integer> put(String key, List<Integer> value, long runTime) {
		keyMap.put(key, runTime);
		keyList.add(key);
		for (Integer e : value) {
			valueSet.add(e);
			allMutants.add(e);
		}
		return put(key, value);
	}

	public HashSet<Integer> getValues() {
		return valueSet;
	}

	public List<String> getKeys() {
		keyList.sort(new Comparator<String>() {
			long first;
			long second;
			@Override
			public int compare(String a, String b) {
				first = keyMap.get(a);
				second = keyMap.get(b);
				if (first == second) return 0;
				return first < second ? -1 : 1;
			}
		});
		return keyList;
	}

	public static void reset() {
		allMutants.clear();
	}

	public static HashSet<Integer> getAllMutants() {
		return allMutants;
	}

}
