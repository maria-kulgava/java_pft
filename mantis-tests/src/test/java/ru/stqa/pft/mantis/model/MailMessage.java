package ru.stqa.pft.mantis.model;

/**
 * Created by Admin on 7/16/2016.
 */
public class MailMessage {

  public String to;
  public String text;

  public MailMessage(String to, String text) {
    this.to = to;
    this.text = text;
  }

}
