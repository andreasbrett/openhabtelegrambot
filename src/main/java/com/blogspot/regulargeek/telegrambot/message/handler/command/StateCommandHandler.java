package com.blogspot.regulargeek.telegrambot.message.handler.command;

import com.blogspot.regulargeek.telegrambot.message.commands.OnCommand;
import com.blogspot.regulargeek.telegrambot.message.commands.StateCommand;
import com.blogspot.regulargeek.telegrambot.message.handler.TelegramCommandHandler;
import model.ItemDTO;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.api.methods.send.SendMessage;

import java.util.Iterator;
import java.util.List;

@Service
public class StateCommandHandler extends TelegramCommandHandler<StateCommand> {


    @Override
    public void handle(StateCommand command) {
        SendMessage message = command.getSendMessage();
        String itemName = command.getItemName();

 		if (itemName.startsWith("*") || itemName.endsWith("*")) {
			List<ItemDTO> items = restApiService.getItems();
			message.setText(prepareResponseWildcard(items, itemName));
		} else {
        	ItemDTO itemDTO = restApiService.getItem(itemName);
        	message.setText(prepareResponse(itemDTO));
		}

        sendResponse(message);
    }

	private String prepareResponseWildcard(List<ItemDTO> items, String itemName) {
		Iterator<ItemDTO> i = items.iterator();
		if (itemName.startsWith("*")) {
			itemName = itemName.substring(1, itemName.length());
			while (i.hasNext()) {
				ItemDTO o = i.next();
				if (!o.getName().endsWith(itemName)) { i.remove(); }
			}
		} else {
			itemName = itemName.substring(0, itemName.length() - 1);
			while (i.hasNext()) {
				ItemDTO o = i.next();
				if (!o.getName().startsWith(itemName)) { i.remove(); }
			}
		}

		StringBuilder builder = new StringBuilder();
		items.stream().forEach(itemDTO ->
			builder.append("<b>")
					.append(itemDTO.getName())
					.append("</b> - ")
					.append(itemDTO.getLabel())
					.append("\n--> <i>")
					.append(itemDTO.getState())
					.append("</i>\n\n")
		);
		return builder.toString();
	}

    private String prepareResponse(ItemDTO itemDTO) {
        StringBuilder builder = new StringBuilder();
        builder.append("<b>");
        builder.append(itemDTO.getName());
        builder.append("</b> = <b>");
        builder.append(itemDTO.getState());
        builder.append("</b>");
        return builder.toString();
    }

}
