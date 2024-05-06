package com.phonepe.pheonepeMC.exceptions;

public class DocumentException extends Exception{
    String documentId;
    public DocumentException(String documentId,String message){
        super(String.format("documentId -%s %s",documentId,message));
        this.documentId = documentId;

    }
}
