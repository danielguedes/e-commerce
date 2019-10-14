package com.altran.rh.exercicio.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altran.rh.exercicio.ecommerce.exception.BadRequestException;
import com.altran.rh.exercicio.ecommerce.exception.NotFoundException;
import com.altran.rh.exercicio.ecommerce.model.Item;
import com.altran.rh.exercicio.ecommerce.repository.ItemRepository;

@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;

	public Item getById(String id) {
		return itemRepository.findById(id).orElseThrow(NotFoundException::new);
	}

	public List<Item> getAll() {
		return itemRepository.findAll();
	}

	public Item create(Item item) {
		if (item.getValue() <= 0f) {
			throw new BadRequestException();
		}
		normalize(item);
		return itemRepository.save(item);
	}

	private void normalize(Item item) {
		item.setName(item.getName().trim());
	}

	public Item updateById(String id, Item item) {
		if (!(itemRepository.findById(id).isPresent())) {
			throw new NotFoundException();
		}
		normalize(item);
		item.setId(id);
		return itemRepository.save(item);
	}

	public void removeById(String id) {
		itemRepository.deleteById(id);
	}
}
