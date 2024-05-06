package com.phonepe.pheonepeMC.repository;

import com.phonepe.pheonepeMC.models.Document;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface DocumentRepo {

    Document createDocument(Document document);

    Document updateDocument(Document document);

    Document getDocument(String documentId);
    String deleteDocument(String documenId,Document document);

}
