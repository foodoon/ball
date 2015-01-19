package guda.ball.util;

import org.apache.commons.lang.StringUtils;

import java.util.*;

/**
 * Created by foodoon on 2014/8/1.
 */
public class SignUtil {


    public static Map<String,String> convert2TreeMap(Map<String,String[]> paramMap){
        if(paramMap == null || paramMap.size() == 0){
            return Collections.emptyMap();
        }
        Map<String,String> map = new TreeMap<String,String>();
        Set<Map.Entry<String, String[]>> entries = paramMap.entrySet();
        Iterator<Map.Entry<String, String[]>> iterator = entries.iterator();
        while(iterator.hasNext()){
            Map.Entry<String, String[]> entry = iterator.next();
            String[] value = entry.getValue();
            if(value!=null&&value.length>0) {
                map.put(entry.getKey(), StringUtils.join(value,","));
            }else{
                map.put(entry.getKey(),"");
            }

        }
        return map;
    }

    public static Map<String,String> convert2TreeMap(Map<String,String[]> paramMap,String[] excludeParamName){
        if(paramMap == null || paramMap.size() == 0){
            return Collections.emptyMap();
        }
        Map<String,String> map = new TreeMap<String,String>();
        Set<Map.Entry<String, String[]>> entries = paramMap.entrySet();
        Iterator<Map.Entry<String, String[]>> iterator = entries.iterator();
        while(iterator.hasNext()){
            Map.Entry<String, String[]> entry = iterator.next();
            if(constants(excludeParamName,entry.getKey())){
                continue;
            }
            String[] value = entry.getValue();
            if(value!=null&&value.length>0) {
                map.put(entry.getKey(), StringUtils.join(value,","));
            }else{
                map.put(entry.getKey(),"");
            }

        }
        return map;
    }
    public static boolean constants(String[] excludeParamName,String key){
        if(key == null || excludeParamName == null){
            return false;
        }
        for(String s:excludeParamName){
            if(key.equalsIgnoreCase(s)){
                return true;
            }
        }
        return false;
    }

    public static String convert2Str(Map<String,String[]> paramMap,String[] excludeParamName){
        Map<String,String> p = convert2TreeMap(paramMap,excludeParamName);
        Iterator<Map.Entry<String, String>> iterator = p.entrySet().iterator();
        StringBuilder buf = new StringBuilder();
        int i=0;
        while(iterator.hasNext()){
            Map.Entry<String, String> next = iterator.next();
            if(i>0){
                buf.append("&");
            }
            buf.append(next.getKey()).append("=").append(next.getValue());
            ++i;
        }
        return buf.toString();
    }

}
