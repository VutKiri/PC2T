import java.util.ArrayList;
import java.util.List;

public abstract class Student {
    protected int id;
    protected String meno;
    protected String priezvisko;
    protected int rokNarodenia;
    protected List<Integer> znamky;
    protected String odbor;

    public Student(int id, String meno, String priezvisko, int rokNarodenia, String odbor) {
        this.id = id;
        this.meno = meno;
        this.priezvisko = priezvisko;
        this.rokNarodenia = rokNarodenia;
        this.odbor = odbor;
        this.znamky = new ArrayList<>();
    }

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

    public String getOdbor() {
        return odbor;
    }

    public List<Integer> getZnamky() {
        return znamky;
    }

    public void pridajZnamku(int znamka) {
        if (znamka >= 1 && znamka <= 5) {
            znamky.add(znamka);
        }
    }

    public double vypocitajPriemer() {
        if (znamky.isEmpty()) return 0.0;
        int sum = 0;
        for (int z : znamky) {
            sum += z;
        }
        return (double) sum / znamky.size();
    }

    public abstract void spustiDovednost();

    @Override
    public String toString() {
        return "ID: " + id +
               ", Meno: " + meno +
               ", Priezvisko: " + priezvisko +
               ", Rok narodenia: " + rokNarodenia +
               ", Odbor: " + odbor +
               ", Študijný priemer: " + String.format("%.2f", vypocitajPriemer());
    }
}