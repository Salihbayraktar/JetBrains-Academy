package SimpleSearchEngine.Strategies;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SearchAny implements SearchInterface {

    @Override
    public Set<Integer> search(Map<Integer, String> dataBase, Map<String, List<Integer>> indexMap, String query) {

        String[] words = query.toLowerCase().split(" ");

        Set<Integer> result = new HashSet<>();

        for (String word : words) {
            if (indexMap.get(word) != null) {
                result.addAll(indexMap.get(word));
            }
        }

        return result;

    }
}
