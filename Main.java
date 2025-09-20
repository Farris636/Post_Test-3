package main;

import models.*;
import services.KontakService;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final KontakService service = new KontakService();

    public static void main(String[] args) {
        int pilihan;
        do {
            showMenu();
            pilihan = readInt("Pilih menu: ");
            switch (pilihan) {
                case 1: tambahKontak(); break;
                case 2: lihatSemuaKontak(); break;
                case 3: ubahKontak(); break;
                case 4: hapusKontak(); break;
                case 5: service.sortKontakAZ(); System.out.println("Kontak diurutkan A-Z."); break;
                case 6: cariKontak(); break;
                case 7: System.out.println("Keluar..."); break;
                default: System.out.println("Pilihan tidak valid.");
            }
            System.out.println();
        } while (pilihan != 7);
    }

    private static void showMenu() {
        System.out.println("===== SISTEM CATATAN KONTAK =====");
        System.out.println("1. Tambah Kontak");
        System.out.println("2. Lihat Semua Kontak");
        System.out.println("3. Ubah Kontak (by id)");
        System.out.println("4. Hapus Kontak (by id)");
        System.out.println("5. Sortir Kontak (A-Z)");
        System.out.println("6. Cari Kontak (by nama)");
        System.out.println("7. Keluar");
    }

    private static void tambahKontak() {
        String nama = readNonEmptyString("Masukkan nama: ");
        String nomor = readValidPhone("Masukkan nomor: ");

        System.out.print("Jenis (1. Keluarga, 2. Teman): ");
        int jenis = readInt("");
        Kontak k;
        if (jenis == 1) {
            String hubungan = readNonEmptyString("Masukkan hubungan (misal: Ayah/Ibu/Adik): ");
            k = new Keluarga(service.generateId(), nama, nomor, hubungan);
        } else {
            String hobi = readNonEmptyString("Masukkan hobi: ");
            k = new Teman(service.generateId(), nama, nomor, hobi);
        }
        service.addKontak(k);
        System.out.println("Kontak berhasil ditambahkan: " + k);
    }

    private static void lihatSemuaKontak() {
        List<Kontak> all = service.getAllKontak();
        if (all.isEmpty()) {
            System.out.println("Belum ada kontak.");
            return;
        }
        for (Kontak k : all) System.out.println(k);
    }

    private static void ubahKontak() {
        int id = readInt("Masukkan ID kontak: ");
        Kontak found = service.findById(id);
        if (found == null) {
            System.out.println("Kontak tidak ditemukan.");
            return;
        }
        String namaBaru = readNonEmptyString("Nama baru (enter untuk tetap): ", true);
        String nomorBaru = readValidPhone("Nomor baru (enter untuk tetap): ", true);
        if (namaBaru.isEmpty()) namaBaru = found.getNama();
        if (nomorBaru.isEmpty()) nomorBaru = found.getNomor();
        service.updateKontak(id, namaBaru, nomorBaru);
        System.out.println("Kontak berhasil diubah.");
    }

    private static void hapusKontak() {
        int id = readInt("Masukkan ID kontak: ");
        if (service.deleteKontak(id)) {
            System.out.println("Kontak berhasil dihapus.");
        } else {
            System.out.println("Kontak tidak ditemukan.");
        }
    }

    private static void cariKontak() {
        String q = readNonEmptyString("Masukkan nama: ");
        List<Kontak> hasil = service.searchByName(q);
        if (hasil.isEmpty()) {
            System.out.println("Tidak ada kontak ditemukan.");
        } else {
            for (Kontak k : hasil) System.out.println(k);
        }
    }

    // Utility
    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Input harus angka.");
            }
        }
    }

    private static String readNonEmptyString(String prompt) {
        return readNonEmptyString(prompt, false);
    }

    private static String readNonEmptyString(String prompt, boolean allowEmpty) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine();
            if (allowEmpty && line.trim().isEmpty()) return "";
            if (!line.trim().isEmpty()) return line.trim();
            System.out.println("Input tidak boleh kosong.");
        }
    }

    private static String readValidPhone(String prompt) {
        return readValidPhone(prompt, false);
    }

    private static String readValidPhone(String prompt, boolean allowEmpty) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            if (allowEmpty && line.isEmpty()) return "";
            if (line.matches("\\d{6,}")) return line;
            System.out.println("Nomor tidak valid (minimal 6 digit angka).");
        }
    }
}
