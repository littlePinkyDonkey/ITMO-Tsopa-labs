package andrei.teplyh.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CacheData {
    private Integer temporaryFeedbacksCount;
    private String adminEmail;
}
