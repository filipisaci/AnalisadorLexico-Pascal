import java.io.BufferedReader;
import java.io.StringReader;
import java.io.IOException;

public class TesteBufferMark {

    public static void main(String[] args) throws IOException {

        BufferedReader r = new BufferedReader(new StringReader(
                "Happy Birthday to You!\n"
                + "Happy Birthday, dear " + System.getProperty("user.name") + "!"));
        r.mark(1000); // save the data we are about to read
        System.out.println(r.readLine()); // read the first line
        r.reset(); // jump back to the marked position
        r.mark(1000); // start saving the data again
        System.out.println(r.readLine()); // read the first line again
        System.out.println(r.readLine()); // read the second line
        r.reset(); // jump back to the marked position
        System.out.println(r.readLine()); // read the first line one final time

    }
}
