package server;

public class Database {
    final String[] data = new String[1000];

    boolean set(int index, String string) {
        if (index < 1001 && index > 0) {
            data[index-1] = string;
            return true;
        }
        return false;
    }

    String get(int index) {
        if (index < 1001 && index > 0) {
            return data[index-1];
        }
        return "";
    }

    boolean delete(int index) {
        if (index < 1001 && index > 0) {
            data[index-1] = "";
            return true;
        }
        return false;
    }
}
