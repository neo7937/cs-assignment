package com.cs.assignment.service;

import com.cs.assignment.dto.ServerLogDto;
import com.cs.assignment.model.ServerLog;
import com.cs.assignment.repository.ServerLogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class LogService {

    @Autowired
    private ServerLogRepository logRepository;

    public void saveServerLogs (List<ServerLogDto> logDtoList) {

        log.info("Number of records to be saved is [{}]", logDtoList.size());

        List<ServerLog> logList= convertDtoToEntity(logDtoList);
        logRepository.saveAll(logList);
        findAllServerLogs();
    }

    private List<ServerLog> convertDtoToEntity(List<ServerLogDto> logDtoList) {
        List<ServerLog> result = new ArrayList<>(logDtoList.size());

        for(ServerLogDto dto : logDtoList) {
            ServerLog serverLog = new ServerLog(dto.getId(), dto.getType(), dto.getHost(), dto.isAlert(), dto.getDuration());
            result.add(serverLog);
            log.info("Record to be saved [{}].",serverLog);
        }
        return result;
    }

    public void findAllServerLogs() {
        log.info("retrieving all logs...");
        List<ServerLog> logList = (List<ServerLog>) logRepository.findAll();

        logList.forEach(e-> log.info("log from db =",e));
    }
}
