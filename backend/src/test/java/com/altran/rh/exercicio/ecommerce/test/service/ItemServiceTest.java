package com.altran.rh.exercicio.ecommerce.test.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.altran.rh.exercicio.ecommerce.exception.BadRequestException;
import com.altran.rh.exercicio.ecommerce.exception.BusinessException;
import com.altran.rh.exercicio.ecommerce.exception.NotFoundException;
import com.altran.rh.exercicio.ecommerce.model.Item;
import com.altran.rh.exercicio.ecommerce.repository.ItemRepository;
import com.altran.rh.exercicio.ecommerce.service.ItemService;

@RunWith(SpringRunner.class)
public class ItemServiceTest {

	static final String DEFAULT_ITEM_ID = "a59d3c03a9f34747e33aas53";

	@TestConfiguration
	static class UserServiceIntegrationTestContextConfiguratio {

		@Bean
		public ItemService itemService() {
			return new ItemService();
		}
	}

	@Autowired
	private ItemService itemService;

	@MockBean
	private ItemRepository itemRepository;

	Item item;

	@Before
	public void setUp() {
		item = new Item();
		item.setId(DEFAULT_ITEM_ID);
		item.setName("Product One");
		item.setValue(new Float(30.56));

		List<Item> itemList = new ArrayList<>();
		itemList.add(item);

		Mockito.when(itemRepository.findById(DEFAULT_ITEM_ID)).thenReturn(Optional.of(item));
		Mockito.when(itemRepository.save(item)).thenReturn(item);
		Mockito.when(itemRepository.findAll()).thenReturn(itemList);
		ItemRepository itemRepository2 = Mockito.mock(ItemRepository.class);
		doNothing().when(itemRepository2).deleteById(DEFAULT_ITEM_ID);
	}

	@Test
	public void whenValidIdThenItemShouldBeFound() {
		String name = "Product One";
		Item found = itemService.getById(DEFAULT_ITEM_ID);
		assertThat(found.getName()).isEqualTo(name);
	}

	@Test(expected = BusinessException.class)
	public void whenInvalidIdThenExceptionShouldBeThrow() {
		itemService.getById("any-inexistent-id");
	}

	@Test
	public void whenUpdateByIdThenUserShouldBeFound() {
		String name = "Test name";
		item.setName(name);
		Item updated = itemService.updateById(DEFAULT_ITEM_ID, item);
		assertThat(updated.getName()).isEqualTo(name);
	}

	@Test
	public void whenRemoveByIdThenUserShouldNotBeFound() {
		itemService.removeById(DEFAULT_ITEM_ID);
	}

	@Test
	public void whenFindAllThenUserShouldBeFound() {
		assertThat(itemService.getAll().get(0).getName()).isEqualTo(item.getName());
	}

	@Test
	public void whenCreateThenItemShouldBeFound() {
		assertThat(itemService.create(item)).isEqualTo(item);
	}

	@Test(expected = NotFoundException.class)
	public void whenUpdateWithInvalidIdThenExceptionShouldBeThrow() {
		itemService.updateById("any-inexistent-id", item);
	}

	@Test(expected = BadRequestException.class)
	public void whenCreateWithZeroValueOrLessThenItemShouldBeFound() {
		item.setValue(0f);
		itemService.create(item);
	}

}
