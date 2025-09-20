package models;

/**
 * Subclass dari Kontak untuk Teman
 */
public class Teman extends Kontak {
    private String hobi;

    public Teman(int id, String nama, String nomor, String hobi) {
        super(id, nama, nomor);
        this.hobi = hobi;
    }

    public String getHobi() { return hobi; }
    public void setHobi(String hobi) { this.hobi = hobi; }

    @Override
    public String toString() {
        return super.toString() + " | Hobi: " + hobi;
    }
}
