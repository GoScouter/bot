package io.github.goscouter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.goscouter.config.Config;
import io.github.goscouter.listeners.ReadyListener;
import lombok.SneakyThrows;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class BotLoader {

    public static final Logger logger = LogManager.getLogger(BotLoader.class);
    private static final String CONFIG_FILE = "config.json";

    @SneakyThrows
    public static void main(String[] args) {
        final ObjectMapper mapper = new ObjectMapper();
        Config config = mapper.readValue(new File(CONFIG_FILE), Config.class);

        JDA jda = JDABuilder.createDefault("token")
                .addEventListeners(new ReadyListener(config))
                .build();

        jda.awaitReady();
    }

}