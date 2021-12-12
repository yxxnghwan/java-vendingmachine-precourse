package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;
import vendingmachine.Application;
import vendingmachine.controller.ViewMappingKey;
import vendingmachine.util.MoneyValidator;
import vendingmachine.util.PublicConst;
import vendingmachine.util.SystemMessage;

public class InputFirstMoneyView implements View {
	@Override
	public void flow() {
		int firstMoney;
		try {
			firstMoney = readFirstMoney();
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			reshow();
			return;
		}
		createFirstCoins(firstMoney);
		goShowFirstMoney();
	}

	@Override
	public void printViewMessage() {
		System.out.println(SystemMessage.INPUT_FIRST_MONEY);
	}

	private int readFirstMoney() {
		String firstMoneyStr = Console.readLine().replaceAll(PublicConst.BLANK_REGEX, PublicConst.EMPTY_STRING);
		return MoneyValidator.validate(firstMoneyStr);
	}

	private void createFirstCoins(int firstMoney) {
		Application.controller.createFirstCoins(firstMoney);
	}

	private void goShowFirstMoney() {
		Application.controller.view(ViewMappingKey.SHOW_FIRST_MONEY);
	}

	private void reshow() {
		Application.controller.view(ViewMappingKey.INPUT_FIRST_MONEY);
	}
}
