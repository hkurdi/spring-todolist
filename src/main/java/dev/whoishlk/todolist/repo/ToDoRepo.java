package dev.whoishlk.todolist.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.whoishlk.todolist.model.ToDoModel;

@Repository
public interface ToDoRepo extends JpaRepository<ToDoModel, Long> {

}
