package ems.bll.util;

import ems.be.Ticket;

import java.io.File;
import java.io.IOException;

public class EmailSender {
    public static void sendTicket(String recipient, Ticket ticket, File ticketFile) throws IOException {
        String outlookPath = "C:\\Program Files\\Microsoft Office\\root\\Office16\\OUTLOOK.EXE";

        String subject = "Your ticket for event " + ticket.getEvent().getName();
        String body = "Dear " + ticket.getCustomer().getName() + ",\n" +
                "\nYour ticket for " + ticket.getEvent().getName() + " is attached to this email.\n" +
                "Make sure to save this ticket to be able to present it at the entrance.\n" +
                "\nWe hope that you will have a wonderful time!";
        String mString = (recipient + "?subject=" + subject + "&body=" + body).replace(" ", "%20").replace("\n", "%0A");

        String outlookCommand = " /c ipm.note /m \"" + mString + "\" /a \"" + ticketFile.getAbsolutePath() + "\"";
        //execute outlook
        Runtime.getRuntime().exec(outlookPath + outlookCommand);
    }
}
