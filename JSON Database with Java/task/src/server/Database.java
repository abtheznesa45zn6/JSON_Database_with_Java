package server;

public class Database {
    final String[] data = new String[100];

    boolean set(int index, String string) {
        if (index < 101 && index > 0) {
            data[index-1] = string;
            return true;
        }
        return false;
    }

    String get(int index) {
        if (index < 101 && index > 0) {
            return data[index-1];
        }
        return "";
    }

    boolean delete(int index) {
        if (index < 101 && index > 0) {
            data[index-1] = "";
            return true;
        }
        return false;
    }
}
