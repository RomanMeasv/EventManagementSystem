package ems.bll.util;

import ems.gui.view.util.PopUp;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MailSender {
    public String getOutlook() {
        try {
            Process p = Runtime.getRuntime()
                    .exec(new String[]{"cmd.exe", "/c", "assoc", ".pst"});
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String extensionType = input.readLine();
            input.close();
            // extract type
            if (extensionType == null) {
                outlookNotFoundMessage("File type PST not associated with Outlook.");
            } else {
                String[] fileType = extensionType.split("=");

                p = Runtime.getRuntime().exec(
                        new String[]{"cmd.exe", "/c", "ftype", fileType[1]});
                input = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String fileAssociation = input.readLine();
                // extract path
                Pattern pattern = Pattern.compile(".*?");
                Matcher m = pattern.matcher(fileAssociation);
                if (m.find()) {
                    String outlookPath = m.group(0);
                    System.out.println(outlookPath);
                    return outlookPath;
                } else {
                    outlookNotFoundMessage("Error parsing PST file association");
                }
            }

        } catch (Exception err) {
            err.printStackTrace();
            outlookNotFoundMessage(err.getMessage());
        }
        return null;
    }

    private static void outlookNotFoundMessage(String errorMessage) {
        System.out.println("Could not find Outlook: \n" + errorMessage);
    }

    public void openOutlook()
    {
        String outlook = getOutlook();
        System.out.println(outlook);
        Runtime rt = Runtime.getRuntime();

        try {

            String attachment = "";
            String subject = "Ticket%20Email"; //%20 is used in place of a space
            String email = "cchesberg@gmail.com"; //participant.getEmail();
            String emailSubjectCombined = email+"?subject="+subject;
            File file = new File(attachment);
            rt.exec(new String[]{"cmd.exe","/c", outlook, "/m", emailSubjectCombined});

        } catch (IOException e) {
            // TODO Auto-generated catch block
            PopUp.showError(e.getMessage());
        }
    }
}
