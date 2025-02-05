public class HashTable {
    private Entry[] table;
    private int size;

    private class Entry{
        String username;
        String email;

        public Entry(String username, String email) {
            this.username = username;
            this.email = email;
        }
    }

    public HashTable(int size) {
        this.table = new Entry[size];
        this.size = size;
    }

    private int hash(String username){
        int hashValue = 0;
        for(int i = 0; i < username.length(); i++){
            hashValue += username.charAt(i);
        }
        return hashValue % size;
    }

    public void add(String username,
                    String email){
        int index = hash(username);
        table[index] = new Entry(username, email);
    }

    public String get(String username){
        int index = hash(username);
        if(table[index] != null &&
        table[index].username.equals(username)){
            return table[index].email;
        }else{
            return null;
        }


    }

    public static void main(String args[]){
        HashTable userTable = new HashTable(10);
        userTable.add("John", "john@gmail.com");
        userTable.add("Jane", "jane@gmail.com");
        userTable.add("Jack", "jack@gmail.com");

        System.out.println(userTable.get("Jane"));
    }
}
