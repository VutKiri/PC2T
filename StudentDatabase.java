import java.io.*;
import java.sql.*;
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
            System.out.println("Neznámy odbor.");
            return;
        }

        studenti.put(dalsieId, novy);
        System.out.println("Študent pridaný: " + novy);
        dalsieId++;
    }

    public void pridajZnamkuPreStudenta(int id, int znamka) {
        Student s = studenti.get(id);
        if (s != null) {
            s.pridajZnamku(znamka);
            System.out.println("Známka pridaná.");
        } else {
            System.out.println("Študent s ID " + id + " neexistuje.");
        }
    }

    public void odstranStudenta(int id) {
        if (studenti.remove(id) != null) {
            System.out.println("Študent odstránený.");
        } else {
            System.out.println("Študent s ID " + id + " neexistuje.");
        }
    }

    public Student najdiStudentaPodlaId(int id) {
        return studenti.get(id);
    }

    public void spustiDovednostStudenta(int id) {
        Student s = studenti.get(id);
        if (s != null) {
            System.out.println("Výsledok dovednosti: " + "s.vykonajDovednost()");
        } else {
            System.out.println("Študent s ID " + id + " neexistuje.");
        }
    }

    public void vypisStudentovAbecedne() {
        List<Student> zoznam = new ArrayList<>(studenti.values());
        zoznam.sort(Comparator.comparing(Student::getPriezvisko));

        System.out.println("Zoznam všetkých študentov (abecedne):");
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

        System.out.println("Počet študentov:");
        System.out.println("- Telekomunikácie: " + telekom);
        System.out.println("- Kyberbezpečnosť: " + kyber);
    }

    public void ulozStudentaDoSuboru(int id) {
        Student s = studenti.get(id);
        if (s == null) {
            System.out.println("Študent s ID " + id + " neexistuje.");
            return;
        }

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("student_" + id + ".ser"))) {
            out.writeObject(s);
            System.out.println("Študent uložený do súboru: student_" + id + ".ser");
        } catch (Exception e) {
            System.out.println("Chyba pri ukladaní: " + e.getMessage());
        }
    }

    public void nacitajStudentaZoSuboru(int id) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("student_" + id + ".ser"))) {
            Student s = (Student) in.readObject();
            System.out.println("Načítaný študent zo súboru:");
            System.out.println("ID: " + s.getId());
            System.out.println("Meno: " + s.getMeno());
            System.out.println("Priezvisko: " + s.getPriezvisko());
            System.out.println("Rok narodenia: " + s.getRokNarodenia());
            System.out.println("Priemer: " + s.vypocitajPriemer());
        } catch (Exception e) {
            System.out.println("Chyba pri načítaní: " + e.getMessage());
        }
    }
    private Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:students.db");
    }
    public void ulozDoDatabazy() {
        try (Connection conn = connect()) {
            conn.createStatement().execute("DELETE FROM znamka");
            conn.createStatement().execute("DELETE FROM student");

            for (Student s : studenti.values()) {
                PreparedStatement ps = conn.prepareStatement("INSERT INTO student(id, meno, priezvisko, rokNarodenia, odbor) VALUES (?, ?, ?, ?, ?)");
                ps.setInt(1, s.getId());
                ps.setString(2, s.getMeno());
                ps.setString(3, s.getPriezvisko());
                ps.setInt(4, s.getRokNarodenia());
                ps.setString(5, s.getOdbor());
                ps.executeUpdate();

                for (int znamka : s.getZnamky()) {
                    PreparedStatement pz = conn.prepareStatement("INSERT INTO znamka(student_id, hodnota) VALUES (?, ?)");
                    pz.setInt(1, s.getId());
                    pz.setInt(2, znamka);
                    pz.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void nacitajZDatabazy() {
        try (Connection conn = connect()) {
            // vytvorenie tabuliek, ak ešte neexistujú
            Statement stmt = conn.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS student (" +
                "id INTEGER PRIMARY KEY, " +
                "meno TEXT NOT NULL, " +
                "priezvisko TEXT NOT NULL, " +
                "rokNarodenia INTEGER NOT NULL, " +
                "odbor TEXT NOT NULL)");
            stmt.execute("CREATE TABLE IF NOT EXISTS znamka (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "student_id INTEGER NOT NULL, " +
                "hodnota INTEGER NOT NULL, " +
                "FOREIGN KEY (student_id) REFERENCES student(id))");

            ResultSet rs = stmt.executeQuery("SELECT * FROM student");
            while (rs.next()) {
                int id = rs.getInt("id");
                String meno = rs.getString("meno");
                String priezvisko = rs.getString("priezvisko");
                int rok = rs.getInt("rokNarodenia");
                String odbor = rs.getString("odbor");

                Student s;
                if (odbor.equalsIgnoreCase("telekomunikacie")) {
                    s = new TelekomunikacnyStudent(id, meno, priezvisko, rok);
                } else {
                    s = new KyberbezpecnostnyStudent(id, meno, priezvisko, rok);
                }

                ResultSet znamkyRS = conn.createStatement().executeQuery("SELECT hodnota FROM znamka WHERE student_id = " + id);
                while (znamkyRS.next()) {
                    s.pridajZnamku(znamkyRS.getInt("hodnota"));
                }

                studenti.put(id, s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}