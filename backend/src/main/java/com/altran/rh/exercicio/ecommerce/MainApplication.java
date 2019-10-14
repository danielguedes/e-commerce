package com.altran.rh.exercicio.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

//@Bean
//ApplicationRunner init(ItemRepository itemRepository, CartRepository cartRepository) {

//  Object[][] data = {
//      {"sea", "Andrew", 300.12, "NDK"},
//      {"creek", "Andrew", 100.75, "Piranha"},
//      {"loaner", "Andrew", 75, "Necky"}
//  };
//  
//  final User user;
//  final CartItem cartItems;
//  
//  Item item1 = new Item();
//  item1.setName("Item 1");
//  
//  Item item2 = new Item();
//  item2.setName("Item 2");
//  
//  itemRepository.save(item1);
//  return itemRepository.save(item2);
//  
//  return;

//  CartItem cartItem1 = new CartItem();
//  cartItem1.setItem();
//  cartItem1.setItem();

//  
//  final List<Cart> carts;
//  carts.add(new Cart("", user, ));
//
//  return args -> {
//    cartRepository
//        .deleteAll()
//        .thenMany(
//            Flux
//                .just(data)
//                .map(array -> {
//                  return new Cart((String) array[0], (String) array[1], (Number) array[2], (String) array[3]);
//                })
//                .flatMap(cartRepository::save)
//        )
//        .thenMany(cartRepository.findAll())
//        .subscribe(kayak -> System.out.println("saving " + kayak.toString()));
//
//  };
//}

//@Bean
//ApplicationRunner init(KayakRepository repository) {
//
//  Object[][] data = {
//      {"sea", "Andrew", 300.12, "NDK"},
//      {"creek", "Andrew", 100.75, "Piranha"},
//      {"loaner", "Andrew", 75, "Necky"}
//  };
//
//  return args -> {
//    repository
//        .deleteAll()
//        .thenMany(
//            Flux
//                .just(data)
//                .map(array -> {
//                  return new Kayak((String) array[0], (String) array[1], (Number) array[2], (String) array[3]);
//                })
//                .flatMap(repository::save)
//        )
//        .thenMany(repository.findAll())
//        .subscribe(kayak -> System.out.println("saving " + kayak.toString()));
//
//  };
//}
}
