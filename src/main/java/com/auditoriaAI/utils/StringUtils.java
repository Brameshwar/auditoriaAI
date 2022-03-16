package com.auditoriaAI.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Slf4j
public class StringUtils {

    ThreadLocal<String> finalDataHolder = new InheritableThreadLocal<>();

    public String replaceAllOccurrences(String data, String strToReplace, String strToReplaceWith){
        Pattern pattern = Pattern.compile(strToReplace);
        Matcher matcher = pattern.matcher(data);

        if(null==finalDataHolder.get())
            finalDataHolder.set(new String());

        if(matcher.find()){
            finalDataHolder.set(data.replaceFirst(strToReplace,strToReplaceWith)) ;
            replaceAllOccurrences(finalDataHolder.get(), strToReplace, strToReplaceWith);
        }

        return finalDataHolder.get();
    }
}
