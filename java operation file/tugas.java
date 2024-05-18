import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class Pasien {
    private String Hari;
    private String nama;
    private String NIK;
    private int umur;
    private ArrayList<String> riwayatPenyakit;

    public Pasien(String Hari, String nama, String NIK, int umur) {
        this.Hari = Hari;
        this.nama = nama;
        this.NIK = NIK;
        this.umur = umur;
        riwayatPenyakit = new ArrayList<>();
    }

    public String getHari() {
        return Hari;
    }

    public void setHari(String Hari) {
        this.Hari = Hari;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNIK() {
        return NIK;
    }

    public void setNIK(String NIK) {
        this.NIK = NIK;
    }

    public int getUmur() {
        return umur;
    }

    public void setUmur(int umur) {
        this.umur = umur;
    }

    public String getRiwayatAt(int index) {
        if (index < 0 || index >= riwayatPenyakit.size()) {
            return "";
        }
        return riwayatPenyakit.get(index);
    }

    public void addRiwayat(String riwayat) {
        riwayatPenyakit.add(riwayat);
    }

    @Override
    public String toString() {
        return String.format(
            """
            Hari             : %s
            Nama             : %s        
            NIK              : %s
            Umur             : %d
            Riwayat Penyakit : %s
            """,
            Hari, nama, NIK, umur, 
            riwayatPenyakit.toString()
        );
    }
}

class SistemRumahSakit {
    private ArrayList<Pasien> antrianPasien;
    private ArrayList<Pasien> pasienTerlayani;
    private Scanner in;

    public SistemRumahSakit(Scanner sc) {
        antrianPasien = new ArrayList<>();
        pasienTerlayani = new ArrayList<>();
        in = sc;
        loadAntrianFromFile();
        loadTerlayaniFromFile();
    }

    private static final String BERKAS_ANTRIAN = "antrian.txt";
    private static final String BERKAS_TERLAYANI = "terlayani.txt";

    public void tambahAntrian(Pasien p) {
        antrianPasien.add(p);
        System.out.println("Pasien dengan nama " + p.getNama() + " sudah tertambah ke dalam antrian");
        saveAntrianToFile();
    }

    public void layaniPasien() {
        if (antrianPasien.isEmpty()) {
            System.out.println("Antrian kosong");
            return;
        }
        Pasien p = antrianPasien.remove(0);
        pasienTerlayani.add(p);
        System.out.println("Pasien dengan nama " + p.getNama() + " sudah terlayani");
        saveAntrianToFile();
        saveTerlayaniToFile();
    }

    public void displayAntrian() {
        final int BORDER = 40;
        System.out.println("=".repeat(BORDER));
        System.out.println("Antrian Rumah Sakit ");
        System.out.println("-".repeat(BORDER));
        for (Pasien p : antrianPasien) {
            System.out.println(p);
            System.out.println("-".repeat(BORDER));
        }
    }

    public void displayTerlayani() {
        final int BORDER = 40;
        System.out.println("=".repeat(BORDER));
        System.out.println("Pasien Terlayani");
        System.out.println("-".repeat(BORDER));
        for (Pasien p : pasienTerlayani) {
            System.out.println(p);
            System.out.println("-".repeat(BORDER));
        }
    }

    public Pasien buatPasienInfo() {
        try {
            System.out.print("Masukkan tanggal: ");
            String Hari = in.nextLine();
            System.out.print("Masukkan nama pasien : ");
            String nama = in.nextLine();
            System.out.print("Masukkan NIK pasien : ");
            String nik = in.nextLine();

            int umur = 0;
            while (true) {
                try {
                    System.out.print("Masukkan umur pasien : ");
                    umur = in.nextInt();
                    in.nextLine();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Input tidak valid, silakan coba lagi.");
                    in.nextLine();
                }
            }
            Pasien p = new Pasien(Hari, nama, nik, umur);

            while (true) {
                System.out.println("Tinggalkan kosong apabila tidak ada (enter)");
                System.out.print("Masukkan riwayat penyakit : ");
                String riwayat = in.nextLine();
                if (riwayat.isEmpty()) {
                    break;
                }
                p.addRiwayat(riwayat);
            }
            return p;

        } catch (InputMismatchException e) {
            System.out.println("Input tidak valid, silakan coba lagi");
            in.nextLine();
            return null;
        }
    }

    private void saveAntrianToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(BERKAS_ANTRIAN))) {
            for (Pasien p : antrianPasien) {
                pw.println(p.getHari() + "," + p.getNama() + "," + p.getNIK() + "," + p.getUmur() );
            }
        } catch (IOException e) {
            System.out.println("Gagal menyimpan antrian: " + e.getMessage());
        }
    }

    private void saveTerlayaniToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(BERKAS_TERLAYANI))) {
            for (Pasien p : pasienTerlayani) {
                pw.println(p.getHari() + "," + p.getNama() + "," + p.getNIK() + "," + p.getUmur() );
            }
        } catch (IOException e) {
            System.out.println("Gagal menyimpan pasien terlayani: " + e.getMessage());
        }
    }

    private void loadAntrianFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(BERKAS_ANTRIAN))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 5) continue;
                Pasien p = new Pasien(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]));
                String[] riwayats = parts[4].split(";");
                for (String riwayat : riwayats) {
                    p.addRiwayat(riwayat);
                }
                antrianPasien.add(p);
            }
        } catch (IOException e) {
            System.out.println("Gagal memuat antrian: " + e.getMessage());
        }
    }

    private void loadTerlayaniFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(BERKAS_TERLAYANI))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 5) continue;
                Pasien p = new Pasien(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]));
                String[] riwayats = parts[4].split(";");
                for (String riwayat : riwayats) {
                    p.addRiwayat(riwayat);
                }
                pasienTerlayani.add(p);
            }
        } catch (IOException e) {
            System.out.println("Gagal memuat pasien terlayani: " + e.getMessage());
        }
    }
}

public class tugas {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        SistemRumahSakit srs = new SistemRumahSakit(in);

        while (true) {
            try {
                System.out.println("1. Tambah Antrian");
                System.out.println("2. Layani Antrian");
                System.out.println("3. Lihat Antrian");
                System.out.println("4. Keluar Program");

                System.out.print("Masukkan pilihan : ");

                char choice = in.nextLine().charAt(0);

                switch (choice) {
                    case '1':
                        Pasien p = srs.buatPasienInfo();
                        if (p != null) {
                            srs.tambahAntrian(p);
                        }
                        break;
                    case '2':
                        srs.layaniPasien();
                        break;
                    case '3':
                        srs.displayAntrian();
                        break;
                    case '4':
                        System.out.println("Keluar program...");
                        return;
                    default:
                        System.out.println("Pilihan tidak valid");
                }
            } catch (InputMismatchException e) {
                System.out.println("Pilihan tidak valid");
                in.nextLine();
            }
        }
    }
}