package com.example.app.services;

import static com.example.app.utils.Configuration.generateRandomCode;

import android.app.Activity;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app.utils.Configuration;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class EmailService extends AppCompatActivity {

    String randomCode = "";
    final String emailOrigin = Configuration.VERIFICATION_EMAIL;
    final String passwordOrigin = Configuration.VERIFICATION_PASSWORD;

public String sendEmail(String mailTo, Activity activity){
    randomCode = generateRandomCode();

    String messageToSend = "Hola, el código de autenticación es: " + randomCode;

    Properties props = new Properties();

    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");

    Session session = Session.getInstance(props,new javax.mail.Authenticator(){
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(emailOrigin, passwordOrigin);
        }
    });


    try{
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(emailOrigin));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailTo));
        message.setSubject("Enviando Email");
        message.setText(messageToSend);
        Transport.send(message);
        Toast.makeText(activity,"Email enviado correctamente",Toast.LENGTH_LONG).show();


    }catch (MessagingException e){
        throw new RuntimeException(e);
    }

    return randomCode;

}

}