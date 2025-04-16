import java.io.*;
import java.util.*;

public class StudentDatabase {
    private Map<Integer, Student> studenti = new HashMap<>();
    private int dalsieId = 1;
    
    public void pridajStudenta(String meno, String priezvisko, int rokNarodenia, String odbor) {
        Student novy;
        if (odbor.equalsIgnoreCase("telekomunikacie")) {
            novy = new TelekomunikacnyStudent(dalsieId, meno, priezvisko, rokNarodenia);
        } else if (odbor.equalsIgnoreCase("kyberbezpecnost")) {
            novy = new KyberbezpecnostnyStudent(dalsieId, meno, priezvisko, rokNarodenia);
        } else {
            System.out.println("❌ Neznámy odbor.");
            return;
        }

        studenti.put(dalsieId, novy);
        System.out.println("✅ Študent pridaný: " + novy);
        dalsieId++;
    }

    public void pridajZnamkuPreStudenta(int id, int znamka) {
        Student s = studenti.get(id);
        if (s != null) {
            s.pridajZnamku(znamka);
            System.out.println("✅ Známka pridaná.");
        } else {
            System.out.println("❌ Študent s ID " + id + " neexistuje.");
        }
    }

    public void odstranStudenta(int id) {
        if (studenti.remove(id) != null) {
            System.out.println("✅ Študent odstránený.");
        } else {
            System.out.println("❌ Študent s ID " + id + " neexistuje.");
        }
    }

    public Student najdiStudentaPodlaId(int id) {
        return studenti.get(id);
    }

    public void spustiDovednostStudenta(int id) {
        Student s = studenti.get(id);
        if (s != null) {
            System.out.println("🎯 Výsledok dovednosti: " + "s.vykonajDovednost()");
        } else {
            System.out.println("❌ Študent s ID " + id + " neexistuje.");
        }
    }

    public void vypisStudentovAbecedne() {
        List<Student> zoznam = new ArrayList<>(studenti.values());
        zoznam.sort(Comparator.comparing(Student::getPriezvisko));

        System.out.println("📋 Zoznam všetkých študentov (abecedne):");
        for (Student s : zoznam) {
            System.out.println(s);
        }
    }

    public void vypisPriemerPreOdbor(String odbor) {
        double suma = 0;
        int pocet = 0;

        for (Student s : studenti.values()) {
            if (odbor.equalsIgnoreCase("telekomunikacie") && s instanceof TelekomunikacnyStudent) {
                suma += s.vypocitajPriemer();
                pocet++;
            } else if (odbor.equalsIgnoreCase("kyberbezpecnost") && s instanceof KyberbezpecnostnyStudent) {
                suma += s.vypocitajPriemer();
                pocet++;
            }
        }

        if (pocet == 0) {
            System.out.println("⚠️ Žiadni študenti v odbore: " + odbor);
        } else {
            System.out.printf("📊 Priemer pre odbor %s: %.2f%n", odbor, (suma / pocet));
        }
    }

    public void vypisPocetStudentovPodlaOdboru() {
        int telekom = 0;
        int kyber = 0;

        for (Student s : studenti.values()) {
            if (s instanceof TelekomunikacnyStudent) {
                telekom++;
            } else if (s instanceof KyberbezpecnostnyStudent) {
                kyber++;
            }
        }

        System.out.println("👥 Počet študentov:");
        System.out.println("- Telekomunikácie: " + telekom);
        System.out.println("- Kyberbezpečnosť: " + kyber);
    }

    public void ulozStudentaDoSuboru(int id) {
        Student s = studenti.get(id);
        if (s == null) {
            System.out.println("❗ Študent s ID " + id + " neexistuje.");
            return;
        }

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("student_" + id + ".ser"))) {
            out.writeObject(s);
            System.out.println("✅ Študent uložený do súboru: student_" + id + ".ser");
        } catch (Exception e) {
            System.out.println("❌ Chyba pri ukladaní: " + e.getMessage());
        }
    }

    public void nacitajStudentaZoSuboru(int id) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("student_" + id + ".ser"))) {
            Student s = (Student) in.readObject();
            System.out.println("📄 Načítaný študent zo súboru:");
            System.out.println("ID: " + s.getId());
            System.out.println("Meno: " + s.getMeno());
            System.out.println("Priezvisko: " + s.getPriezvisko());
            System.out.println("Rok narodenia: " + s.getRokNarodenia());
            System.out.println("Priemer: " + s.vypocitajPriemer());
        } catch (Exception e) {
            System.out.println("❌ Chyba pri načítaní: " + e.getMessage());
        }
    }
}