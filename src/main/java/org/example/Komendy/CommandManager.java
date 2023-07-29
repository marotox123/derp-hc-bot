package org.example.Komendy;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.selections.SelectOption;
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.Modal;
import org.example.ModalListener;
import org.example.SharedData;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CommandManager extends ListenerAdapter {







    /**
     * Ta metoda nasłuchuje na komendy slash i reaguje odpowiednio.
     */
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        String command = event.getName();
        if (command.equals("hello")) {
            // Uruchamia polecenie "Hello"
            String userTag = event.getUser().getAsTag();
            event.reply("Witam Jestem Gotowy do pomocy, **" + userTag + "**!").queue();
        } else if (command.equals("meme")) {
            try {
                // Pobiera losowego mema z API
                URL url = new URL("https://meme-api.com/gimme");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Parsuje odpowiedź, aby uzyskać adres URL obrazka mema i podpis
                JSONObject json = new JSONObject(response.toString());
                String imageUrl = json.getString("url");
                String caption = json.getString("title");

                // Wysyła obrazek mema i podpis jako wiadomość
                EmbedBuilder embed = new EmbedBuilder()
                        .setColor(Color.GREEN)
                        .setImage(imageUrl)
                        .setFooter(caption);
                event.replyEmbeds(embed.build()).queue();

            } catch (Exception e) {
                event.reply("Przepraszamy, wystąpił błąd podczas generowania mema.").queue();
                e.printStackTrace();
            }


        } else if (command.equals("help")){
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
                    Button.primary("prawa1",Emoji.fromUnicode("▶")))


                    .queue();
        } else if (command.equals("ping")){
            ActionRow row = ActionRow.of(
                    Button.primary("help", "Help").withEmoji(Emoji.fromUnicode("🛠️"))
            );


            EmbedBuilder embed = new EmbedBuilder()
                    .setColor(Color.red)
                    .setTitle("**Pong!**")
                    .addField("Tip", "Jeżeli potrzebujesz pomocy użyj `/help` ", false);
            event.replyEmbeds(embed.build()).addActionRow( Button.primary("help", "Help").withEmoji(Emoji.fromUnicode("🛠️"))).queue();


        } else if (command.equals("kick")){

            Member member = event.getOption("user").getAsMember();
            String reason = event.getOption("reason") != null ? event.getOption("reason").getAsString() : "Brak powodu";
            event.deferReply().queue();
            member.kick(reason).queue(
                    success -> {
                        EmbedBuilder embed = new EmbedBuilder()
                                .setColor(Color.RED)
                                .setTitle("Wyrzucono użytkownika")
                                .addField("Nazwa użytkownika", member.getUser().getAsTag(), false)
                                .addField("Powód", reason, false);
                        event.getHook().sendMessageEmbeds(embed.build()).queue();
                    },
                    error -> {
                        EmbedBuilder embed = new EmbedBuilder()
                                .setColor(Color.RED)
                                .setTitle("Błąd wyrzucania użytkownika")
                                .addField("Nazwa użytkownika", member.getUser().getAsTag(), false)
                                .addField("Powód", error.getMessage(), false);
                        event.getHook().sendMessageEmbeds(embed.build()).queue();
                    }
            );

        } else if (command.equals("ban")){
            User user = event.getOption("user").getAsUser();
            int deleteMessageDays = 7;

            event.getGuild().ban(user, deleteMessageDays, TimeUnit.DAYS).queue(
                    success -> event.reply("Użytkownik został zbanowany.").queue(),
                    error -> event.reply("Nie udało się zbanować użytkownika.").queue()
            );



        } else if (command.equals("unban")){
            User user = event.getOption("user").getAsUser();

            event.getGuild().unban(user).queue(
                    success -> event.reply("Użytkownik został odbanowany.").queue(),
                    error -> event.reply("Nie udało się odbanować użytkownika.").queue()
            );





        } else if (command.equals("mute")){
            event.deferReply().queue();
            Member member = event.getOption("user").getAsMember();

            // Sprawdź, czy bot ma uprawnienia do zarządzania rolami
            if (!event.getGuild().getSelfMember().hasPermission(Permission.MANAGE_ROLES)) {
                event.reply("Nie mam uprawnień do zarządzania rolami.").setEphemeral(true).queue();
                return;
            }

            // Sprawdź, czy użytkownik ma uprawnienia do zarządzania rolami
            if (!event.getMember().hasPermission(Permission.MANAGE_ROLES)) {
                event.reply("Nie masz uprawnień do zarządzania rolami.").setEphemeral(true).queue();
                return;
            }

            Role muteRole = event.getGuild().getRolesByName("Muted", true).get(0);

            event.getGuild().addRoleToMember(member, muteRole).queue(
                    success -> event.reply("Użytkownik został wyciszony.").setEphemeral(true).queue(),
                    error -> event.reply("Nie udało się wyciszyć użytkownika.").setEphemeral(true).queue()
            );


        } else if (command.equals("unmute")){
            event.deferReply().queue();
            Member member = event.getOption("user").getAsMember();

            // Sprawdź, czy bot ma uprawnienia do zarządzania rolami
            if (!event.getGuild().getSelfMember().hasPermission(Permission.MANAGE_ROLES)) {
                event.reply("Nie mam uprawnień do zarządzania rolami.").setEphemeral(true).queue();
                return;
            }

            // Sprawdź, czy użytkownik ma uprawnienia do zarządzania rolami
            if (!event.getMember().hasPermission(Permission.MANAGE_ROLES)) {
                event.reply("Nie masz uprawnień do zarządzania rolami.").setEphemeral(true).queue();
                return;
            }

            Role muteRole = event.getGuild().getRolesByName("Muted", true).get(0);

            event.getGuild().addRoleToMember(member, muteRole).queue(
                    success -> event.reply("Użytkownik został wyciszony.").setEphemeral(true).queue(),
                    error -> event.reply("Nie udało się wyciszyć użytkownika.").setEphemeral(true).queue()
            );
        }else if (command.equals("ticket")){
            String text = "Brak";
            String texxt = "MAK";
            event.reply("Witaj w Strefie Pomocy serwisu FlurryMc.pl!\n" +
                    "Jeżeli potrzebujesz Pomocy wybierz 1 z 7 poniższych kategorii. Wybierz ten, który najbardziej odpowiada twojemu problemowi.\n" +
                    "Opisz dokładnie swój problem i poczekaj na pomoc. Pamiętaj, że czas odpowiedzi nie wynosi więcej niż 24 godziny!\n" +
                    "\n" +
                    "Dostępne kategorie pomocy:\n" +
                    "\uD83D\uDD38 Brak rangi na koncie - problemy związane z kupnem rangi,\n" +
                    "\uD83D\uDD38 Skarga na użytkownika lub administratora - jeżeli masz jakieś podejrzenia co do administracji lub innych użytkowników zgłoś ich w tej kategorii,\n" +
                    "\uD83D\uDD38 Problem na serwerze Minecraft - problemy związane z kontem, płatnościami itp.\n" +
                    "\uD83D\uDD38 Problem na serwerze Discord - ogólne problemy związane z Discordem serwera itp.\n" +
                    "\uD83D\uDD38 Odwołanie od kary - możliwość odwołania się od kary nałożonej na serwerze Minecraft lub Discord,\n" +
                    "\uD83D\uDD38 Zapomniane dane logowania - problemy związane z brakiem dostępu do konta\n" +
                    "❓ Jeżeli nie ma odpowiedniej kategorii, napisz do nas wybierając opcję  Inna sprawa \n" +
                    "\n" +
                    "Nie zapominaj o zasadach!\n" +
                    "Na prywatnym ticketcie również musisz się stosować do regulaminu.\n" +
                    "Zachowaj szacunek do Administracji.\n" +
                    "Zachowaj cierpliwość.\n" +
                    "Tworzenie ticketów nie związanych z serwerem lub tematem kategorii jest niedozwolone.\n" +
                    "\n" +
                    "Aby utworzyć kanał pomocy wybierz odpowiednią kategorię i uzupełnij formularz.")
            .setActionRow(
                    StringSelectMenu.create("konto")
                            .addOptions(SelectOption.of("Rekrutacja na Testera", "8")
                                    .withEmoji(Emoji.fromUnicode("📣"))
                                    .withDefault(false))
                            .addOptions(SelectOption.of("Brak Rangi na Koncie", "1")
                                    .withDescription("problemy związane z kupnem rangi")
                                    .withEmoji(Emoji.fromUnicode("\uD83D\uDD38"))
                                    .withDefault(false))
                            .addOptions(SelectOption.of("Skarga na użytkownika lub administratora", "2")
                                    .withDescription("Zgłoś użytkownika lub administratora ")
                                    .withEmoji(Emoji.fromUnicode("\uD83D\uDD38"))
                                    .withDefault(false))
                            .addOptions(SelectOption.of("Problem na serwerze Minecraft", "3")
                                    .withDescription("problemy związane z kontem, płatnościami itp. ")
                                    .withEmoji(Emoji.fromUnicode("\uD83D\uDD38"))
                                    .withDefault(false))
                            .addOptions(SelectOption.of("Problem na serwerze Discord", "4")
                                    .withDescription("ogólne problemy związane z Discordem serwera itp.")
                                    .withEmoji(Emoji.fromUnicode("\uD83D\uDD38"))
                                    .withDefault(false))
                            .addOptions(SelectOption.of("Odwołanie od kary", "5")
                                    .withDescription("Możliwość odwołania od bana lub mute")
                                    .withEmoji(Emoji.fromUnicode("\uD83D\uDD38"))
                                    .withDefault(false))
                            .addOptions(SelectOption.of("Zapomniane dane logowania", "6")
                                    .withDescription("problemy związane z brakiem dostępu do konta")
                                    .withEmoji(Emoji.fromUnicode("\uD83D\uDD38"))
                                    .withDefault(false))
                            .addOptions(SelectOption.of("Inne", "7")
                                    .withDescription("Inna kategoria")
                                    .withEmoji(Emoji.fromUnicode("❓"))
                                    .withDefault(false))
                            .addOptions(SelectOption.of("Wybierz kategorię", "9")
                                    .withEmoji(Emoji.fromUnicode("\uD83D\uDD38"))
                                    .withDefault(true))
                            .build())
                    .queue();




        }else if (command.equals("weryfikacja")){
            Color color = new Color(0x00ABFF);

            // Tworzenie EmbedBuilder
            EmbedBuilder embedBuilder = new EmbedBuilder()
                    .setTitle("Gratulacje! Jesteś godnym członkiem naszej społeczności 🥳.")
                    .setDescription("Ale zaczekaj, pozostał jeszcze jeden ETAP, a mianowicie weryfikacja.")
                    .addField("Aby się zweryfikować, kliknij przycisk dostępny pod tą wiadomością.", "\u200B", false)
                    .addField("\u200B", "Pamiętaj, że na całym serwerze obowiązuje regulamin z kanału \uD83D\uDCDC︱ʀᴇɢᴜʟᴀᴍɪɴ-ᴅɪꜱᴄᴏʀᴅ oraz zasady aplikacji Discord!", false)
                    .setColor(color);
            event.replyEmbeds(embedBuilder.build()).addActionRow(
                    Button.secondary("weryfikacja", "✅Kliknij mnie jak chcesz się Zweryfikować")
            ).queue();

        }
    }







        @Override
        public void onStringSelectInteraction(@NotNull StringSelectInteractionEvent event) {
            System.out.println("event.getSelectMenu().getId().toLowerCase() = " + event.getSelectMenu().getId().toLowerCase());

            switch (event.getSelectMenu().getId().toLowerCase()) {
                case "konto" -> {
                    String selectedValue = event.getValues().get(0);

                    // Sprawdź, czy selectedValue ma wartość "1"
                    if ("1".equals(selectedValue)) {

                        TextInput subject = TextInput.create("subject", "Nazwa z Minecraft", TextInputStyle.SHORT)
                                .setPlaceholder("Np. Herobrine123")
                                .setMinLength(2)
                                .setMaxLength(16) // or setRequiredRange(10, 100)
                                .build();

                        TextInput body = TextInput.create("body", "Opisz swój problem", TextInputStyle.PARAGRAPH)
                                .setPlaceholder("W czym mamy ci pomóc?")
                                .setMinLength(30)
                                .setMaxLength(1000)
                                .build();

                        Modal modal = Modal.create("konto1", "Wypełnij Formularz")
                                .addComponents(ActionRow.of(subject), ActionRow.of(body))
                                .build();

                        event.replyModal(modal).queue();
                    } else if ("2".equals(selectedValue)) {
                        // Tutaj dodaj kod odpowiedzialny za modal dla wartości "2"
                        TextInput subject = TextInput.create("subject2", "Nazwa z Minecraft", TextInputStyle.SHORT)
                                .setPlaceholder("Np. Herobrine123")
                                .setMinLength(2)
                                .setMaxLength(16) // or setRequiredRange(10, 100)
                                .build();

                        TextInput Osoba = TextInput.create("Osoba", "Nick Administratora lub Gracza", TextInputStyle.SHORT)
                                .setPlaceholder("Np. Herobrine123")
                                .setMinLength(2)
                                .setMaxLength(16) // or setRequiredRange(10, 100)
                                .build();

                        TextInput body = TextInput.create("body2", "Opisz swój problem", TextInputStyle.PARAGRAPH)
                                .setPlaceholder("W czym mamy ci pomóc?")
                                .setMinLength(30)
                                .setMaxLength(1000)
                                .build();

                        Modal modal = Modal.create("skarga", "Wypełnij Formularz")
                                .addComponents(ActionRow.of(subject), ActionRow.of(Osoba), ActionRow.of(body))
                                .build();

                        event.replyModal(modal).queue();
                    } else if ("3".equals(selectedValue)) {
                        TextInput subject = TextInput.create("subject3", "Nazwa z Minecraft", TextInputStyle.SHORT)
                                .setPlaceholder("Np. Herobrine123")
                                .setMinLength(2)
                                .setMaxLength(16) // or setRequiredRange(10, 100)
                                .build();

                        TextInput body = TextInput.create("body3", "Opisz swój problem", TextInputStyle.PARAGRAPH)
                                .setPlaceholder("W czym mamy ci pomóc?")
                                .setMinLength(30)
                                .setMaxLength(1000)
                                .build();

                        Modal modal = Modal.create("Problem", "Wypełnij Formularz")
                                .addComponents(ActionRow.of(subject), ActionRow.of(body))
                                .build();
                        event.replyModal(modal).queue();




                    }else if ("4".equals(selectedValue)){
                        TextInput subject = TextInput.create("subject4", "Nazwa z Discord", TextInputStyle.SHORT)
                                .setPlaceholder("Np. Herobrine123")
                                .setMinLength(2)
                                .setMaxLength(16) // or setRequiredRange(10, 100)
                                .build();

                        TextInput body = TextInput.create("body4", "Opisz swój problem", TextInputStyle.PARAGRAPH)
                                .setPlaceholder("W czym mamy ci pomóc?")
                                .setMinLength(30)
                                .setMaxLength(1000)
                                .build();

                        Modal modal = Modal.create("Problem_DC", "Wypełnij Formularz")
                                .addComponents(ActionRow.of(subject), ActionRow.of(body))
                                .build();
                        event.replyModal(modal).queue();

                    }else if ("5".equals(selectedValue)){
                        TextInput subject = TextInput.create("subject5", "Nazwa z Minecraft", TextInputStyle.SHORT)
                                .setPlaceholder("Np. Herobrine123")
                                .setMinLength(2)
                                .setMaxLength(16) // or setRequiredRange(10, 100)
                                .build();

                        TextInput body = TextInput.create("body5", "Kiedy zostales zbanowany/wyciszony", TextInputStyle.PARAGRAPH)
                                .setPlaceholder("Np. 29.07.2023")
                                .setMinLength(6)
                                .setMaxLength(100)
                                .build();

                        TextInput Pytanie = TextInput.create("pytanie", "Dlaczego mielibyśmy cię odbanować?", TextInputStyle.PARAGRAPH)
                                .setPlaceholder("Np. Nie mam chatów, mam dowody na to że nie oszukiwałem")
                                .setMinLength(30)
                                .setMaxLength(1000)
                                .build();

                        Modal modal = Modal.create("odwołanie", "Wypełnij Formularz")
                                .addComponents(ActionRow.of(subject), ActionRow.of(body), ActionRow.of(Pytanie))
                                .build();

                        event.replyModal(modal).queue();



                    }else if ("6".equals(selectedValue)){
                        TextInput subject = TextInput.create("subject6", "Nazwa z Minecraft", TextInputStyle.SHORT)
                                .setPlaceholder("Np. Herobrine123")
                                .setMinLength(2)
                                .setMaxLength(16) // or setRequiredRange(10, 100)
                                .build();

                        TextInput body = TextInput.create("body6", "Opisz swój problem", TextInputStyle.PARAGRAPH)
                                .setPlaceholder("W czym mamy ci pomóc?")
                                .setMinLength(30)
                                .setMaxLength(1000)
                                .build();

                        Modal modal = Modal.create("loginzap", "Wypełnij Formularz")
                                .addComponents(ActionRow.of(subject), ActionRow.of(body))
                                .build();

                        event.replyModal(modal).queue();

                    }else if ("7".equals(selectedValue)){
                        TextInput subject = TextInput.create("subject7", "Nazwa z Minecraft", TextInputStyle.SHORT)
                                .setPlaceholder("Np. Herobrine123")
                                .setMinLength(2)
                                .setMaxLength(16) // or setRequiredRange(10, 100)
                                .build();

                        TextInput body = TextInput.create("body7", "Opisz swój problem", TextInputStyle.PARAGRAPH)
                                .setPlaceholder("W czym mamy ci pomóc?")
                                .setMinLength(30)
                                .setMaxLength(1000)
                                .build();

                        Modal modal = Modal.create("inne", "Wypełnij Formularz")
                                .addComponents(ActionRow.of(subject), ActionRow.of(body))
                                .build();

                        event.replyModal(modal).queue();

                    }else if ("8".equals(selectedValue)){
                        TextInput subject = TextInput.create("subject8", "Nazwa z Minecraft", TextInputStyle.SHORT)
                                .setPlaceholder("Np. Herobrine123")
                                .setMinLength(2)
                                .setMaxLength(16) // or setRequiredRange(10, 100)
                                .build();
                        TextInput Imie = TextInput.create("imie", "Imie", TextInputStyle.PARAGRAPH)
                                .setMinLength(2)
                                .setMaxLength(50)
                                .build();
                        TextInput Wiek = TextInput.create("wiek", "Wiek", TextInputStyle.PARAGRAPH)
                                .setMinLength(2)
                                .setMaxLength(2)
                                .build();
                        TextInput Doświadczenie = TextInput.create("dos", "Doświadcznenie + Jakie", TextInputStyle.PARAGRAPH)
                                .setPlaceholder("Np. Jeśli masz doświadczenie w testowaniu serwera opisz je tutaj.")
                                .setMinLength(30)
                                .setMaxLength(1000)
                                .build();
                        TextInput osobie = TextInput.create("osobie", "Napisz coś o sobie", TextInputStyle.PARAGRAPH)
                                .setMinLength(30)
                                .setMaxLength(1000)
                                .build();
                        Modal modal = Modal.create("zgłoszenie-tester", "Wypełnij Formularz")
                                .addComponents(ActionRow.of(subject), ActionRow.of(Imie), ActionRow.of(Wiek), ActionRow.of(Doświadczenie), ActionRow.of(osobie))
                                .build();

                        event.replyModal(modal).queue();
                    }else {
                        // Kod dla innych wybranych wartości
                        // ...
                    }

                    ///Till here
                }
            }
        }











    /**
     * Registers slash commands as GLOBAL commands (unlimited).
     * These commands may take up to an hour to update.
     */

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        List<CommandData> commandData = new ArrayList<>();
        commandData.add(Commands.slash("ticket", "Ustawia Ticket"));
        commandData.add(Commands.slash("weryfikacja", "Ustawia weryfikacje"));
        commandData.add(Commands.slash("hello", "Siemka Bocie"));
        commandData.add(Commands.slash("meme", "Generuje mema"));
        commandData.add(Commands.slash("help", "Lista komend"));
        commandData.add(Commands.slash("ping", "Sprawdz czy bot odpowiada"));
        commandData.add(Commands.slash("kick", "Wyrzuca  z serwera")
                .addOption(OptionType.USER, "user", "Użytkownik do wyrzucenia", true)
                .addOption(OptionType.STRING, "reason", "Powód wyrzucenia", false));
        commandData.add(Commands.slash("ban", "Banuje użytkownika na serwerze")
                .addOption(OptionType.USER, "user", "Użytkownik do zbanowania", true)
                .addOption(OptionType.STRING, "reason", "Powód zbanowania", false));
        commandData.add(Commands.slash("unban", "Odbanowuje użytkownika na serwerze")
                .addOption(OptionType.USER, "user", "Użytkownik do odbanowania", true)
                .addOption(OptionType.STRING, "reason", "Powód odbanowania", false));
        commandData.add(Commands.slash("mute", "Mutuje Użytkownika")
                .addOption(OptionType.USER, "user", "Użytkownik do Mutowania", true)
                .addOption(OptionType.STRING, "reason", "Powód Mutowania", false));
        commandData.add(Commands.slash("unmute", "Unmutuje Użytkownika")
                .addOption(OptionType.USER, "user", "Użytkownik do Unmutowania", true)
                .addOption(OptionType.STRING, "reason", "Powód Unmutowania", false));
        event.getJDA().updateCommands().addCommands(commandData).queue();
    }


}






