package com.lotus.common.kit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

/**
 * 何人以大贪欲心，夺或令夺我诸财，自身受用三世善，回向于他佛子行。
 * 我虽无有些微错，何人若断吾头颅，然以悲心将彼罪，自身代受佛子行。
 * 有者百般中伤吾，恶名纵遍三千界，然我深怀慈爱心，赞其功德佛子行。
 * 何人大庭广众中，揭露吾过出恶语，于彼亦作上师想，恭敬顶礼佛子行。
 * <p>
 * 吾如自子爱护者，彼纵视我如怨敌，犹如慈母于病儿，尤为怜爱佛子行。
 * 与我等同或下士，虽以傲慢而凌辱，然吾敬其如上师，恒时顶戴佛子行。
 * <p>
 * 贫穷恒常受人欺，且为重疾恶魔逼，众生罪苦自代受，无有怯懦佛子行。
 * 美名远扬受人敬，亦获财如多闻子，然见世福无实义，毫无傲慢佛子行。
 */
public class JsonKit {

    private static Logger log = LoggerFactory.getLogger(MapKit.class);

    public static void jsonPrint(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            System.out.println(mapper.writeValueAsString(object));
        } catch (JsonProcessingException e) {
            System.out.println("Json转换失败");
        }
    }

    public static Map<String, Object> toMap(String json) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Map map = mapper.readValue(json, Map.class);
            return map;
        } catch (IOException e) {
            log.error("Json转换失败", ExceptionUtils.getStackTrace(e));
            return null;
        }
    }

    public static String toJson(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return (mapper.writeValueAsString(object));
        } catch (JsonProcessingException e) {
            log.error("Json转换失败", ExceptionUtils.getStackTrace(e));
            return null;
        }
    }
}
