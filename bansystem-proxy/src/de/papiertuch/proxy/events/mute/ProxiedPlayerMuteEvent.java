package de.papiertuch.proxy.events.mute;

import lombok.AllArgsConstructor;
import net.md_5.bungee.api.plugin.Event;

import java.util.UUID;

@AllArgsConstructor
public class ProxiedPlayerMuteEvent extends Event {

    private UUID operator;
    private String name, reason;
    private long duration;
}
