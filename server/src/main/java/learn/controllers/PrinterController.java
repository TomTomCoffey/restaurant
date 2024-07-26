package learn.controllers;

import org.springframework.web.bind.annotation.*;

import javax.print.*;
import java.io.ByteArrayInputStream;
import java.util.Arrays;

@RestController
//@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/printer")
public class PrinterController {


    @GetMapping
    public String[] getPrinters() {
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
        String[] printerNames = new String[printServices.length];

        for (int i = 0; i < printServices.length; i++) {
            printerNames[i] = printServices[i].getName();
        }

        return printerNames; ////this is how im gonna find the printer names for tony's
    }

    @PostMapping
    public String printText( @RequestBody String text) {
        /////so im gonna change the request body to the Order and make a nice order tostring method so itll print nice on the recept
        String printerName = getPrinters()[0]; ///just grabbing the first printer because I onbly own one printer

        try {
            PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
            PrintService printService = null;

            for (PrintService service : printServices) {
                if (service.getName().equalsIgnoreCase(printerName)) {
                    printService = service;
                    break;
                }
            }

            if (printService != null) {
                DocPrintJob job = printService.createPrintJob();
                ByteArrayInputStream textStream = new ByteArrayInputStream(text.getBytes());
                Doc doc = new SimpleDoc(textStream, DocFlavor.INPUT_STREAM.AUTOSENSE, null);
                job.print(doc, null);
                return "Print job completed successfully!";
            } else {
                return "Printer not found!";
            }
        } catch (PrintException e) {
            e.printStackTrace();
            return "Printing failed: " + e.getMessage();
        }
    }




}
