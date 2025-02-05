public class TryHashFunctions {
    public static Integer hash1(String email){
        int hashValue = 0;

        String userName = email.split("@")[0];
        String domain = email.split("@")[1];

        for(int i = 0; i < userName.length(); i++){
            hashValue = (hashValue * 17) + email.charAt(i);
        }

        for(int i = 0; i < domain.length(); i++){
            hashValue = (hashValue * 31) + email.charAt(userName.length() + i);
        }

        return hashValue;
    }

    public static Integer hash2(String email){
        int hashValue = 0;

        String userName = email.split("@")[0];
        String domain = email.split("@")[1];

        for(int i = 0; i < userName.length(); i++){
            hashValue = (hashValue * 31) + email.charAt(i);
        }

        for(int i = 0; i < domain.length(); i++){
            hashValue = (hashValue * 17) + email.charAt(userName.length() + i);
        }

        return hashValue;
    }

    public static void main(String[] args){
        System.out.println(hash1("hristo041978@abv.bg"));
        System.out.println(hash2("hristo041978@abv.bg"));
        System.out.println(hash1("ganevg@gmail.com"));
        System.out.println(hash2("ganevg@gmail.com"));
    }
}
