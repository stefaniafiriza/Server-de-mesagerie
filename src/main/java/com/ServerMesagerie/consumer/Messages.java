package com.ServerMesagerie.consumer;

public class Messages {

    private String destination;
    private String publicationData;
    private String textMessage;

    public Messages () {

    }

    public Messages(String destination, String textMessage) {
        this.destination = destination;
        this.textMessage = textMessage;
        this.publicationData = java.time.LocalDate.now().toString();
    }

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String text) {
        textMessage = text;
    }

    public String getPublicationData() {
        return publicationData;
    }

    public String getDestination() {
        return destination;
    }

    @Override
    public String toString() {
        return "Message [destination = " + destination + ", publication data = " + publicationData + ", text message = " + textMessage + "]";
    }
}
