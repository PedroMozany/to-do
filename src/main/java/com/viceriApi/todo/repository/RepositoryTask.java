package com.viceriApi.todo.repository;

import com.viceriApi.todo.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface RepositoryTask extends JpaRepository<Task, Long> {

}
