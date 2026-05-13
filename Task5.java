package com.shop;

public class Task5 {
    private static final Logger log = LoggerFactory.getLogger(DocumentValidator.class);
    public ValidationResult validate(Document doc) {
        try {
            if (doc == null) {
                throw new RuntimeException("Document is null");
            }
            String content = doc.extractContent();
            if (content.isEmpty()) {
                throw new RuntimeException("Empty content");
            }
            return runValidationRules(content);

        } catch (Exception e) {
           //FIX issue 1 : replace e.printStackTrace() with logging
            log.error("Unexpected error during validation", e);
            return null;                            // issue 2
        }
    }

    public void validateBatch(List<Document> docs) {
        for (Document doc : docs) {
            try {
                ValidationResult r = validate(doc);
                //FIX Issue 3:Null check added to prevent NPE
                if (r!=null && r.isValid()) {                  // issue 3
                    saveResult(r);
                }
            } catch (Exception e) {
                //FIX silent — swallowed completely    // issue 4
                log.error("Failed to process document in batch", e);
            }
        }
    }

}
