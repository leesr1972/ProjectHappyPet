package sky.pro.java.course6.projecthappypet.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import sky.pro.java.course6.projecthappypet.listener.TelegramBotUpdatesListener;

@Configuration
public class TelegramBotConfiguration {
    @Bean
    public TelegramBotsApi telegramBotsApi(TelegramBotUpdatesListener bot) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(bot);
        return telegramBotsApi;
    }
}
