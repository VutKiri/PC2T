public class TelekomunikacnyStudent extends Student {

    public TelekomunikacnyStudent(int id, String meno, String priezvisko, int rokNarodenia) {
        super(id, meno, priezvisko, rokNarodenia);
    }

    @Override
    public void vykonajDovednost() {
        System.out.println("Morseova abeceda pre " + meno + " " + priezvisko + ": " + konvertujDoMorse(meno + " " + priezvisko));
    }

    private String konvertujDoMorse(String text) {
        String morseKody = "A:.- B:-... C:-.-. D:-.. E:. F:..-. G:--. H:.... I:.. J:.--- K:-.- L:.-.. M:-- N:-. O:--- P:.--. Q:--.- R:.-. S:... T:- U:..- V:...- W:.-- X:-..- Y:-.-- Z:--..";
        String[] znaky = morseKody.split(" ");
        StringBuilder morseText = new StringBuilder();

        text = text.toUpperCase();
        for (char c : text.toCharArray()) {
            if (c == ' ') {
                morseText.append(" / ");
            } else {
                for (String znak : znaky) {
                    if (znak.startsWith(c + ":")) {
                        morseText.append(znak.substring(2)).append(" ");
                        break;
                    }
                }
            }
        }
        return morseText.toString();
    }
}