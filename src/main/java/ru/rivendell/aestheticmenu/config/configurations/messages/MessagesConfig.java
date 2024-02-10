package ru.rivendell.aestheticmenu.config.configurations.messages;

import lombok.Getter;
import lombok.Setter;
import ru.rivendell.aestheticmenu.config.Configurable;

@Getter @Setter
public class MessagesConfig extends Configurable {

    private String reloadMessage;
    private String noPermission;

    @Override
    protected String getName() {
        return "messages";
    }
}
