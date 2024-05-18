import java.util.Scanner;
public class latihan {
    public static void main(String[] args) {
        try {
            String nama ;
            Scanner nm = new Scanner(System.in);

            System.out.print("Masukan Nama :");
            nama = nm.nextLine();
            if(nama.matches(".*\\d.*")){
                throw new IllegalArgumentException("nama tidak boleh angka");
            }
            System.out.println("nama yang dimasukan :" +  nama);

            nm.close();

        } catch (IllegalArgumentException e) {
            System.out.println("Data tidak valid");
        }
        
    }
}
