
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Stream;

public class Methods {
  Path p;

  public Methods(String s) {
    p = Paths.get(s);
  }

  public void scanDirectory() throws IOException {
    try (Stream<Path> w = Files.walk(p)) {
      w.forEach(x -> {
        try {
          long sz = Files.isDirectory(x) ? 0 : Files.size(x);
          System.out.printf("%s - %d bytes%n", x.toAbsolutePath(), sz);
        } catch (IOException e) {
        }
      });
    }
  }

  public void findDuplicates() throws IOException {
    List<Path> list;
    try (Stream<Path> w = Files.walk(p)) {
      list = w.filter(Files::isRegularFile).toList();
    }
    for (int i = 0; i < list.size(); i++) {
      for (int j = i + 1; j < list.size(); j++) {
        Path f1 = list.get(i);
        Path f2 = list.get(j);
        if (Files.size(f1) == Files.size(f2) && Files.mismatch(f1, f2) == -1) {
          System.out.println("Дубликат: " + f1 + " и " + f2);
        }
      }
    }
  }

  public void verifyBackup(Path bkp) throws IOException {
    try (Stream<Path> w = Files.walk(p)) {
      w.filter(Files::isRegularFile).forEach(f -> {
        Path rel = p.relativize(f);
        Path bf = bkp.resolve(rel);
        if (!Files.exists(bf)) {
          System.out.println("Отсутствует: " + rel);
        } else {
          try {
            if (Files.size(f) != Files.size(bf)) {
              System.out.println("Разный размер: " + rel);
            }
          } catch (IOException e) {
          }
        }
      });
    }
  }
}
