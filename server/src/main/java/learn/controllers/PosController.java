package learn.controllers;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
//@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/pos/printer")
public class PosController {


    @PostMapping("/print")
    public ResponseEntity<String> printOrder(@RequestBody String order){
        String printerUrl = "http://printer-ip/WebPRNT/commands";

        // Prepare the ESC/POS commands
        String printData = generatePrintData();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "text/plain");
        HttpEntity<String> entity = new HttpEntity<>(printData, headers);

        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.exchange(printerUrl, HttpMethod.POST, entity, String.class);
    }
    private String generatePrintData() {
        // Generate your ESC/POS formatted print data here
        StringBuilder sb = new StringBuilder();
        sb.append((char) 0x1B); // ESC
        sb.append("@");         // Initialize printer
        sb.append("Hello World!"); // Your print data
        sb.append((char) 0x0A); // Newline
        sb.append((char) 0x1D); // GS
        sb.append("V");         // Cut
        sb.append((char) 0x00); // Full cut
        return sb.toString();
    }

}
