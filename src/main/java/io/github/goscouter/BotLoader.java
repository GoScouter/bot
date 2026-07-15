package io.github.goscouter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.goscouter.config.Config;
import io.github.goscouter.listeners.ReadyListener;
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
                .enableIntents(
                        GatewayIntent.GUILD_MEMBERS,
                        GatewayIntent.GUILD_MESSAGES,
                        GatewayIntent.MESSAGE_CONTENT,
                        GatewayIntent.GUILD_PRESENCES,
                        GatewayIntent.GUILD_VOICE_STATES
                )
                .addEventListeners(new ReadyListener(config))
                .build();

        jda.awaitReady();
    }

}