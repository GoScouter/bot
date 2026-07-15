package io.github.goscouter.listeners;

import io.github.goscouter.BotLoader;
import io.github.goscouter.config.Config;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

@RequiredArgsConstructor
public class WelcomeListener extends ListenerAdapter {

    private static final Color GO_BLUE = new Color(0x00, 0xAD, 0xD8);

    private final Config config;

    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {
        final TextChannel channel = event.getGuild().getTextChannelById(this.config.welcomeChannel());
        if (channel == null) {
            BotLoader.logger.warn("Welcome channel {} not found in guild {}",
                    this.config.welcomeChannel(), event.getGuild().getId());
            return;
        }

        final MessageEmbed embed = buildWelcomeEmbed(event);
        channel.sendMessage(event.getMember().getAsMention())
                .addEmbeds(embed)
                .queue();
    }

    private MessageEmbed buildWelcomeEmbed(@NotNull GuildMemberJoinEvent event) {
        return new EmbedBuilder()
                .setColor(GO_BLUE)
                .setTitle("Welcome to GoScouter 🧭")
                .setThumbnail(event.getUser().getEffectiveAvatarUrl())
                .setDescription("Hey %s, welcome aboard! 👋%n%n".formatted(event.getMember().getAsMention())
                        + "**GoScouter** is a fast, no-nonsense toolkit for scouting, "
                        + "probing, and analyzing the net"
                )
                .addField("📖 About", "Check out <#" + this.config.aboutChannel()
                        + "> to learn what we're building.", false)
                .setFooter("Member #" + event.getGuild().getMemberCount())
                .build();
    }
}
