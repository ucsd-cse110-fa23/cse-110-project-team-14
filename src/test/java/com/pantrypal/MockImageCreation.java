package com.pantrypal;

import java.io.IOException;
import java.net.URISyntaxException;

public class MockImageCreation extends ImageCreation {
    
    public static String generateImageURL(String prompt)  throws IOException, InterruptedException, URISyntaxException {
        return "https://oaidalleapiprodscus.blob.core.windows.net/private/org-IHlav8UaojB2abcqdO8mRLyN/user-ErXYzzy8BahPc4Al13wvczxy/img-76wKBb0ThmagtT74FHAlyiUc.png?st=2023-12-04T18%3A26%3A22Z&se=2023-12-04T20%3A26%3A22Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2023-12-03T22%3A44%3A01Z&ske=2023-12-04T22%3A44%3A01Z&sks=b&skv=2021-08-06&sig=2zQka8wpL7CtkON1ngUrOFLRyr4EXiaBHzzYidOqFv8%3D";
    }
}
