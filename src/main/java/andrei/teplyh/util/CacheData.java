package andrei.teplyh.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CacheData {
    private int temporaryFeedbacksCount;
    private String adminEmail;
}
