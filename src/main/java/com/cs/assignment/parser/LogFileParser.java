package com.cs.assignment.parser;

import com.cs.assignment.dto.ServerLogDto;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Slf4j
public class LogFileParser {

    private static long MAX_DURATION = 0L;
    private static String LONGEST_EVENT_ID = "";

    public static List<ServerLogDto> parseFile(String fileName) {

        log.info("Input file name is [{}]", fileName);

        Map<String, ServerLogDto> logMap = new HashMap<>();

        try (Scanner scanner = new Scanner(new File(fileName))) {

            while (scanner.hasNext()) {

                String line = scanner.nextLine();
                if (line != null) {
                    JSONObject json = new JSONObject(line);
                    String id = json.getString("id");
                    String state = json.getString("state");
                    long timestamp = json.getLong("timestamp");
                    String host = !line.contains("host") ? "Nil" : json.getString("host");
                    String type = !line.contains("type") ? "Nil" : json.getString("type");

                    if (!logMap.containsKey(id)) {
                        logMap.put(id, new ServerLogDto(id, state, type, host, timestamp, false, 0));
                    } else {
                        ServerLogDto oldLog = logMap.get(id);
                        long duration = Math.abs(timestamp - oldLog.getTimestamp());
                        if (duration > 4) {
                            oldLog.setAlert(true);
                            log.info("Duration [{}]ms greater 4ms for ID [{}].", duration, id);
                        }

                        if(duration > MAX_DURATION) {
                            MAX_DURATION = duration;
                            LONGEST_EVENT_ID = id;
                        }
                        oldLog.setDuration(duration);
                    }
                    log.info("Parsed record -> [{}]", logMap.get(id));

                }
            }
            log.info("Longest event with id [{}] is [{}]ms", LONGEST_EVENT_ID, MAX_DURATION);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>(logMap.values());
    }

}
