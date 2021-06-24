package SimpleSearchEngine.Strategies;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class SearchNone implements SearchInterface {

    @Override
    public Set<Integer> search(Map<Integer, String> dataBase, Map<String, List<Integer>> indexMap, String query) {

        SearchAny searchAny = new SearchAny();

        Set<Integer> result = dataBase.keySet();                          // ALL_KEYS

        Set<Integer> keys = searchAny.search(dataBase, indexMap, query);  // ANY_KEYS

        for (Integer value : keys) {
            result.remove(value);                                         // NONE_KEYS = ALL_KEYS - ANY_KEYS
        }

        return result;

    }
}
