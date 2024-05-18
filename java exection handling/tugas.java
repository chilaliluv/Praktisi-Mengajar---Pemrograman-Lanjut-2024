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
     public void setHari(String nama) {
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

    public void setNIK(String nIK) {
        NIK = nIK;
    }
    
    public String getRiwayatAt(int index) {
        if(index < 0 || index >= riwayatPenyakit.size()) {
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
        in = sc ; 
    }

    public void tambahAntrian(Pasien p) {
        antrianPasien.add(p);
        System.out.println("Pasien dengan nama " + p.getNama() + " sudah tertambah ke dalam antrian");
    }

    public void layaniPasien() {
        
        if(antrianPasien.size() == 0) {
            System.out.println("Antrian kosong");
            return;
        }
        Pasien p = antrianPasien.get(0);
        pasienTerlayani.add(p);
        antrianPasien.remove(0);
        System.out.println("Pasien dengan nama " + p.getNama() + " sudah terlayani");
    }

    public void displayAntrian() {
        final int BORDER = 40;
        System.out.println("=".repeat(BORDER));
        System.out.println("Antrian Rumah Sakit ");
        System.out.println("-".repeat(BORDER));
        for(Pasien p : antrianPasien) {
            System.out.println(p);
            System.out.println("-".repeat(BORDER));
        }
    }

    public void displayTerlayani() {
        final int BORDER = 40;
        System.out.println("=".repeat(BORDER));
        System.out.println("Pasien Terlayani");
        System.out.println("-".repeat(BORDER));
        for(Pasien p : pasienTerlayani) {
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
                System.out.println("Masukkan umur pasiem :");
                umur = in.nextInt();
                in.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Input tidak valid, silakan coba lagi.");
                in.nextLine(); 
            }
        }
        Pasien p = new Pasien( Hari, nama, nik, umur);        

        while(true) {
            System.out.println("Tinggalkan kosong apabila tidak ada (enter)");
            System.out.print("Masukkan riwayat penyakit : ");
            
            String riwayat = in.nextLine();

            if(riwayat.isEmpty()) {
                break;
            }

            p.addRiwayat(riwayat);
        }

        return p;

        } catch (InputMismatchException e) {
            System.out.println("Input tidak valid, silakan di coba lagi");
            in.nextLine();
            return null;
        }
        
    }
}

public class tugas {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        SistemRumahSakit srs = new SistemRumahSakit(in);   

        while(true) { 
            try {
                       System.out.println("1. Tambah Antrian");
            System.out.println("2. Layani Antrian");
            System.out.println("3. Lihat  Antrian");
            System.out.println("4. Keluar Program");

            System.out.print("Masukkan pilihan : ");

            char choice = in.nextLine().charAt(0);

            switch(choice) {
                case '1' :
                    Pasien p = srs.buatPasienInfo();
                    srs.tambahAntrian(p);
                    break;
                case '2' :
                    srs.layaniPasien();
                    break;
                case '3' :
                    srs.displayAntrian();
                    break;
                default: 
                    System.out.println("Terimakasih sudah menggunakan sistem rumah sakit");
            }
            } catch (Exception e) {
                 System.out.println("Terjadi kesalahan" + e.getMessage());
            in.nextLine();
            
            }
     
        }
    }
}

