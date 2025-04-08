import java.util.*;

public class StudentDatabase {
    private Map<Integer, Student> studenti;
    private int dalsieId;

    public StudentDatabase() {
        studenti = new HashMap<>();
        dalsieId = 1;
    }

    public void pridajStudenta(String meno, String priezvisko, int rokNarodenia, String odbor) {
        Student student = null;

        if (odbor.equalsIgnoreCase("telekomunikacie")) {
            student = new TelekomunikacnyStudent(dalsieId, meno, priezvisko, rokNarodenia);
        } else if (odbor.equalsIgnoreCase("kyberbezpecnost")) {
            student = new KyberbezpecnostnyStudent(dalsieId, meno, priezvisko, rokNarodenia);
        } else {
            System.out.println("Neznámy odbor: " + odbor);
            return;
        }

        studenti.put(dalsieId, student);
        System.out.println("Študent pridaný: " + student);
        dalsieId++;
    }

    public boolean pridajZnamkuPreStudenta(int id, int znamka) {
        Student student = studenti.get(id);
        if (student != null) {
            if (znamka >= 1 && znamka <= 5) {
                student.pridajZnamku(znamka);
                System.out.println("Pridaná známka " + znamka + " študentovi: " + student.getMeno() + " " + student.getPriezvisko());
                return true;
            } else {
                System.out.println("Neplatná známka! Musí byť medzi 1 a 5.");
                return false;
            }
        } else {
            System.out.println("Študent s ID " + id + " neexistuje.");
            return false;
        }
    }

    public boolean odstranStudenta(int id) {
        if (studenti.containsKey(id)) {
            studenti.remove(id);
            System.out.println("Študent s ID " + id + " bol odstránený.");
            return true;
        } else {
            System.out.println("Študent s ID " + id + " neexistuje.");
            return false;
        }
    }

    public Student najdiStudentaPodlaId(int id) {
        return studenti.get(id);
    }

    public void spustiDovednostStudenta(int id) {
        Student student = studenti.get(id);
        if (student != null) {
            student.vykonajDovednost();
        } else {
            System.out.println("Študent s ID " + id + " neexistuje.");
        }
    }

    public void vypisStudentovAbecedne() {
        List<Student> zoznam = new ArrayList<>(studenti.values());

        zoznam.sort(Comparator.comparing(Student::getPriezvisko));

        for (Student student : zoznam) {
            System.out.println("ID: " + student.getId() + ", Meno: " + student.getMeno() + ", Priezvisko: " + student.getPriezvisko() +
                    ", Rok narodenia: " + student.getRokNarodenia() + ", Priemer: " + student.vypocitajPriemer());
        }
    }
    public void vypisPriemerPreOdbor(String odbor) {
        double sucet = 0;
        int pocet = 0;

        for (Student s : studenti.values()) {
            if ((odbor.equalsIgnoreCase("telekomunikacie") && s instanceof TelekomStudent) ||
                (odbor.equalsIgnoreCase("kyberbezpecnost") && s instanceof KyberStudent)) {

                double priemer = s.vypocitajPriemer();
                if (priemer > 0) { // len ak má známky
                    sucet += priemer;
                    pocet++;
                }
            }
        }

        if (pocet > 0) {
            double vysledok = sucet / pocet;
            System.out.println("📊 Priemer pre odbor " + odbor + ": " + vysledok);
        } else {
            System.out.println("❗ Žiadni študenti so známkami v odbore " + odbor);
        }
    }
    public void vypisPocetStudentovPodlaOdboru() {
        int telekom = 0;
        int kyber = 0;

        for (Student s : studenti.values()) {
            if (s instanceof TelekomStudent) {
                telekom++;
            } else if (s instanceof KyberStudent) {
                kyber++;
            }
        }

        System.out.println("👥 Počet študentov:");
        System.out.println("- Telekomunikácie: " + telekom);
        System.out.println("- Kyberbezpečnosť: " + kyber);
    }
    
}