package andrei.teplyh.entities.enums;

import java.util.stream.Stream;

public enum FeedbackStatuses {
    ON_REVISION,
    ACCEPTED,
    REJECTED,
    EDITS_NEEDED;

    public static FeedbackStatuses of(String value) {
        return Stream.of(FeedbackStatuses.values())
                .filter(statuses -> statuses.toString().equals(value))
                .findFirst()
                .orElse(null);
    }
}
