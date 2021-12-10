package vendingmachine.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vendingmachine.domain.Coin;
import vendingmachine.domain.Item;
import vendingmachine.domain.service.CoinService;
import vendingmachine.domain.service.ItemService;
import vendingmachine.view.InputFirstMoneyView;
import vendingmachine.view.InputItemInfoView;
import vendingmachine.view.ShowFirstMoneyView;
import vendingmachine.view.View;

public class VendingMachineController {
	private final Map<ViewMappingKey, View> viewMapper = new HashMap<>();
	private final CoinService coinService = new CoinService();
	private final ItemService itemService = new ItemService();

	public VendingMachineController() {
		viewMapper.put(ViewMappingKey.INPUT_FIRST_MONEY, new InputFirstMoneyView());
		viewMapper.put(ViewMappingKey.SHOW_FIRST_MONEY, new ShowFirstMoneyView());
		viewMapper.put(ViewMappingKey.INPUT_ITEM_INFO, new InputItemInfoView());
	}

	public void view(ViewMappingKey key) {
		viewMapper.get(key).show();
	}

	public void createFirstCoins(int firstMoney) {
		coinService.createFirstCoins(firstMoney);
	}

	public Map<Coin, Integer> getCoins() {
		return coinService.getCoins();
	}

	public void addItems(List<Item> items) {
		itemService.addItems(items);
	}
}
