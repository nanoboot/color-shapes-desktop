
///////////////////////////////////////////////////////////////////////////////////////////////
// color-shapes-desktop: A logic game based on Color linez game.
// Copyright (C) 2016-2022 the original author or authors.
//
// This program is free software; you can redistribute it and/or
// modify it under the terms of the GNU General Public License
// as published by the Free Software Foundation; version 2
// of the License only.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
///////////////////////////////////////////////////////////////////////////////////////////////

package org.nanoboot.colorshapes.desktop.view;

import org.nanoboot.colorshapes.desktop.logic.NotifyableAboutChanges;
import static java.lang.Thread.sleep;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Robert Vokáč e-mail: robertvokac@nanoboot.org
 */
public class ChangeExecutor extends Thread implements NotifyableAboutChanges {

    private final View view;
    private final Queue<String> queue;
    private boolean exit = false;

    public ChangeExecutor(View view) {
        this.queue = new ConcurrentLinkedQueue<>();

        this.view = view;
        this.setName("commandExecutor");
        this.start();
    }

    public synchronized void notifyAboutChange(String change) {
        if (change.equals("EXIT")) {
            exit = true;
        }
        if (change.equals("CLEAR")) {
            this.queue.clear();
            return;
        }
        this.queue.add(change);
    }

    @Override
    public void run() {
        while (!exit) {
            if (!queue.isEmpty()) {
                executeChanges();
            }
            try {
                sleep(25);
            } catch (InterruptedException ex) {
            }

        }
    }

    private synchronized void executeChanges() {
        Change change;
        while (!this.queue.isEmpty()) {
            String instructions = queue.poll();
            change = new Change(instructions, view);
            while (change.isAlive()) {
                try {
                    sleep(25);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ChangeExecutor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
