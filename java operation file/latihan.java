import java.io.*;

public class latihan {
    public static void main(String[] args) {
        readFileAndCopyContent();
        readFileAndPrintContent();
    }

    public static void readFileAndCopyContent() {
        int kar;
        try {
            FileInputStream fileInput = new FileInputStream("C:\\Users\\ASUS ZENBOOK\\Downloads\\tugas pemlan\\New folder\\java operation file\\lt.txt");
            FileOutputStream fileOutput = new FileOutputStream("C:\\Users\\ASUS ZENBOOK\\Downloads\\tugas pemlan\\New folder\\java operation file\\lt.txt");
            while ((kar = fileInput.read()) != -1) {
                fileOutput.write(kar);
            }
            fileInput.close();
            fileOutput.close();
        } catch (Exception e) {
            System.out.println("File Tidak Ada");
        }
    }

    public static void readFileAndPrintContent() {
        try {
            File f = new File("C:\\Users\\ASUS ZENBOOK\\Downloads\\tugas pemlan\\New folder\\java operation file\\lt.txt");
            FileReader reader = new FileReader(f);
            BufferedReader buff = new BufferedReader(reader);
            String line;
            while ((line = buff.readLine()) != null) {
                System.out.println(line);
            }
            buff.close();
            reader.close();
        } catch (Exception e) {
            System.out.println("File Tidak Ada");
        }
    }
}