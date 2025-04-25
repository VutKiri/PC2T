import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentDatabase db = new StudentDatabase();

        
        db.nacitajZDatabazy();

        while (true) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Pridať študenta");
            System.out.println("2. Pridať známku študentovi");
            System.out.println("3. Odstrániť študenta");
            System.out.println("4. Zobraziť info o študentovi");
            System.out.println("5. Spustiť dovednosť študenta");
            System.out.println("6. Abecedný výpis všetkých študentov");
            System.out.println("7. Výpis študijného priemeru podľa odboru");
            System.out.println("8. Počet študentov podľa odboru");
            System.out.println("9. Uložiť študenta do súboru");
            System.out.println("10. Načítať študenta zo súboru");
            System.out.println("0. Ukončiť program");

            System.out.print("Zadaj možnosť: ");
            int volba = sc.nextInt();
            sc.nextLine(); // očistenie vstupu

            switch (volba) {
                case 1:
                    System.out.print("Meno: ");
                    String meno = sc.nextLine();
                    System.out.print("Priezvisko: ");
                    String priezvisko = sc.nextLine();
                    System.out.print("Rok narodenia: ");
                    int rok = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Odbor (telekomunikacie / kyberbezpecnost): ");
                    String odbor = sc.nextLine().toLowerCase();
                    db.pridajStudenta(meno, priezvisko, rok, odbor);
                    break;

                case 2:
                    System.out.print("Zadaj ID študenta: ");
                    int idZnamka = sc.nextInt();
                    System.out.print("Zadaj známku (1-5): ");
                    int znamka = sc.nextInt();
                    db.pridajZnamkuPreStudenta(idZnamka, znamka);
                    break;

                case 3:
                    System.out.print("Zadaj ID študenta na odstránenie: ");
                    int idOdstranit = sc.nextInt();
                    db.odstranStudenta(idOdstranit);
                    break;

                case 4:
                    System.out.print("Zadaj ID študenta: ");
                    int idInfo = sc.nextInt();
                    Student s = db.najdiStudentaPodlaId(idInfo);
                    if (s != null) {
                        System.out.println(s.toString());
                    } else {
                        System.out.println("Študent s ID " + idInfo + " neexistuje.");
                    }
                    break;

                case 5:
                    System.out.print("Zadaj ID študenta: ");
                    int idSkill = sc.nextInt();
                    db.spustiDovednostStudenta(idSkill);
                    break;

                case 6:
                    db.vypisStudentovAbecedne();
                    break;

                case 7:
                    System.out.print("Zadaj odbor (telekomunikacie / kyberbezpecnost): ");
                    String odborPriemer = sc.nextLine().toLowerCase();
                    db.vypisPriemerPreOdbor(odborPriemer);
                    break;

                case 8:
                    db.vypisPocetStudentovPodlaOdboru();
                    break;

                case 9:
                    System.out.print("Zadaj ID študenta: ");
                    int idUlozit = sc.nextInt();
                    db.ulozStudentaDoSuboru(idUlozit);
                    break;

                case 10:
                    System.out.print("Zadaj ID študenta: ");
                    int idNacitaj = sc.nextInt();
                    db.nacitajStudentaZoSuboru(idNacitaj);
                    break;

                case 0:
                    System.out.println("Ukladám dáta do databázy a končím program...");
                    db.ulozDoDatabazy();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Neplatná voľba, skús znova.");
            }
        }
    }
}