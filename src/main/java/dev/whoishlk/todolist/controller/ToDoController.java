package dev.whoishlk.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dev.whoishlk.todolist.model.ToDoModel;
import dev.whoishlk.todolist.service.ToDoService;

@Controller
public class ToDoController {

	@Autowired
	private ToDoService service;
	
	@GetMapping({"/", "viewToDoList"})
	public String viewAllToDoItems(Model model, @ModelAttribute("message") String message) {
		model.addAttribute("list", service.getAllToDoItems());
		model.addAttribute("msg", message);
		return "ViewToDoList";
		
	}

	@PostMapping("/updateToDoStatus/{id}")
	public String updateAllToDoItems(@PathVariable Long id, RedirectAttributes redirectAttributes) {
		if (service.updateStatus(id)) {
			redirectAttributes.addFlashAttribute("message", "Successfully updated.");
			return "redirect:/viewToDoList";
		} 
		redirectAttributes.addFlashAttribute("message", "Update failed.");
		return "redirect:/viewToDoList";
	}

	@GetMapping("/addToDoItem")
	public String addToDoItem(Model model) {
		model.addAttribute("todo", new ToDoModel());
		return "AddToDoItem";
	}

	@PostMapping("/saveToDoItem")
	public String saveToDoItem(ToDoModel todo, RedirectAttributes redirectAttributes) {
		if(service.saveOrUpdateToDoItem(todo)) {
			redirectAttributes.addFlashAttribute("message", "Successfully saved.");
			return "redirect:/viewToDoList";
		}
		redirectAttributes.addFlashAttribute("message", "Save failed.");
		return "redirect:/addToDoItem";
	}

	@GetMapping("/editToDoItem/{id}")
	public String editToDoItem(@PathVariable Long id, Model model) {
		model.addAttribute("todo", service.getToDoItemById(id));
		return "EditToDoItem";
	}

	@PostMapping("/editSaveToDoItem")
	public String editSaveToDoItem(ToDoModel todo, RedirectAttributes redirectAttributes) {
		if(service.saveOrUpdateToDoItem(todo)) {
			redirectAttributes.addFlashAttribute("message", "Successfully edited.");
			return "redirect:/viewToDoList";
		}
		redirectAttributes.addFlashAttribute("message", "Edit failed.");
		return "redirect:/editToDoItem/" + todo.getID();
	}

	@GetMapping("/deleteToDoItem/{id}")
	public String deleteEditedToDoItem(@PathVariable Long id, RedirectAttributes redirectAttributes) {
		if (service.deleteToDoItem(id) ) {
			redirectAttributes.addFlashAttribute("message", "Successfully deleted.");
		}
		redirectAttributes.addFlashAttribute("message", "Deletion failed.");
		return "redirect:/viewToDoList";
	}

}
