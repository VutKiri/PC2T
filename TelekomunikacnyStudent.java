public class TelekomunikacnyStudent extends Student {

    public TelekomunikacnyStudent(int id, String meno, String priezvisko, int rokNarodenia) {
        super(id, meno, priezvisko, rokNarodenia, "Telekomunikacie");
    }

    @Override
    public void spustiDovednost() {
        System.out.println("Meno v Morseovej abecede: " + previestNaMorse(meno + " " + priezvisko));
    }

    private String previestNaMorse(String text) {
        String[] morseAbeceda = {
            ".-", "-...", "-.-.", "-..", ".", "..-.", "--.",
            "....", "..", ".---", "-.-", ".-..", "--", "-.",
            "---", ".--.", "--.-", ".-.", "...", "-", "..-",
            "...-", ".--", "-..-", "-.--", "--.." };

        text = text.toUpperCase();
        StringBuilder vysledok = new StringBuilder();

        for (char c : text.toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                vysledok.append(morseAbeceda[c - 'A']).append(" ");
            } else if (c == ' ') {
                vysledok.append(" / ");
            } else {
                vysledok.append("? ");
            }
        }
        return vysledok.toString();
    }
}