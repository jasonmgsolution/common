package jason.common.parser;

import org.json.JSONObject;

/**
 * Created by jasonmg_0302 on 2016-03-17.
 */
public class JsonParser {

    public static JsonResult parse(JSONObject jo) {
        JsonResult jr = new JsonResult();
        jr.result = getResult(jo);
        jr.comment = getComment(jo);
        jr.data = jo.opt("data");
        return jr;
    }

    public static boolean getResult(JSONObject jo) {
        return jo.optBoolean("result");
    }

    public static String getComment(JSONObject jo) {
        return jo.optString("comment");
    }


}
