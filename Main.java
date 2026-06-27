
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) throws IOException {
    Scanner sc = new Scanner(System.in);
    for (;;) {
      System.out.print("\nПуть: ");
      String s = sc.nextLine();
      Methods m = new Methods(s);
      System.out.println("\n1) Скан\n2) Дубликаты\n3) Бэкап\n4) Выход");
      int ch = sc.nextInt();
      sc.nextLine();
      if (ch == 4)
        break;
      if (ch == 1)
        m.scanDirectory();
      if (ch == 2)
        m.findDuplicates();
      if (ch == 3) {
        System.out.print("Путь бэкапа: ");
        m.verifyBackup(Path.of(sc.nextLine()));
      }
    }
  }
}
