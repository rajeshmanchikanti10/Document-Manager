package com.phonepe.pheonepeMC.repository;

import com.phonepe.pheonepeMC.models.Document;

import java.util.HashMap;

public class DocumentRepoImpl implements DocumentRepo {
    HashMap<String, Document> documents = new HashMap<>();

    @Override
    public Document createDocument(Document document) {
        documents.put(document.getDocumentId(), document);
        return documents.get(document.getDocumentId());
    }

    @Override
    public Document updateDocument(Document document) {
        documents.put(document.getDocumentId(), document);
        return documents.get(document.getDocumentId());

    }

    @Override
    public Document getDocument(String documentId) {
        return documents.get(documentId);
    }

    @Override
    public String deleteDocument(String documentId,Document document) {
        documents.put(documentId,document);
        return documentId;
    }


}
