import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

class copy {
  public static void main(String... args) throws Exception {
    if (args.length != 2) {
      System.err.println("Usage: java copy.java URL FILENAME");
      System.exit(1);
    }
    var url = args[0];
    try (var stream = new URL(url).openStream()) {
      var file = Path.of(args[1]);
      var size = Files.copy(stream, file, StandardCopyOption.REPLACE_EXISTING);
      System.out.printf("%s << copied %d bytes << %s%n", file, size, url);
    }
  }
}
