public class Main {
    public static void main(String[] args) {
        // Vytvorenie študentov
        Student student1 = new TelekomunikacnyStudent(1, "Jan", "Novak", 1995);
        Student student2 = new KyberbezpecnostnyStudent(2, "Peter", "Kovac", 1996);

        // Pridanie známok
        student1.pridajZnamku(1);
        student1.pridajZnamku(3);
        student2.pridajZnamku(2);
        student2.pridajZnamku(4);

        // Vytlačenie informácií o študentoch
        System.out.println(student1);
        System.out.println(student2);

        // Vykonanie dovedností
        student1.vykonajDovednost();
        student2.vykonajDovednost();
    }
}