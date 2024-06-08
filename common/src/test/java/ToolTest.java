import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.TypeReference;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class ToolTest {
    @Test
    public void test(){
        Object t = null;
        var jsonString = JSON.toJSONString(t, JSONWriter.Feature.WriteClassName).getBytes(StandardCharsets.UTF_8);
//        System.out.println(jsonString);
//        JSON.parseObject(jsonString, new TypeReference<Object>() {});
    }
}
