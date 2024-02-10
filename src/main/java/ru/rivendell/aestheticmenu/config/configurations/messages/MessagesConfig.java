package ru.rivendell.aestheticmenu.config.configurations.messages;

import lombok.Getter;
import lombok.Setter;
import ru.rivendell.aestheticmenu.config.Configurable;
import ru.rivendell.aestheticmenu.config.configurations.messages.variants.ErrorMessages;
import ru.rivendell.aestheticmenu.config.configurations.messages.variants.SystemMessages;

@Getter @Setter
public class MessagesConfig extends Configurable {

    private ErrorMessages errors;
    private SystemMessages system;

    @Override
    protected String getName() {
        return "messages";
    }
}
