package com.pantrypal;

import java.io.IOException;
import java.net.URISyntaxException;

import com.pantrypal.model.ImageCreation;

public class MockImageCreation extends ImageCreation {
    
    public String generateImageURL(String prompt)  throws IOException, InterruptedException, URISyntaxException {
        if (prompt.equals("test")){
            return "https://oaidalleapiprodscus.blob.core.windows.net/private/org-IHlav8UaojB2abcqdO8mRLyN/user-ErXYzzy8BahPc4Al13wvczxy/img-76wKBb0ThmagtT74FHAlyiUc.png?st=2023-12-04T18%3A26%3A22Z&se=2023-12-04T20%3A26%3A22Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2023-12-03T22%3A44%3A01Z&ske=2023-12-04T22%3A44%3A01Z&sks=b&skv=2021-08-06&sig=2zQka8wpL7CtkON1ngUrOFLRyr4EXiaBHzzYidOqFv8%3D";
        }
        else if (prompt.equals("lab")){
            return "https://oaidalleapiprodscus.blob.core.windows.net/private/org-IHlav8UaojB2abcqdO8mRLyN/user-ErXYzzy8BahPc4Al13wvczxy/img-AERBuFQ6ZfPy3OAAIJicjJtu.png?st=2023-12-04T18%3A33%3A45Z&se=2023-12-04T20%3A33%3A45Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2023-12-03T22%3A48%3A09Z&ske=2023-12-04T22%3A48%3A09Z&sks=b&skv=2021-08-06&sig=frAQK%2BXdCSyxdD90Pi%2BVJsRIQNgDXc0t1TMO69Jnihw%3D";
        }
        else if (prompt.equals("chilaquiles")){
            return "https://oaidalleapiprodscus.blob.core.windows.net/private/org-IHlav8UaojB2abcqdO8mRLyN/user-ErXYzzy8BahPc4Al13wvczxy/img-yvoR7YVxVaXFlgv1Z8tTOyBz.png?st=2023-12-04T18%3A42%3A33Z&se=2023-12-04T20%3A42%3A33Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2023-12-03T22%3A41%3A12Z&ske=2023-12-04T22%3A41%3A12Z&sks=b&skv=2021-08-06&sig=FPN2oEvg3vwX5GuV71M41Lh6A%2Bg84RcCCCr5Fq695GA%3D";
        }
        return "https://oaidalleapiprodscus.blob.core.windows.net/private/org-IHlav8UaojB2abcqdO8mRLyN/user-ErXYzzy8BahPc4Al13wvczxy/img-yvoR7YVxVaXFlgv1Z8tTOyBz.png?st=2023-12-04T18%3A42%3A33Z&se=2023-12-04T20%3A42%3A33Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2023-12-03T22%3A41%3A12Z&ske=2023-12-04T22%3A41%3A12Z&sks=b&skv=2021-08-06&sig=FPN2oEvg3vwX5GuV71M41Lh6A%2Bg84RcCCCr5Fq695GA%3D";
    }
}
