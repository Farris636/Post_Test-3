# Post_Test-3
Nama: Moch. Farris Alfiansyah Nim: 2409116079

# Sistem Catatan Kontak

## Deskripsi Program
Program **Sistem Catatan Kontak** adalah aplikasi berbasis console yang digunakan untuk mencatat daftar kontak.  
Fitur utama:
- Tambah kontak (Create)
- Lihat semua kontak (Read)
- Ubah kontak (Update)
- Hapus kontak (Delete)
- Sortir kontak A–Z
- Cari kontak berdasarkan nama
- Validasi input (nama tidak boleh kosong, nomor telepon hanya angka)

Arsitektur menggunakan konsep **MVC sederhana**:
- `model` → struktur data
- `service` → logika CRUD
- `main` → menu interaksi user

## Struktur Project
<img width="269" height="235" alt="image" src="https://github.com/user-attachments/assets/6ab7c165-631c-4740-95ec-1712030588d4" />


Struktur project **Catatan_Kontak** dibagi menjadi tiga package utama, yaitu `Main`, `Models`, dan `Services`. Berikut penjelasan singkatnya:

 **Main**
  - Berisi file `Main.java` yang menjadi entry point dari program. Di sinilah menu utama ditampilkan, input dari pengguna dibaca, dan perintah CRUD dijalankan dengan memanggil service.

**Models**
- Berisi class yang merepresentasikan data kontak.
  - `Kontak.java` → superclass utama yang menyimpan atribut dasar (id, nama, nomor).
  - `Keluarga.java` → subclass dari `Kontak` untuk menyimpan kontak dengan kategori keluarga.
  - `Teman.java` → subclass dari `Kontak` untuk menyimpan kontak dengan kategori teman.
- Package ini menunjukkan penerapan **encapsulation** dan **inheritance**.

**Services**
- Berisi `KontakService.java` yang menampung logika utama aplikasi seperti CRUD, pencarian, dan sorting.
- Package ini bertindak sebagai penghubung antara data (`Models`) dan tampilan menu (`Main`).

Dengan struktur ini, kode menjadi lebih rapi, terorganisir, dan sesuai dengan prinsip **pemrograman berorientasi objek (OOP)**.  


## Alur Program
1. Program dijalankan → tampil menu utama.
2. User memilih menu (Tambah, Lihat, Ubah, Hapus, Cari, Sortir, Keluar).
3. Program memproses pilihan menggunakan method di `KontakService`.
4. Hasil operasi ditampilkan di layar.
5. Program kembali ke menu utama sampai user memilih **Keluar**.



---

## Penjelasan Kode

### 1. `Kontak.java`
```java
public class Kontak {
    private int id;
    private String nama;
    private String nomor;

    public Kontak(int id, String nama, String nomor) {
        this.id = id;
        this.nama = nama;
        this.nomor = nomor;
    }

    public int getId() { return id; }
    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }
    public String getNomor() { return nomor; }
    public void setNomor(String nomor) { this.nomor = nomor; }
}
```

Penjelasan:

1. Kontak adalah class model untuk menyimpan data kontak.
2. Properti:
   - id → identitas unik kontak
   - nama → nama orang
   - nomor → nomor telepon
3. Constructor digunakan untuk inisialisasi data.
4. Access modifier private melindungi data agar hanya bisa diakses melalui getter dan setter.

```java
import java.util.ArrayList;
import java.util.Scanner;

public class KontakService {
    private ArrayList<Kontak> daftarKontak = new ArrayList<>();
    private int idCounter = 1;
    private Scanner scanner = new Scanner(System.in);

    public void tambahKontak() {
        System.out.print("Masukkan nama: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan nomor: ");
        String nomor = scanner.nextLine();

        Kontak kontak = new Kontak(idCounter++, nama, nomor);
        daftarKontak.add(kontak);
        System.out.println("Kontak berhasil ditambahkan!");
    }

    public void lihatKontak() {
        if (daftarKontak.isEmpty()) {
            System.out.println("Tidak ada kontak.");
        } else {
            for (Kontak k : daftarKontak) {
                System.out.println(k.getId() + ". " + k.getNama() + " - " + k.getNomor());
            }
        }
    }
}
```
Penjelasan:

