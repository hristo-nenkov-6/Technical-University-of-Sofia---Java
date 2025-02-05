import java.util.ArrayList;
import java.util.Map;

public class DocumentHashTable {
    private Map<Integer, ArrayList<String>> documentsTable;

    public void addDocument(int key,
                            ArrayList<String> value) {
        documentsTable.put(key, value);
    }

    public ArrayList searchByKey(int key) {
        return documentsTable
                .get(key);
    }

    public String searchByValue(String value) {
        for(Integer key : documentsTable.keySet()) {
            if(documentsTable.get(key).contains(value)) {
                return "Document " + value + " is under key " + key;
            }
        }

        return null;
    }
}
