import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

class get_jreleaser {
  public static void main(String... args) throws Exception {
    if (args.length != 1) {
      System.err.println("Usage: java get_jreleaser.java VERSION");
      System.exit(1);
    }

    // TODO: handle version = 'latest'

    var version = args[0];
    var url = "https://github.com/jreleaser/jreleaser/releases/download/" + version + "/jreleaser-tool-provider-" + version + ".jar";
    var file = Path.of("jreleaser-cli.jar");

    try (var stream = new URL(url).openStream()) {
      System.out.printf("✅ Located JReleaser %s%n", version);
      System.out.printf("⬇️  Downloading %s%n", url);
      var size = Files.copy(stream, file, StandardCopyOption.REPLACE_EXISTING);
      System.out.printf("%s << copied %d bytes%n", file, size);
      System.out.printf("✅ JReleaser installed successfully%n");
    } catch(FileNotFoundException e) {
      System.out.printf("❌ JReleaser %s not found%n", version);
      System.exit(1);
    } catch(IOException e) {
      System.out.printf("☠️  JReleaser %s could not be downloaded/copied%n", version);
      e.printStackTrace();
      System.exit(1);
    }
  }
}
