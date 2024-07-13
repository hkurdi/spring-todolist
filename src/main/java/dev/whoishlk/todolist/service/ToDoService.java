package dev.whoishlk.todolist.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.whoishlk.todolist.model.ToDoModel;
import dev.whoishlk.todolist.repo.ToDoRepo;

@Service
public class ToDoService {

	@Autowired
	ToDoRepo repo;

	public List<ToDoModel> getAllToDoItems() {
		ArrayList<ToDoModel> todoList = new ArrayList<>();
		repo.findAll().forEach(todo -> todoList.add(todo));
		return todoList;
	}

	public ToDoModel getToDoItemById(Long id) {
		return repo.findById(id).get();
	}

	public boolean updateStatus(Long id) {
		ToDoModel todo = getToDoItemById(id);
		todo.setStatus("Completed");

		return saveOrUpdateToDoItem(todo);
	}

	public boolean saveOrUpdateToDoItem(ToDoModel todo) {
		ToDoModel updatedToDo = repo.save(todo);

		if (getToDoItemById(updatedToDo.getID()) != null) {
			return true;
		}
		return false;
	}

	public boolean deleteToDoItem(Long id) {
		repo.deleteById(id);
		if (getToDoItemById(id) == null) {
			return true;
		}
		return false;
	}

}
