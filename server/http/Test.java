import java.io.OutputStream;
import java.net.Socket;

public class Test {
    public static void main(String[] args) {
        String printerIp = "192.168.0.12";
        int port = 9100;

        try (Socket socket = new Socket(printerIp, port);
                OutputStream out = socket.getOutputStream()) {

            // ESC/POS command to initialize the printer
            out.write(new byte[] { 0x1B, 0x40 });
            // Print data
            out.write("Hello World!\n".getBytes("UTF-8"));
            // ESC/POS command to cut the paper
            out.write(new byte[] { 0x1D, 0x56, 0x00 });

            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
