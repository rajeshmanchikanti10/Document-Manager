package com.phonepe.pheonepeMC.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DocumentHistory extends AuditEntity{

    private String documentId;
    private String data;
    private Integer version;
    public static  DocumentHistory toDocumentHistory(Document document){
       return DocumentHistory.builder()
                .documentId(document.getDocumentId())
                .data(document.getData())
                .version(document.getVersion())
                .build();
    }
    public static  Document toDocument(DocumentHistory documentHistory){
        return Document.builder()
                .docName(documentHistory.getData())
                .documentId(documentHistory.getDocumentId())
                .build();
    }
}
