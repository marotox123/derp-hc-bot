package org.example;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import javax.annotation.Nonnull;
import java.awt.*;
import java.util.EnumSet;

public class ModalListener extends ListenerAdapter {


    @Override
    public void onModalInteraction(@Nonnull ModalInteractionEvent event) {
        if (event.getModalId().equals("konto1")) {
            String subject = event.getValue("subject").getAsString();
            String body = event.getValue("body").getAsString();



            EmbedBuilder embed = new EmbedBuilder()
                    .setTitle("FlurryMC.pl Ticket Informacje")
                    .setColor(new Color(6908077))
                    .setAuthor("FlurryMc Bot", "https://cdn.discordapp.com/avatars/1073688615118446682/ca734c73c9aaa9625f1d4028f08665a8.png?size=1024")
                    .addField("Kategoria:", "Brak Rangi na Koncie", false)
                    .addField("Nick:", subject, false)
                    .addField("Opis Problemu:", body, false);

            Guild guild = event.getGuild();
            int min = 1000;
            int max = 99999;
            int random_int = (int) Math.floor(Math.random() * (max - min + 1) + min);

            final TextChannel channel = guild.createTextChannel("ticket-" + random_int, guild.getCategoryById("1075048189801009194"))
                    .addPermissionOverride(event.getMember(), EnumSet.of(Permission.VIEW_CHANNEL), null)
                    .addPermissionOverride(guild.getPublicRole(), null, EnumSet.of(Permission.VIEW_CHANNEL))
                    .addPermissionOverride(guild.getRoleById("1075397088688492554"), EnumSet.of(Permission.VIEW_CHANNEL), null).complete();

            channel.sendMessageEmbeds(embed.build()).addActionRow(
                    Button.danger("zamknijticket", "❌Zamknij Ticketa")).queue();

            Role role1 = guild.getRoleById("1075027970483294328");
            Role role2 = guild.getRoleById("1075397088688492554");

            // Pobieramy osobę, która stworzyła ticketa
            Member author = event.getMember();

            channel.sendMessage(author.getAsMention() + role1.getAsMention() + role2.getAsMention()).queue();
            

            event.reply("Twój Ticket został stworzony, Kanał ticket to " + channel.getAsMention() + ". Tylko ty widzisz tą wiadomość ").setEphemeral(true).queue();










        } else  if (event.getModalId().equals("skarga")) {
            String subject = event.getValue("subject2").getAsString();
            String Osoba = event.getValue("Osoba").getAsString();
            String body = event.getValue("body2").getAsString();




            EmbedBuilder embed = new EmbedBuilder()
                    .setTitle("FlurryMC.pl Ticket Informacje")
                    .setColor(new Color(6908077))
                    .setAuthor("FlurryMc Bot", "https://cdn.discordapp.com/avatars/1073688615118446682/ca734c73c9aaa9625f1d4028f08665a8.png?size=1024")
                    .addField("Kategoria:", "Skarga na użytkownika lub administratora", false)
                    .addField("Nick:", subject, false)
                    .addField("Administrator/Osoba", Osoba, false)
                    .addField("Opis Problemu:", body, false);

            Guild guild = event.getGuild();
            int min = 1000;
            int max = 99999;
            int random_int = (int) Math.floor(Math.random() * (max - min + 1) + min);

            final TextChannel channel = guild.createTextChannel("ticket-" + random_int, guild.getCategoryById("1075048189801009194"))
                    .addPermissionOverride(event.getMember(), EnumSet.of(Permission.VIEW_CHANNEL), null)
                    .addPermissionOverride(guild.getPublicRole(), null, EnumSet.of(Permission.VIEW_CHANNEL))
                    .addPermissionOverride(guild.getRoleById("1075397088688492554"), EnumSet.of(Permission.VIEW_CHANNEL), null).complete();

            channel.sendMessageEmbeds(embed.build()).addActionRow(
                    Button.danger("zamknijticket", "❌Zamknij Ticketa")).queue();

            Role role1 = guild.getRoleById("1075027970483294328");
            Role role2 = guild.getRoleById("1075397088688492554");

            // Pobieramy osobę, która stworzyła ticketa
            Member author = event.getMember();

            channel.sendMessage(author.getAsMention() + role1.getAsMention() + role2.getAsMention()).queue();


            event.reply("Twój Ticket został stworzony, Kanał ticket to " + channel.getAsMention() + ". Tylko ty widzisz tą wiadomość ").setEphemeral(true).queue();

        }else  if (event.getModalId().equals("Problem")) {
            String subject = event.getValue("subject3").getAsString();
            String body = event.getValue("body3").getAsString();




            EmbedBuilder embed = new EmbedBuilder()
                    .setTitle("FlurryMC.pl Ticket Informacje")
                    .setColor(new Color(6908077))
                    .setAuthor("FlurryMc Bot", "https://cdn.discordapp.com/avatars/1073688615118446682/ca734c73c9aaa9625f1d4028f08665a8.png?size=1024")
                    .addField("Kategoria:", "Problem na serwerze Minecraft", false)
                    .addField("Nick:", subject, false)
                    .addField("Opis Problemu:", body, false);

            Guild guild = event.getGuild();
            int min = 1000;
            int max = 99999;
            int random_int = (int) Math.floor(Math.random() * (max - min + 1) + min);

            final TextChannel channel = guild.createTextChannel("ticket-" + random_int, guild.getCategoryById("1075048189801009194"))
                    .addPermissionOverride(event.getMember(), EnumSet.of(Permission.VIEW_CHANNEL), null)
                    .addPermissionOverride(guild.getPublicRole(), null, EnumSet.of(Permission.VIEW_CHANNEL))
                    .addPermissionOverride(guild.getRoleById("1075397088688492554"), EnumSet.of(Permission.VIEW_CHANNEL), null).complete();

            channel.sendMessageEmbeds(embed.build()).addActionRow(
                    Button.danger("zamknijticket", "❌Zamknij Ticketa")).queue();

            Role role1 = guild.getRoleById("1075027970483294328");
            Role role2 = guild.getRoleById("1075397088688492554");

            // Pobieramy osobę, która stworzyła ticketa
            Member author = event.getMember();

            channel.sendMessage(author.getAsMention() + role1.getAsMention() + role2.getAsMention()).queue();


            event.reply("Twój Ticket został stworzony, Kanał ticket to " + channel.getAsMention() + ". Tylko ty widzisz tą wiadomość ").setEphemeral(true).queue();







        }else  if (event.getModalId().equals("Problem_DC")) {
            String subject = event.getValue("subject4").getAsString();
            String body = event.getValue("body4").getAsString();




            EmbedBuilder embed = new EmbedBuilder()
                    .setTitle("FlurryMC.pl Ticket Informacje")
                    .setColor(new Color(6908077))
                    .setAuthor("FlurryMc Bot", "https://cdn.discordapp.com/avatars/1073688615118446682/ca734c73c9aaa9625f1d4028f08665a8.png?size=1024")
                    .addField("Kategoria:", "Problem na serwerze Discord", false)
                    .addField("Nick:", subject, false)
                    .addField("Opis Problemu:", body, false);

            Guild guild = event.getGuild();
            int min = 1000;
            int max = 99999;
            int random_int = (int) Math.floor(Math.random() * (max - min + 1) + min);

            final TextChannel channel = guild.createTextChannel("ticket-" + random_int, guild.getCategoryById("1075048189801009194"))
                    .addPermissionOverride(event.getMember(), EnumSet.of(Permission.VIEW_CHANNEL), null)
                    .addPermissionOverride(guild.getPublicRole(), null, EnumSet.of(Permission.VIEW_CHANNEL))
                    .addPermissionOverride(guild.getRoleById("1075397088688492554"), EnumSet.of(Permission.VIEW_CHANNEL), null).complete();

            channel.sendMessageEmbeds(embed.build()).addActionRow(
                    Button.danger("zamknijticket", "❌Zamknij Ticketa")).queue();

            Role role1 = guild.getRoleById("1075027970483294328");
            Role role2 = guild.getRoleById("1075397088688492554");

            // Pobieramy osobę, która stworzyła ticketa
            Member author = event.getMember();

            channel.sendMessage(author.getAsMention() + role1.getAsMention() + role2.getAsMention()).queue();


            event.reply("Twój Ticket został stworzony, Kanał ticket to " + channel.getAsMention() + ". Tylko ty widzisz tą wiadomość ").setEphemeral(true).queue();







        }else  if (event.getModalId().equals("odwołanie")) {
            String subject = event.getValue("subject5").getAsString();
            String body = event.getValue("body5").getAsString();
            String Pytanie = event.getValue("pytanie").getAsString();




            EmbedBuilder embed = new EmbedBuilder()
                    .setTitle("FlurryMC.pl Ticket Informacje")
                    .setColor(new Color(6908077))
                    .setAuthor("FlurryMc Bot", "https://cdn.discordapp.com/avatars/1073688615118446682/ca734c73c9aaa9625f1d4028f08665a8.png?size=1024")
                    .addField("Kategoria:", "Odwołanie od kary", false)
                    .addField("Nick:", subject, false)
                    .addField("Data kary", body, false)
                    .addField("Dlaczego mielibyśmy cię odbanować?", Pytanie, false);

            Guild guild = event.getGuild();
            int min = 1000;
            int max = 99999;
            int random_int = (int) Math.floor(Math.random() * (max - min + 1) + min);

            final TextChannel channel = guild.createTextChannel("ticket-" + random_int, guild.getCategoryById("1075048189801009194"))
                    .addPermissionOverride(event.getMember(), EnumSet.of(Permission.VIEW_CHANNEL), null)
                    .addPermissionOverride(guild.getPublicRole(), null, EnumSet.of(Permission.VIEW_CHANNEL))
                    .addPermissionOverride(guild.getRoleById("1075397088688492554"), EnumSet.of(Permission.VIEW_CHANNEL), null).complete();

            channel.sendMessageEmbeds(embed.build()).addActionRow(
                    Button.danger("zamknijticket", "❌Zamknij Ticketa")).queue();

            Role role1 = guild.getRoleById("1075027970483294328");
            Role role2 = guild.getRoleById("1075397088688492554");

            // Pobieramy osobę, która stworzyła ticketa
            Member author = event.getMember();

            channel.sendMessage(author.getAsMention() + role1.getAsMention() + role2.getAsMention()).queue();


            event.reply("Twój Ticket został stworzony, Kanał ticket to " + channel.getAsMention() + ". Tylko ty widzisz tą wiadomość ").setEphemeral(true).queue();


        }else  if (event.getModalId().equals("loginzap")) {
            String subject = event.getValue("subject6").getAsString();
            String body = event.getValue("body6").getAsString();



            EmbedBuilder embed = new EmbedBuilder()
                    .setTitle("FlurryMC.pl Ticket Informacje")
                    .setColor(new Color(6908077))
                    .setAuthor("FlurryMc Bot", "https://cdn.discordapp.com/avatars/1073688615118446682/ca734c73c9aaa9625f1d4028f08665a8.png?size=1024")
                    .addField("Kategoria:", "Zapomniane dane logowania", false)
                    .addField("Nick:", subject, false)
                    .addField("Opis Problemu:", body, false);

            Guild guild = event.getGuild();
            int min = 1000;
            int max = 99999;
            int random_int = (int) Math.floor(Math.random() * (max - min + 1) + min);

            final TextChannel channel = guild.createTextChannel("ticket-" + random_int, guild.getCategoryById("1075048189801009194"))
                    .addPermissionOverride(event.getMember(), EnumSet.of(Permission.VIEW_CHANNEL), null)
                    .addPermissionOverride(guild.getPublicRole(), null, EnumSet.of(Permission.VIEW_CHANNEL))
                    .addPermissionOverride(guild.getRoleById("1075397088688492554"), EnumSet.of(Permission.VIEW_CHANNEL), null).complete();

            channel.sendMessageEmbeds(embed.build()).addActionRow(
                    Button.danger("zamknijticket", "❌Zamknij Ticketa")).queue();

            Role role1 = guild.getRoleById("1075027970483294328");
            Role role2 = guild.getRoleById("1075397088688492554");

            // Pobieramy osobę, która stworzyła ticketa
            Member author = event.getMember();

            channel.sendMessage(author.getAsMention() + role1.getAsMention() + role2.getAsMention()).queue();


            event.reply("Twój Ticket został stworzony, Kanał ticket to " + channel.getAsMention() + ". Tylko ty widzisz tą wiadomość ").setEphemeral(true).queue();

        }else  if (event.getModalId().equals("inne")) {
            String subject = event.getValue("subject7").getAsString();
            String body = event.getValue("body7").getAsString();



            EmbedBuilder embed = new EmbedBuilder()
                    .setTitle("FlurryMC.pl Ticket Informacje")
                    .setColor(new Color(6908077))
                    .setAuthor("FlurryMc Bot", "https://cdn.discordapp.com/avatars/1073688615118446682/ca734c73c9aaa9625f1d4028f08665a8.png?size=1024")
                    .addField("Kategoria:", "Inne", false)
                    .addField("Nick:", subject, false)
                    .addField("Opis Problemu:", body, false);

            Guild guild = event.getGuild();
            int min = 1000;
            int max = 99999;
            int random_int = (int) Math.floor(Math.random() * (max - min + 1) + min);

            final TextChannel channel = guild.createTextChannel("ticket-" + random_int, guild.getCategoryById("1075048189801009194"))
                    .addPermissionOverride(event.getMember(), EnumSet.of(Permission.VIEW_CHANNEL), null)
                    .addPermissionOverride(guild.getPublicRole(), null, EnumSet.of(Permission.VIEW_CHANNEL))
                    .addPermissionOverride(guild.getRoleById("1075397088688492554"), EnumSet.of(Permission.VIEW_CHANNEL), null).complete();

            channel.sendMessageEmbeds(embed.build()).addActionRow(
                    Button.danger("zamknijticket", "❌Zamknij Ticketa")).queue();

            Role role1 = guild.getRoleById("1075027970483294328");
            Role role2 = guild.getRoleById("1075397088688492554");

            // Pobieramy osobę, która stworzyła ticketa
            Member author = event.getMember();

            channel.sendMessage(author.getAsMention() + role1.getAsMention() + role2.getAsMention()).queue();


            event.reply("Twój Ticket został stworzony, Kanał ticket to " + channel.getAsMention() + ". Tylko ty widzisz tą wiadomość ").setEphemeral(true).queue();

        }else  if (event.getModalId().equals("zgłoszenie-tester")) {
            String subject = event.getValue("subject8").getAsString();
            String Imie = event.getValue("imie").getAsString();
            String Wiek = event.getValue("imie").getAsString();
            String Doświadczenie = event.getValue("dos").getAsString();
            String osobie = event.getValue("osobie").getAsString();

            Color color = new Color(0x42FF00); // Kolor #42ff00



            EmbedBuilder embed = new EmbedBuilder()
                    .setTitle("FlurryMC.pl Zgłoszenie Testera Informacje")
                    .setColor(color)
                    .setAuthor("FlurryMc Bot", "https://cdn.discordapp.com/avatars/1073688615118446682/ca734c73c9aaa9625f1d4028f08665a8.png?size=1024")
                    .addField("Kategoria:", "Zgłoszenie Testera", false)
                    .addField("Nick", subject, false)
                    .addField("Imie", Imie, false)
                    .addField("Wiek", Wiek, false)
                    .addField("Doświadczenie", Doświadczenie, false)
                    .addField("Opis siebie", osobie, false);



            Guild guild = event.getGuild();
            int min = 1000;
            int max = 99999;
            int random_int = (int) Math.floor(Math.random() * (max - min + 1) + min);

            final TextChannel channel = guild.createTextChannel("zgłoszenie-" + random_int, guild.getCategoryById("1075048189801009194"))
                    .addPermissionOverride(event.getMember(), EnumSet.of(Permission.VIEW_CHANNEL), null)
                    .addPermissionOverride(guild.getPublicRole(), null, EnumSet.of(Permission.VIEW_CHANNEL))
                    .addPermissionOverride(guild.getRoleById("1075027970483294328"), EnumSet.of(Permission.VIEW_CHANNEL), null).complete();

            channel.sendMessageEmbeds(embed.build()).addActionRow(
                    Button.danger("zamknijticket", "❌Zamknij Zgłoszenie")).queue();

            Role role1 = guild.getRoleById("1075027970483294328");

            // Pobieramy osobę, która stworzyła ticketa
            Member author = event.getMember();

            channel.sendMessage(author.getAsMention() + role1.getAsMention()).queue();


            event.reply("Twoje Zgłoszenie zostało stworzone, Kanał Zgłoszenia to " + channel.getAsMention() + ". Tylko ty widzisz tą wiadomość ").setEphemeral(true).queue();


        }
    }

}
