package server;

public class ProxyDatabase {
    private final Database database = new Database();
    private final String OK = "OK";
    private final String ERROR = "ERROR";

    String set(int index, String text) {
        if (database.set(index, text)) {
            return OK;
        }
        return ERROR;
    }

    String get(int index) {
        String text = database.get(index);
        if (text == null || text.isEmpty()) {
            return ERROR;
        }
        return text;
    }

    String delete(int index) {
        if (database.delete(index)) {
            return OK;
        }
        return ERROR;
    }
}
