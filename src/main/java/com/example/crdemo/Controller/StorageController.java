package com.example.crdemo.Controller;

        import com.google.cloud.storage.*;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RestController;

        import java.io.IOException;

@RestController
@RequestMapping("/data")
public class StorageController {

    @GetMapping("/empdata")
    public String getempdata() throws IOException {
        Storage storage = StorageOptions.newBuilder()
                .build()
                .getService();
        // Define the bucket and object names
        String bucketName = "pmtmbucket001";
        String objectName = "extdata/newempdata.json";

        // Get the blob from the bucket
        BlobId blobId = BlobId.of(bucketName, objectName);
        Blob blob = storage.get(blobId);

        // Read the content of the blob
        byte[] content = blob.getContent();
        System.out.println(new String(content));
        return new String(content);
    }
}
