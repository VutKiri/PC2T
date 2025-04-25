import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class KyberbezpecnostnyStudent extends Student {

    public KyberbezpecnostnyStudent(int id, String meno, String priezvisko, int rokNarodenia) {
        super(id, meno, priezvisko, rokNarodenia, "Kyberbezpecnost");
    }

    @Override
    public void spustiDovednost() {
        String fullName = meno + " " + priezvisko;
        System.out.println("Hash mena: " + hashujMeno(fullName));
    }

    private String hashujMeno(String text) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(text.getBytes());
            StringBuilder sb = new StringBuilder();

            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            return "Chyba pri hashovani.";
        }
    }
}