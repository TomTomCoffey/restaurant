package learn.controllers;


import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import org.springframework.web.bind.annotation.*;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api/pos/printer")
public class PosController {

    @PostMapping("/print")
    public ResponseEntity<String> printOrder(@RequestBody String order) {
        String printerIp = "192.168.0.12"; /// current ip
        int port = 8000;

        try (Socket socket = new Socket(printerIp, port);
             OutputStream out = socket.getOutputStream()) {

            // Prepare the ESC/POS commands
            String printData = generatePrintData();
            out.write(printData.getBytes(StandardCharsets.UTF_8));
            out.flush();

            return ResponseEntity.ok("Printed successfully did it print?!?!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Printing failed: " + e.getMessage());
        }
    }

    private String generatePrintData() {
        StringBuilder sb = new StringBuilder();
        sb.append((char) 0x1B); // ESC
        sb.append("@");         // Initialize printer
        sb.append("Hello Tony!"); ///this is where i can start printing
        sb.append((char) 0x0A); // Newline
        sb.append((char) 0x1D); // GS
        sb.append("V");         // Cut
        sb.append((char) 0x00); // Full cut
        return sb.toString();
    }
}
