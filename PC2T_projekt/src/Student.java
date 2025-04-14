package projekt_pc2t;

import java.util.ArrayList;
import java.util.List;

public abstract class Student {
    protected int id;
    protected String meno;
    protected String priezvisko;
    protected int rokNarodenia;
    protected List<Integer> znamky;

    public Student(int id, String meno, String priezvisko, int rokNarodenia) {
        this.id = id;
        this.meno = meno;
        this.priezvisko = priezvisko;
        this.rokNarodenia = rokNarodenia;
        this.znamky = new ArrayList<>();
    }

    public void pridajZnamku(int znamka) {
        if (znamka >= 1 && znamka <= 5) {
            znamky.add(znamka);
        } else {
            System.out.println("Neplatná známka! Zadajte hodnotu medzi 1 a 5.");
        }
    }

    public double vypocitajPriemer() {
        if (znamky.isEmpty()) {
            return 0.0;
        }
        int suma = 0;
        for (int z : znamky) {
            suma += z;
        }
        return (double) suma / znamky.size();
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Meno: " + meno + " " + priezvisko + 
               ", Rok narodenia: " + rokNarodenia + 
               ", Študijný priemer: " + vypocitajPriemer();
    }

    // Abstraktná metóda pre vykonanie špeciálnej dovednosti
    public abstract void vykonajDovednost();

	
	public int getId() {
	    return id;
	}

	public String getMeno() {
	    return meno;
	}

	public String getPriezvisko() {
	    return priezvisko;
	}

	public int getRokNarodenia() {
	    return rokNarodenia;
	}
}

	
