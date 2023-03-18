
///////////////////////////////////////////////////////////////////////////////////////////////
// color-shapes-desktop: The desktop UI for on Color Lines Engine.
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

package org.nanoboot.colorshapes.desktop.view.eventprocessor;

import org.nanoboot.colorshapes.engine.flow.event.core.Event;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author <a href="mailto:robertvokac@nanoboot.org">Robert Vokac</a>
 * @since 0.0.0
 */
public class EventsExecutor extends Thread {

    private Queue<String> queue;
    private boolean exit = false;

    public EventsExecutor() {
        this.queue = new ConcurrentLinkedQueue<>();

        this.setName("commandExecutor");
        this.start();
    }

    public synchronized void notifyAboutChange(String change) {
//        System.out.println(1);
//        if (change.equals("EXIT")) {
//            exit = true;
//        }
//        System.out.println(2);
//        if (change.equals("CLEAR")) {
//            this.queue.clear();
//            return;
//        }
//        System.out.println(3);
        this.queue.add(change);
//        System.out.println(4);
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

    private void executeChanges() {//synchronized
        Event event;
        while (!this.queue.isEmpty()) {
            String instructions = queue.poll();
            event = new Event(instructions);
            EventExecutor eventExecutor = new EventExecutor(event);
            while (eventExecutor.isAlive()) {
                try {
                    sleep(25);
                } catch (InterruptedException ex) {
                    Logger.getLogger(EventsExecutor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }



}
