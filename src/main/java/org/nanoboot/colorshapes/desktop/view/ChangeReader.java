
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

/**
 *
 * @author Robert Vokáč e-mail: robertvokac@nanoboot.org
 */
public class ChangeReader {

    private final String[] splitCommand;
    private final int COUNTOFWORDS;

    public int getCountOfWords() {
        return COUNTOFWORDS;
    }
    private int currentIndex = -1;

    /**
     * Constructor
     *
     * @param command
     */
    public ChangeReader(String command) {
        splitCommand = command.split("\\s+");
        COUNTOFWORDS = splitCommand.length;
    }

    /**
     *
     * @return
     */
    public String getNextWord() {
        this.currentIndex++;
        return this.splitCommand[this.currentIndex];
    }

    public String getWord(int index) {
        if (index >= this.COUNTOFWORDS) {
            return "";
        }
        return this.splitCommand[index];
    }

    /**
     *
     * @return
     */
    public int getNextWordAsInt() {
        return Integer.parseInt(getNextWord());
    }

    /**
     *
     * @return
     */
    public boolean hasNextWord() {
        return (this.currentIndex) + 2 <= this.COUNTOFWORDS;
    }

    /**
     *
     * @return
     */
    public int getCurrentIndex() {
        return this.currentIndex;
    }

    /**
     *
     * @return
     */
    public String toStringAndMarkWordWithCurrentIndex() {
        StringBuilder stringBuilder = new StringBuilder();
        final String SPACE = " ";
        for (int i = 0; i < splitCommand.length; i++) {
            String element = splitCommand[i];
            if (i == this.currentIndex) {
                stringBuilder.append('[');
            }
            stringBuilder.append(element);
            if (i == this.currentIndex) {
                stringBuilder.append(']');
            }
            if (i != (splitCommand.length - 1)) {
                stringBuilder.append(SPACE);
            }
        }
        return stringBuilder.toString();
    }

}