1. KontakService mengatur seluruh logika CRUD.
2. daftarKontak → menyimpan semua kontak dalam ArrayList.
3. idCounter → memberikan ID otomatis setiap kontak baru.
4. tambahKontak() → meminta input user, membuat objek kontak baru, lalu menyimpannya.
5. lihatKontak() → menampilkan seluruh daftar kontak atau pesan jika kosong.

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        KontakService service = new KontakService();
        Scanner scanner = new Scanner(System.in);
        int pilihan;

        do {
            System.out.println("\n=== MENU KONTAK ===");
            System.out.println("1. Tambah Kontak");
            System.out.println("2. Lihat Kontak");
            System.out.println("3. Keluar");
            System.out.print("Pilih menu: ");
            pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    service.tambahKontak();
                    break;
                case 2:
                    service.lihatKontak();
                    break;
                case 3:
                    System.out.println("Keluar dari program...");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 3);
    }
}
```
Penjelasan:

1. Main adalah class utama program.
2. do-while memastikan menu selalu tampil sampai user memilih keluar.
3. switch-case mengeksekusi fitur sesuai pilihan menu.
4. Program berhenti hanya jika user memilih opsi 3 (Keluar).

## Output 

<img width="373" height="238" alt="image" src="https://github.com/user-attachments/assets/8d52322b-fae7-4391-99f5-848e05afbe7c" />

Tampilan dari awal program dijalankan.

### Menu 1
<img width="566" height="130" alt="image" src="https://github.com/user-attachments/assets/7efdb5e3-a8c7-4703-aea3-3a41eede4dc4" />

Tampilan dari menu 1 yaitu menambah kontak dengan memasukkan nama dan nomor handphone.

### Menu 2
<img width="238" height="78" alt="image" src="https://github.com/user-attachments/assets/747e45a4-3f5d-4dc4-9a20-eff374fd2ad0" />

Tampilan dari menu 2 yaitu untuk melihat nama kontak yang sudah dimasukkan sebelumnya.

### Menu 3
<img width="461" height="171" alt="image" src="https://github.com/user-attachments/assets/010f7e50-926b-4a0b-b689-f2de9c8ff8a8" />

Tampilan dari menu 3 yaitu mengubah kontak sesuai dengan ID nya.

### Menu 4
<img width="513" height="149" alt="image" src="https://github.com/user-attachments/assets/1a80d10c-7dad-4afc-8ea8-d976a5230a66" />

Tampilan dari menu 4 yaitu menghapus kontak sesuai dengan ID nya.

### Menu 5
<img width="268" height="175" alt="image" src="https://github.com/user-attachments/assets/76377b93-6c10-4b79-94e4-6ec517210f46" />

Tampilan sebelum menggunakan menu 5 yaitu sortir sesuai abjad yang dimulai dari huruf A.

<img width="371" height="494" alt="image" src="https://github.com/user-attachments/assets/c1ab179b-ebfe-4560-b183-7c80913276b1" />

Tampilan setelah menggunakan sortir sesuai abjad.

### Menu 6
<img width="447" height="125" alt="image" src="https://github.com/user-attachments/assets/76e28c7b-5684-41f3-b099-6a309d7a34ad" />

Tampilan dari menu 6 yaitu untuk mencari Kontak dengan menggunakan Nama yang ditambahkan.

### Menu 7
<img width="377" height="51" alt="image" src="https://github.com/user-attachments/assets/7bda1802-cd13-45b2-aa76-d89e51000dd4" />

Tampilan dari menu 7 yaitu keluar dari program.












