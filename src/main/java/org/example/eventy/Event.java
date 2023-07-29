package org.example.eventy;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.concrete.Category;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.ItemComponent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.selections.SelectMenu;
import net.dv8tion.jda.api.interactions.components.selections.SelectOption;
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.awt.*;
import java.util.EnumSet;
import java.util.concurrent.TimeUnit;

public class Event extends ListenerAdapter {

    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
        if (event.getComponentId().equals("help")) {
            // Handle the help button click
            EmbedBuilder embed = new EmbedBuilder()
                    .setColor(Color.GREEN)
                    .setTitle("Lista Komend")
                    .addField("`/hello`", "Pozdrawia użytkownika", false)
                    .addField("`/meme`", "Wyświetla losowego mema", false)
                    .addField("`/ban`", "Banuje użytkownika na serwerze", false)
                    .addField("`/unban`", "Odbanowuje użytkownika na serwerze", false)
                    .addField("`/kick`", "Wyrzuca użytkownika z serwera", false)
                    .addField("`/help`", "Wyświetla listę dostępnych komend", false);


            event.replyEmbeds(embed.build()).addActionRow(
                            Button.danger("numer1", "1/2"),
                            Button.primary("prawa1", Emoji.fromUnicode("▶")))


                    .queue();
        }
        if (event.getComponentId().equals("prawa1")) {

            EmbedBuilder embed = new EmbedBuilder()
                    .setColor(Color.GREEN)
                    .setTitle("Lista Komend")
                    .addField("`/mute`", "Mutuje użytkownika", false)
                    .addField("`/unmute`", "Unmutuje użytkownika", false)
                    .addField("Wkrótce...", "\u200E \u200E \u200E \u200E \u200E \u200E \u200E \u200E \u200E \u200E ", false);
            event.replyEmbeds(embed.build()).addActionRow(
                            Button.danger("numer2", "2/2"),
                            Button.primary("lewa1", Emoji.fromUnicode("◀")))


                    .queue();


        }
        if (event.getComponentId().equals("lewa1")) {

            EmbedBuilder embed = new EmbedBuilder()
                    .setColor(Color.GREEN)
                    .setTitle("Lista Komend")
                    .addField("`/hello`", "Pozdrawia użytkownika", false)
                    .addField("`/meme`", "Wyświetla losowego mema", false)
                    .addField("`/ban`", "Banuje użytkownika na serwerze", false)
                    .addField("`/unban`", "Odbanowuje użytkownika na serwerze", false)
                    .addField("`/kick`", "Wyrzuca użytkownika z serwera", false)
                    .addField("`/help`", "Wyświetla listę dostępnych komend", false);


            event.replyEmbeds(embed.build()).addActionRow(
                            Button.danger("numer1", "1/2"),
                            Button.primary("prawa1", Emoji.fromUnicode("▶")))


                    .queue();


        }
        if (event.getComponentId().equals("zamknijticket")) {
            Member member = event.getMember();
            Guild guild = event.getGuild();

            // Sprawdź, czy osoba ma rolę o danym ID
            if (hasRequiredRole(member)) {
                // Sprawdź, czy kanał jest kanałem tekstowym, aby go usunąć
                if (event.getChannel() instanceof TextChannel) {
                    TextChannel textChannel = (TextChannel) event.getChannel();
                    textChannel.delete().queue(
                            success -> {
                                event.reply("Kanał został pomyślnie usunięty.").setEphemeral(true).queue();
                            },
                            error -> {
                                event.reply("Wystąpił błąd podczas próby usunięcia kanału.").setEphemeral(true).queue();
                            }
                    );
                } else {
                    // Jeżeli kanał nie jest kanałem tekstowym, wyświetl odpowiedni komunikat
                    event.reply("Nie można usunąć tego kanału, ponieważ nie jest to kanał tekstowy.").setEphemeral(true).queue();
                }
            } else {
                // Jeżeli osoba nie ma wymaganej roli, wyświetl odpowiedni komunikat
                event.reply("Nie masz uprawnień do usunięcia tego kanału.").setEphemeral(true).queue();
            }
        }if (event.getComponentId().equals("weryfikacja")) {


        }
    }
    private boolean hasRequiredRole(Member member) {
        String requiredRole1 = "1075397088688492554";
        String requiredRole2 = "1075027970483294328";

        return member.getRoles().stream().anyMatch(role -> role.getId().equals(requiredRole1) || role.getId().equals(requiredRole2));
    }
}








