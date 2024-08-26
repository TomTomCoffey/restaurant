package learn.controllers;

import learn.models.Modifiers;
import learn.models.Order;
import learn.models.OrderItem;
import org.springframework.web.bind.annotation.*;

import javax.print.*;
import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
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
    public String printText( @RequestBody Order order) {

        if(order == null){
            return "Order cannot be null";
        }
        /////so im gonna change the request body to the Order and make a nice order tostring method so itll print nice on the recept
        String printerName = getPrinters()[0]; ///just grabbing the first printer because I onbly own one printer
        order.setOrderTime(LocalDateTime.now());
        String text = formatOrderReceipt(order);

        System.out.println(text);

        return("Happy panda");

//        try {
//            PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
//            PrintService printService = null;
//
//            for (PrintService service : printServices) {
//                if (service.getName().equalsIgnoreCase(printerName)) {
//                    printService = service;
//                    break;
//                }
//            }
//
//            if (printService != null) {
//                DocPrintJob job = printService.createPrintJob();
//                ByteArrayInputStream textStream = new ByteArrayInputStream(text.getBytes());
//                Doc doc = new SimpleDoc(textStream, DocFlavor.INPUT_STREAM.AUTOSENSE, null);
//                job.print(doc, null);
//                return "Print job completed successfully!";
//            } else {
//                return "Printer not found!";
//            }
//        } catch (PrintException e) {
//            e.printStackTrace();
//            return "Printing failed: " + e.getMessage();
//        }
    }

    public String formatOrderReceipt(Order order) {
        StringBuilder receipt = new StringBuilder();

        receipt.append("============================================================\n")
                .append("                         ORDER RECEIPT\n")
                .append("============================================================\n")
                .append("Customer: ").append(order.getUser().getLastName()).append("\n")
                .append("Order Time: ").append(order.getOrderTime().toString().replace("T", " ")).append("\n")
                .append("------------------------------------------------------------\n")
                .append("Items Ordered:\n")
                .append("------------------------------------------------------------\n");

        for (OrderItem item : order.getItems()) {
            receipt.append(String.format("%dx %-30s $%.2f\n", item.getQuantity(), item.getItem(), item.getTotal()));

            List<Modifiers> modifiers = item.getModifiers();
            if (modifiers != null && !modifiers.isEmpty()) {
                receipt.append("    Modifiers:\n");
                for (Modifiers modifier : modifiers) {
                    receipt.append(String.format("    • %-30s $%.2f\n", modifier.getName(), modifier.getPrice()));
                }
            } else {
                receipt.append("    - No Modifiers\n");
            }
        }

        receipt.append("------------------------------------------------------------\n")
                .append(String.format("Total Cost:%32s\n", String.format("$%.2f", order.getCost())))
                .append("============================================================\n")
                .append("Thank you for your order!\n")
                .append("============================================================\n");

        return receipt.toString();
    }




}
