import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class MyBot extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {
        // TODO


        if (update.hasMessage() && update.getMessage().hasText()) {

            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();
            ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
            List<KeyboardRow> keyboard = new ArrayList<>();

            if (message_text.equals("/start")) {
                SendMessage message = SendMessage
                        .builder()
                        .chatId(Long.toString(chat_id))
                        .text("Hi "+update.getMessage().getFrom().getFirstName())
                        .build();

                // Create the keyboard (list of keyboard rows)
                keyboard.add(createKeyboardRow("row1", 3));
                keyboard.add(createKeyboardRow("row2", 3));
                keyboard.add(createKeyboardRow("row3", 3));
                keyboardMarkup.setKeyboard(keyboard);

                // Add it to the message
                message.setReplyMarkup(keyboardMarkup);

                // Sending our message object to user
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }


            }
            // Selected button condition
            else if (message_text.equals("row1 button 1")) {
                SendMessage message1 = SendMessage
                        .builder()
                        .chatId(Long.toString(chat_id))
                        .text("selam "+update.getMessage().getFrom().getFirstName()+ ("you picked row1 button 1 "))
                        .build();
                //Creates class to remove custom keyboard
                ReplyKeyboardRemove keyboardRemove = new ReplyKeyboardRemove(true);
                //Adds markup to the message for removing the custom keyboard
                message1.setReplyMarkup(keyboardRemove);
                // Sending our message object to user
                try {
                    execute(message1);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //Method that creates custom keyboard
    private KeyboardRow createKeyboardRow(String rowName, Integer buttonNumber) {
        KeyboardRow row = new KeyboardRow();
        for (int i = 0; i < buttonNumber; i++) {
            row.add(rowName +" button "+ i);
        }
        return row;
    }

    @Override
    public String getBotUsername() {
        // TODO
        return "GeodataCollectorV1_Bot";
    }

    @Override
    public String getBotToken() {
        // TODO
        return "1748091780:AAHnpVnvtr1-7DEwuE3-ycb1kWyeGU1eqR4";
    }
}
