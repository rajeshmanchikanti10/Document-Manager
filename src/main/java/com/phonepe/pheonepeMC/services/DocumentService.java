package com.phonepe.pheonepeMC.services;

import com.phonepe.pheonepeMC.dtos.DocumentRequest;
import com.phonepe.pheonepeMC.enums.AccessType;
import com.phonepe.pheonepeMC.enums.Status;
import com.phonepe.pheonepeMC.exceptions.DocumentException;
import com.phonepe.pheonepeMC.exceptions.LoginException;
import com.phonepe.pheonepeMC.models.Document;
import com.phonepe.pheonepeMC.models.DocumentHistory;
import com.phonepe.pheonepeMC.models.DocumentUserRelation;
import com.phonepe.pheonepeMC.repository.DocumentHistoryRepo;
import com.phonepe.pheonepeMC.repository.DocumentRepo;
import com.phonepe.pheonepeMC.repository.DocumentUserRepoImp;
import com.phonepe.pheonepeMC.utils.LoginUtil;
import org.springframework.beans.factory.annotation.Autowired;

import static java.util.Objects.isNull;

public class DocumentService {
    DocumentRepo documentRepo;
    DocumentHistoryRepo documentHistoryRepo;
    DocumentUserRepoImp documentUserRepoImp;

    @Autowired
    private LoginUtil loginUtil;

    public DocumentService(DocumentRepo documentRepo, DocumentHistoryRepo documentHistoryRepo, DocumentUserRepoImp documentUserRepoImp) {
        this.documentRepo = documentRepo;
        this.documentHistoryRepo = documentHistoryRepo;
        this.documentUserRepoImp = documentUserRepoImp;

    }

    public DocumentRequest createDocument(DocumentRequest documentRequest,String loginKey) throws LoginException {
        loginUtil.validateKey(loginKey);
        Document document = DocumentRequest.createDocumentDto(documentRequest);
        document.setVersion(1);
        documentRepo.createDocument(document);
        documentHistoryRepo.createDocumentVersion(String.format("%s_%d", document.getDocumentId(), 1), DocumentHistory.toDocumentHistory(document));
        documentUserRepoImp.addDocumentUser(String.format("%s_%s", document.getDocumentId(), document.getCreatedBy()), DocumentUserRelation.toCreatDocumentUserRelation(document, AccessType.EDITOR.toString()));
        return DocumentRequest.toDocumentRequest(document);

    }

    public DocumentRequest updateDocument(DocumentRequest documentRequest,String loginKey) throws DocumentException, LoginException {
        loginUtil.validateKey(loginKey);
        Document document = documentRepo.getDocument(documentRequest.getDocumentId());
        DocumentUserRelation documentUserRelation = documentUserRepoImp.getDocumentUser(String.format("%s_%s", document.getDocumentId(), document.getCreatedBy()));
        if (!isNull(document) && !document.getStatus().equals(Status.INACTIVE) && !isNull(documentUserRelation) && !documentUserRelation.getUserType().equals(AccessType.VIEW.toString())) {
            document.setVersion(document.getVersion() + 1);
            documentRepo.updateDocument(document);
            documentHistoryRepo.createDocumentVersion(String.format("%s_%d", document.getDocumentId(), document.getVersion()), DocumentHistory.toDocumentHistory(document));
        } else
            throw new DocumentException(documentRequest.getDocumentId(), "Invalid Update");
        return DocumentRequest.toDocumentRequest(document);


    }

    public String deleteDocument(String documentId,String loginKey) throws DocumentException, LoginException {
        loginUtil.validateKey(loginKey);
        Document document = (documentRepo.getDocument(documentId));
        if (isNull(document)) {
            throw new DocumentException(documentId, "Document Not Found");
        }
        document.setStatus(Status.INACTIVE);

        return String.format("Soft deleted %s successfully", documentRepo.deleteDocument(documentId, document));

    }

    public String revertDocument(String documentId, Integer version, String mail,String loginKey) throws DocumentException, LoginException {
        loginUtil.validateKey(loginKey);
        Document document = documentRepo.getDocument(documentId);
        DocumentUserRelation documentUserRelation = documentUserRepoImp.getDocumentUser(String.format("%s_%s", document.getDocumentId(), mail));
        DocumentHistory documentHistory = documentHistoryRepo.getDocument(String.format("%s_%s", documentId, version));
        if (!isNull(document) && !document.getStatus().equals(Status.INACTIVE) && !isNull(documentUserRelation) && !documentUserRelation.getUserType().equals(AccessType.VIEW.toString())) {
            Document reverteddocument = DocumentHistory.toDocument(documentHistory);
            reverteddocument.setDocName(document.getDocName());
            reverteddocument.setVersion(document.getVersion() + 1);
            documentRepo.updateDocument(reverteddocument);
            documentHistoryRepo.createDocumentVersion(String.format("%s_%s", documentId, reverteddocument.getVersion()), DocumentHistory.toDocumentHistory(reverteddocument));
        } else {
            throw new DocumentException(documentId, String.format("Failed to revert to %s_by %s", version, mail));
        }
        return String.format("reverted document %s successfully", documentId);
    }


}
