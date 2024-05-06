package com.phonepe.pheonepeMC.dtos;

import com.phonepe.pheonepeMC.enums.Status;
import com.phonepe.pheonepeMC.models.AuditEntity;
import com.phonepe.pheonepeMC.models.Document;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DocumentRequest extends AuditEntity {
    private String documentId;
    private String DocName;
    private String data;
    private Integer version;
    private Status status;
    public  DocumentRequest(String DocName,String data){
        this.DocName = DocName;
        this.data = data;
    }

    public static Document createDocumentDto(DocumentRequest documentRequest) {
        Document document =  Document.builder()
                .documentId(String.format("DOC%s", UUID.randomUUID().toString()))
                .data(documentRequest.getData())
                .docName(documentRequest.getDocName())
                .status(Status.ACTIVE)
                .version(documentRequest.getVersion())
                .build();
        document.setCreatedAt(Instant.now());
        document.setUpdatedAt(Instant.now());
        document.setCreatedBy(document.getCreatedBy());
        document.setUpdatedBy(documentRequest.getUpdatedBy());
        return document;
    }
    public static Document updateToDocumentDto(DocumentRequest documentRequest) {
        Document document = Document.builder()
                .documentId(documentRequest.getDocumentId())
                .data(documentRequest.getData())
                .docName(documentRequest.getDocName())
                .status(documentRequest.getStatus())
                .version(documentRequest.getVersion())
                .build();
        document.setUpdatedBy(documentRequest.getUpdatedBy());
        document.setCreatedBy(documentRequest.getCreatedBy());
        document.setUpdatedAt(documentRequest.getUpdatedAt());
        document.setCreatedAt(documentRequest.getCreatedAt());

        return document;
    }
    public static DocumentRequest toDocumentRequest(Document document) {
        DocumentRequest documentRequest = DocumentRequest.builder()
                .data(document.getData())
                .DocName(document.getDocName())
                .version(document.getVersion())
                .status(document.getStatus())
                .build();

        documentRequest.setUpdatedBy(document.getUpdatedBy());
        documentRequest.setCreatedBy(document.getCreatedBy());
        documentRequest.setUpdatedAt(document.getUpdatedAt());
        documentRequest.setCreatedAt(document.getCreatedAt());

        return documentRequest;
    }
}
