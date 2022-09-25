package me.huynx.testjsonserialize;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"SenderReference", "MessageIdentifier", "Format", "Signature"})
public class NapasDocumentHeader {

    @JsonProperty("SenderReference")
    protected String senderReference;

    @JsonProperty("MessageIdentifier")
    protected String messageIdentifier;

    @JsonProperty("Format")
    protected String format;

    @JsonProperty("Signature")
    protected String signature;

    public String getSenderReference() {
        return senderReference;
    }

    public void setSenderReference(String value) {
        this.senderReference = value;
    }

    public String getMessageIdentifier() {
        return messageIdentifier;
    }

    public void setMessageIdentifier(String value) {
        this.messageIdentifier = value;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

}
