package SimpleSearchEngine.Strategies;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class StrategyContext {

    SearchInterface searchInterface;

    public StrategyContext(SearchInterface searchInterface) {
        this.searchInterface = searchInterface;
    }

    public Set<Integer> getResult(Map<Integer, String> dataBase, Map<String, List<Integer>> indexMap, String query) {
        return searchInterface.search(dataBase, indexMap, query);
    }

}