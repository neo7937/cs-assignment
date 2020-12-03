package com.cs.assignment.repository;

import com.cs.assignment.model.ServerLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServerLogRepository extends CrudRepository<ServerLog, String> {
}
