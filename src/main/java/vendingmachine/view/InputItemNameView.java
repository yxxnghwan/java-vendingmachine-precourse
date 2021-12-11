package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;
import vendingmachine.Application;
import vendingmachine.domain.Item;
import vendingmachine.util.PublicConst;
import vendingmachine.util.SystemMessage;

public class InputItemNameView implements View {

	@Override
	public void show() {
		int money = Application.controller.getMoney();
		printMoneyAndMessage(money);
		if(!canPurchase(money)) {
			// 잔돈 반환 뷰 이동
			return;
		}

		String itemName  = readItemName();
		Item item;
		try {
			item = getItem(itemName);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			show();
			return;
		}
		Application.controller.purchase(item);
		show();
	}

	private void printMoneyAndMessage(int money) {
		System.out.println(SystemMessage.SHOW_INPUT_MONEY + money + PublicConst.MONETARY_UNIT);
		System.out.println(SystemMessage.INPUT_ITEM_NAME);
	}

	private String readItemName() {
		return Console.readLine().replaceAll(PublicConst.BLANK_REGEX, PublicConst.EMPTY_STRING);
	}

	private Item getItem(String itemName) {
		Item item = Application.controller.searchItem(itemName);
		if(item == null) {
			throw new IllegalArgumentException(SystemMessage.ERROR_NOT_EXIST_ITEM);
		}
		if(!item.isInStock())
			throw new IllegalArgumentException(SystemMessage.ERROR_IS_NOT_IN_STOCK);

		return item;
	}

	private boolean canPurchase(int money) {
		return Application.controller.canPurchaseByMoney(money);
	}
}
