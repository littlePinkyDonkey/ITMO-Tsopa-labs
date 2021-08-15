package andrei.teplyh.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserNotificationDto {
    private String notificationBody;
    private Timestamp dateOfSending;
    private String sender;
    private String revisionResult;
    private long reviewId;
}
