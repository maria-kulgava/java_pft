package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 6/27/2016.
 */
public class ContactDataGenerator {

  @Parameter(names = "-c", description = "Contact count")
  public int count;

  @Parameter (names = "-f", description = "Target file")
  public String file;

  @Parameter (names = "-d", description = "Data format")
  public String format;

  public  static void main(String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try{
      jCommander.parse(args);
    }catch (ParameterException ex){
      jCommander.usage(); // выводим на консоль текст, который содержит инфу о доступных опциях
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<ContactData> contacts = generateContacts(count);
    if (format.equals("csv")) {
      saveAsCsv(contacts, new File(file));
    } else if (format.equals("xml")) {
      saveAsXml(contacts, new File(file));
    } else {
      System.out.println("Unrecognized format: " + format);
    }
  }

  private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
    XStream xstream = new XStream();
    xstream.processAnnotations(ContactData.class);
    String xml = xstream.toXML(contacts);
    try(Writer writer = new FileWriter(file)){
      writer.write(xml);
    }
  }

  private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
    System.out.println(new File(".").getAbsolutePath());
    try(Writer writer = new FileWriter(file)){
      for(ContactData contact: contacts){
        writer.write(String.format("%s;%s;%s;%s;%s\n", contact.getFirstname(), contact.getLastname(), contact.getMobilePhone(), contact.getEmail(), contact.getGroup()));
      }
    }
  }

  private List<ContactData> generateContacts(int count) {
    List<ContactData> contacts = new ArrayList<ContactData>();
    for(int i = 0; i < count; i++){
// краткая информация о контакте
//      contacts.add(new ContactData().setFirstname(String.format("Firstname %s", i)).setLastname(String.format("Lastname %s", i))
//              .setMobilePhone(String.format("MobilePhone %s", i)).setEmail(String.format("Email %s", i)).setGroup("test 1"));

      contacts.add(new ContactData().setFirstname(String.format("Firstname %s", i)).setLastname(String.format("Lastname %s", i))
              .setHomePhone(String.format("HomePhone %s", i)).setMobilePhone(String.format("MobilePhone %s", i)).setWorkPhone(String.format("WorkPhone %s", i))
              .setAddress(String.format("Address %s", i)).setEmail(String.format("Email %s", i)).setEmail2(String.format("Email2 %s", i)).setEmail3(String.format("Email3 %s", i))
              .setGroup("test 1").setPhoto(new File("src/test/resources/smil1.png")));
    }
    return contacts;
  }

  }
