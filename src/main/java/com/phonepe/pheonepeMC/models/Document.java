package com.phonepe.pheonepeMC.models;

import com.phonepe.pheonepeMC.enums.Status;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Document extends AuditEntity {
    private String documentId;
    private String docName;
    private String data;
    private Integer version;
    private Status status;


}
