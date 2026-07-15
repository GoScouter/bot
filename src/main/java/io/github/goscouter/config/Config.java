package io.github.goscouter.config;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Config(
        @JsonProperty("discord_token")
        String discordToken
) {
}
