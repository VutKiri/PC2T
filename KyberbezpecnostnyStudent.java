import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class KyberbezpecnostnyStudent extends Student {

    public KyberbezpecnostnyStudent(int id, String meno, String priezvisko, int rokNarodenia) {
        super(id, meno, priezvisko, rokNarodenia);
    }

    @Override
    public void vykonajDovednost() {
        System.out.println("Hash mena a priezviska pre " + meno + " " + priezvisko + ": " + vypocitajHash(meno + " " + priezvisko));
    }

    private String vypocitajHash(String text) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(text.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            return "Chyba pri hashovaní";
        }
    }
}
