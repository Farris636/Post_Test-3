package services;

import models.Kontak;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * CRUD & fitur tambahan
 */
public class KontakService {
    private final List<Kontak> daftar = new ArrayList<>();
    private int nextId = 1;

    // CREATE
    public Kontak addKontak(Kontak k) {
        daftar.add(k);
        return k;
    }

    // READ
    public List<Kontak> getAllKontak() {
        return new ArrayList<>(daftar);
    }

    // UPDATE
    public boolean updateKontak(int id, String namaBaru, String nomorBaru) {
        Kontak k = findById(id);
        if (k == null) return false;
        k.setNama(namaBaru);
        k.setNomor(nomorBaru);
        return true;
    }

    // DELETE
    public boolean deleteKontak(int id) {
        Kontak k = findById(id);
        if (k == null) return false;
        return daftar.remove(k);
    }

    // SORT
    public void sortKontakAZ() {
        Collections.sort(daftar, Comparator.comparing(k -> k.getNama().toLowerCase()));
    }

    // SEARCH
    public List<Kontak> searchByName(String query) {
        List<Kontak> hasil = new ArrayList<>();
        String q = query.toLowerCase();
        for (Kontak k : daftar) {
            if (k.getNama().toLowerCase().contains(q)) hasil.add(k);
        }
        return hasil;
    }

    // FIND by ID
    public Kontak findById(int id) {
        for (Kontak k : daftar) {
            if (k.getId() == id) return k;
        }
        return null;
    }

    // Auto ID
    public int generateId() {
        return nextId++;
    }

    public boolean isEmpty() {
        return daftar.isEmpty();
    }
}
