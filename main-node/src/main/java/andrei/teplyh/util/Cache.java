package andrei.teplyh.util;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Data
@Component
public class Cache {
    private Map<String, CacheData> adminsCache = new HashMap<>();
}
