package org.example.eventy;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class button extends ListenerAdapter {
    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
        if (event.getComponentId().equals("weryfikacja")) {
            Member member = event.getMember();

            // Sprawdź, czy członek jest null (na przykład jeśli nie jest już na serwerze)
            if (member != null) {
                // Dodaj rangę o ID 1075028517005316136
                event.getGuild().addRoleToMember(member, event.getGuild().getRoleById("1075028517005316136")).queue();

                // Odpowiedz potwierdzeniem na kliknięcie przycisku
                event.reply("Gratulacje! Zostałeś zweryfikowany i otrzymałeś rangę. 🥳").setEphemeral(true).queue();


        }






    }
}}
