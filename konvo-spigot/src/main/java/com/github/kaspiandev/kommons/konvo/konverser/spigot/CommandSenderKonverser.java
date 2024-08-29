/*
 * Copyright (C) 2024  Kaspian
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */

package com.github.kaspiandev.kommons.konvo.konverser.spigot;

import com.github.kaspiandev.kommons.konvo.konverser.StringKonverser;
import com.github.kaspiandev.kommons.konvo.message.Message;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.command.CommandSender;

public class CommandSenderKonverser {

    private final CommandSender entity;
    private final CommandSenderComponentKonverser componentKonverser;
    private final CommandSenderStringKonverser stringKonverser;

    public CommandSenderKonverser(CommandSender entity) {
        this.entity = entity;
        this.componentKonverser = new CommandSenderComponentKonverser();
        this.stringKonverser = new CommandSenderStringKonverser();
    }

    public CommandSenderComponentKonverser getAsComponent() {
        return componentKonverser;
    }

    public void messageComponent(Message<BaseComponent[]> message) {
        getAsComponent().message(message);
    }

    public CommandSenderStringKonverser getAsString() {
        return stringKonverser;
    }

    public void messageString(Message<String> message) {
        getAsString().message(message);
    }

    public class CommandSenderComponentKonverser implements ComponentKonverser<CommandSender> {

        @Override
        public CommandSender getEntity() {
            return entity;
        }

        @Override
        public void message(Message<BaseComponent[]> message) {
            entity.spigot().sendMessage(message.getContents());
        }

    }

    public class CommandSenderStringKonverser implements StringKonverser<CommandSender> {

        @Override
        public CommandSender getEntity() {
            return entity;
        }

        @Override
        public void message(Message<String> message) {
            entity.sendMessage(message.getContents());
        }

    }

}
