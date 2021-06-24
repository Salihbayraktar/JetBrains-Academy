package SimpleSearchEngine.Strategies;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface SearchInterface {

    Set<Integer> search(Map<Integer,String> dataBase, Map<String, List<Integer>> indexMap, String query);
}
