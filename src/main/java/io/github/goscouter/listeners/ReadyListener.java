package io.github.goscouter.listeners;

import io.github.goscouter.BotLoader;
import io.github.goscouter.config.Config;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

@RequiredArgsConstructor
public class ReadyListener extends ListenerAdapter {

    private final Config config;

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        super.onReady(event);

        BotLoader.logger.info("Goscouter bot - ready!");
        BotLoader.logger.info("Token: {}", (this.config.discordToken().isEmpty() ? "No token was found" : "*****"));
    }
}
