package com.phonepe.pheonepeMC.repository;

import com.phonepe.pheonepeMC.models.DocumentHistory;

public interface DocumentHistoryRepo {

    DocumentHistory getDocument(String id);
    DocumentHistory createDocumentVersion(String id,DocumentHistory documentHistory);
}
