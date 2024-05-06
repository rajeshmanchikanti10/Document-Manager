package com.phonepe.pheonepeMC.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DocumentUserRelation {
    private String documentId;
    private String userMail;
    private String userType;
    public static DocumentUserRelation toCreatDocumentUserRelation(Document document,String userType){
        return DocumentUserRelation.builder()
                .documentId(document.getDocumentId())
                .userMail(document.getCreatedBy())
                .userType(userType)
                .build();
    }
}
