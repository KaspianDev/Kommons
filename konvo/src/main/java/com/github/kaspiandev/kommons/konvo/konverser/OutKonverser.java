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

package com.github.kaspiandev.kommons.konvo.konverser;

import com.github.kaspiandev.kommons.konvo.message.Message;

import java.io.PrintStream;

public class OutKonverser implements StringKonverser<PrintStream> {

    @Override
    public PrintStream getEntity() {
        return System.out;
    }

    @Override
    public void message(Message<String> message) {
        getEntity().println(message.getContents());
    }

}
