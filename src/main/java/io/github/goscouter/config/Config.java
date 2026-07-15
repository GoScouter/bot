package io.github.goscouter.config;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Config(
        @JsonProperty("discord_token")
        String discordToken,

        @JsonProperty("about_channel")
        long aboutChannel,

        @JsonProperty("welcome_channel")
        long welcomeChannel
) {
}
