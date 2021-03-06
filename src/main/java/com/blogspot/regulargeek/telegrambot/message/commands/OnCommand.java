package com.blogspot.regulargeek.telegrambot.message.commands;

import com.blogspot.regulargeek.telegrambot.message.ItemCommand;
import com.blogspot.regulargeek.telegrambot.message.TelegramCommand;
import org.telegram.telegrambots.api.methods.send.SendMessage;

public class OnCommand extends TelegramCommand implements ItemCommand {

    private String itemName;

    public OnCommand(SendMessage message, String itemName) {
        super(message);
        this.itemName = itemName;
    }

    @Override
    public String getItemName() {
        return itemName;
    }
}
