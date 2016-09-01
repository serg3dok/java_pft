package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IEUser on 8/31/2016.
 */
public class ContactDataGenerator {

    @Parameter (names = "-c", description = "Group count")
    public int count;

    @Parameter (names = "-f", description = "Target file")
    public String file;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);

        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();

    }

    private void run() throws IOException {
        List<ContactData> contacts = generatecontacts(count);
        save(contacts, new File(file));
    }

    private static List<ContactData> generatecontacts(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData().withFirstName(String.format("firstname %s", i)).withLastname(String.format("lastname %s", i)).withAddress(String.format("address %s", i))
                    .withHomePhone(String.format("home phone %s", i)).withMobilePhone(String.format("mobile phone %s", i)).withWorkPhone(String.format("work phone %s", i))
                    .withEmail(String.format("email 1 %s", i)).withEmail2(String.format("email 2 %s", i)).withEmail3(String.format("email 3 %s", i)));
        }
        return contacts;
    }

    private static void save(List<ContactData> contacts, File file) throws IOException {
        //System.out.println(new File(".").getAbsoluteFile());
        Writer writer = new FileWriter(file);
        for (ContactData contact : contacts) {
            writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s\n", contact.getFirstName(), contact.getLastname(), contact.getAddress(),
                    contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone(),
                    contact.getEmail(), contact.getEmail2(), contact.getEmail3()));
        }
        writer.close();
    }
}
