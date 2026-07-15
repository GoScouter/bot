package io.github.goscouter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.goscouter.config.Config;
import io.github.goscouter.listeners.ReadyListener;
import io.github.goscouter.listeners.WelcomeListener;
import lombok.SneakyThrows;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class BotLoader {

    public static final Logger logger = LogManager.getLogger(BotLoader.class);
    private static final String CONFIG_FILE = "config.json";

    @SneakyThrows
    public static void main(String[] args) {
        final ObjectMapper mapper = new ObjectMapper();
        Config config = mapper.readValue(new File(CONFIG_FILE), Config.class);

        JDA jda = JDABuilder.createDefault(config.discordToken())
                .enableIntents(GatewayIntent.GUILD_MEMBERS)
                .addEventListeners(new ReadyListener(config), new WelcomeListener(config))
                .build();

        jda.awaitReady();
    }

}