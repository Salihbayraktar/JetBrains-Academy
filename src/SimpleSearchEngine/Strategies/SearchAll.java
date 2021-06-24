package SimpleSearchEngine.Strategies;

import java.util.*;

public class SearchAll implements SearchInterface {

    @Override
    public Set<Integer> search(Map<Integer, String> dataBase, Map<String, List<Integer>> indexMap, String query) {
        String[] words = query.toLowerCase().split(" ");
        int totalQueries = words.length;

        List<Integer> allSearchResult = new LinkedList<>();

        for (String word : words) {
            if (indexMap.get(word) != null) {
                allSearchResult.addAll(indexMap.get(word));
            }
        }

        Set<Integer> result = new HashSet<>();

        for (int i = 0; i < allSearchResult.size(); i++) {

            int count = 0;

            for (int j = 0; j < allSearchResult.size(); j++) {

                if (allSearchResult.get(i).equals(allSearchResult.get(j))) {
                    count++;
                    if (count > 1) {
                        allSearchResult.remove(allSearchResult.get(j));
                    }
                }

                if (count == totalQueries) {
                    result.add(allSearchResult.get(i));
                    break;
                }

            }

        }
        return result;

    }
}
