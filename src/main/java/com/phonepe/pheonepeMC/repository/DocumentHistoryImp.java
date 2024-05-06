package com.phonepe.pheonepeMC.repository;


import com.phonepe.pheonepeMC.models.Document;
import com.phonepe.pheonepeMC.models.DocumentHistory;

import java.util.HashMap;

public class DocumentHistoryImp implements DocumentHistoryRepo {
    HashMap<String, DocumentHistory> history= new HashMap<>();
    @Override
    public DocumentHistory getDocument(String id) {
        return history.get((id));
    }

    @Override
    public DocumentHistory createDocumentVersion(String id,DocumentHistory documentHistory) {
        history.put(id,documentHistory);
        return history.get(id);
    }
}
