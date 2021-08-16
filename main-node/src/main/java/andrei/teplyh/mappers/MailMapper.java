package andrei.teplyh.mappers;

import andrei.teplyh.dto.MailDto;
import andrei.teplyh.util.Cache;
import andrei.teplyh.util.CacheData;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Component
public class MailMapper {
    public List<MailDto> toDto(Cache cache) {
        List<MailDto> result = new ArrayList<>();

        Iterator<Map.Entry<String, CacheData>> iterator = cache.getAdminsCache().entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, CacheData> element = iterator.next();
            MailDto dto = new MailDto(element.getValue().getAdminEmail(),
                    element.getValue().getTemporaryFeedbacksCount().toString());
            result.add(dto);
        }
        return result;
    }
}
