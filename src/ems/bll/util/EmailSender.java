package ems.bll.util;

import ems.be.Ticket;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailSender {
    private static String getOutlookPath() throws Exception{
        Process p = Runtime.getRuntime()
                .exec(new String[]{"cmd.exe", "/c", "assoc", ".pst"});
        BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String extensionType = input.readLine();
        input.close();
        // extract type
        if (extensionType == null) {
            throw new IOException("Could not find extension type!");
        } else {
            String[] fileType = extensionType.split("=");

            p = Runtime.getRuntime().exec(
                    new String[]{"cmd.exe", "/c", "ftype", fileType[1]});
            input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String fileAssociation = input.readLine();
            // extract path
            Pattern pattern = Pattern.compile("\".*?\"");
            Matcher m = pattern.matcher(fileAssociation);
            if (m.find()) {
                return m.group(0);
            } else {
                throw new IOException("Could not find outlook path!");
            }
        }
    }

    public static void sendTicket(String recipient, Ticket ticket, File ticketFile) throws Exception {
        String outlookPath = getOutlookPath();

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
