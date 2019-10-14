package com.altran.rh.exercicio.ecommerce.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.altran.rh.exercicio.ecommerce.model.Item;
import com.altran.rh.exercicio.ecommerce.service.ItemService;

@Controller
@RequestMapping(path = "/api/v1/items")
public class ItemController {

	@Autowired
	private ItemService itemService;

	@PostMapping()
	public @ResponseBody Item addItem(@RequestBody Item item) {
		return itemService.create(item);
	}

	@GetMapping()
	public @ResponseBody List<Item> getAllItems() {
		return itemService.getAll();
	}

	@GetMapping("/{id}")
	public @ResponseBody Item getById(@PathVariable String id) {
		return itemService.getById(id);
	}

	@PutMapping("/{id}")
	public @ResponseBody Item updateById(@PathVariable String id, @RequestBody Item item) {
		return itemService.updateById(id, item);
	}

	@DeleteMapping("/{id}")
	public @ResponseBody void removeById(@PathVariable String id) {
		itemService.removeById(id);
	}

}