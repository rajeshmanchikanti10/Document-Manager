package com.phonepe.pheonepeMC.repository;

import com.phonepe.pheonepeMC.models.DocumentUserRelation;

import java.util.HashMap;

public class DocumentUserRepoImp {
    HashMap<String, DocumentUserRelation> documentUserMap = new HashMap<>();
    public DocumentUserRelation addDocumentUser(String id,DocumentUserRelation documentUserRelation){
        documentUserMap.put(id,documentUserRelation);
        return documentUserMap.get(id);
    }
    public DocumentUserRelation getDocumentUser(String id){
        return documentUserMap.get(id);
    }
}
