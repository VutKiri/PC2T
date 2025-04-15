package projekt_pc2t;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentDatabase db = new StudentDatabase();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Pridať nového študenta");
            System.out.println("2. Pridať známku študentovi");
            System.out.println("3. Odstrániť študenta");
            System.out.println("4. Zobraziť informácie o študentovi podľa ID");
            System.out.println("5. Spustiť dovednosť študenta");
            System.out.println("6. Zobraziť všetkých študentov podľa priezviska");
            System.out.println("7. Výpis priemeru pre odbor");
            System.out.println("8. Výpis počtu študentov podľa odboru");
            System.out.println("0. Ukončiť program");
            System.out.print("Zvoľ možnosť: ");

            int volba = scanner.nextInt();
            scanner.nextLine(); 

            switch (volba) {
                case 1:
                    System.out.print("Meno: ");
                    String meno = scanner.nextLine();
                    System.out.print("Priezvisko: ");
                    String priezvisko = scanner.nextLine();
                    System.out.print("Rok narodenia: ");
                    int rok = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.print("Odbor: telekomunikacie (TLI) / kyberbezpecnost (KYB) ");
                    String odbor = scanner.nextLine();
                    db.pridajStudenta(meno, priezvisko, rok, odbor);
                    break;

                case 2:
                    System.out.print("ID študenta: ");
                    int idZ = scanner.nextInt();
                    System.out.print("Zadaj známku (1-5): ");
                    int znamka = scanner.nextInt();
                    db.pridajZnamkuPreStudenta(idZ, znamka);
                    break;

                case 3:
                    System.out.print("ID študenta na odstránenie: ");
                    int idO = scanner.nextInt();
                    db.odstranStudenta(idO);
                    break;

                case 4:
                    System.out.print("ID študenta na zobrazenie: ");
                    int idInfo = scanner.nextInt();
                    Student s = db.najdiStudentaPodlaId(idInfo);
                    if (s != null) {
                        System.out.println("ID: " + s.getId());
                        System.out.println("Meno: " + s.getMeno());
                        System.out.println("Priezvisko: " + s.getPriezvisko());
                        System.out.println("Rok narodenia: " + s.getRokNarodenia());
                        System.out.println("Priemer: " + s.vypocitajPriemer());
                    } else {
                        System.out.println("Študent s ID " + idInfo + " neexistuje.");
                    }
                    break;

                case 5:
                    System.out.print("ID študenta na spustenie dovednosti: ");
                    int idD = scanner.nextInt();
                    db.spustiDovednostStudenta(idD);
                    break;

                case 6:
                    db.vypisStudentovAbecedne();
                    break;

                case 7:
                    System.out.print("Zadaj odbor (telekomunikacie / kyberbezpecnost): ");
                    String odborPriemer = scanner.nextLine();
                    db.vypisPriemerPreOdbor(odborPriemer);
                    break;

                case 8:
                    db.vypisPocetStudentovPodlaOdboru();
                    break;
                    
                case 0:
                    System.out.println("Program ukončený.");
                    running = false;
                    break;

                default:
                    System.out.println("Neplatná voľba. Skús znova.");
                    break;
            }
        }

        scanner.close();
    }
}
