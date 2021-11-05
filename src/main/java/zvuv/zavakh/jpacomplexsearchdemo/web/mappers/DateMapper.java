package zvuv.zavakh.jpacomplexsearchdemo.web.mappers;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Component
public class DateMapper {

    public OffsetDateTime timestampToOffsetDateTime(Timestamp timestamp) {
        if (timestamp != null) {
            return OffsetDateTime.of(
                    timestamp.toLocalDateTime().getYear(),
                    timestamp.toLocalDateTime().getMonthValue(),
                    timestamp.toLocalDateTime().getDayOfMonth(),
                    timestamp.toLocalDateTime().getHour(),
                    timestamp.toLocalDateTime().getMinute(),
                    timestamp.toLocalDateTime().getSecond(),
                    timestamp.toLocalDateTime().getNano(),
                    ZoneOffset.UTC
            );
        }

        return null;
    }

    public Timestamp offsetDateTimeToTimestamp(OffsetDateTime offsetDateTime) {
        if (offsetDateTime != null) {
            return Timestamp.valueOf(
                    offsetDateTime
                            .atZoneSameInstant(ZoneOffset.UTC)
                            .toLocalDateTime()
            );
        }

        return null;
    }
}
