
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

package org.nanoboot.colorshapes.desktop.domain.domaininitializing;

import org.nanoboot.colorshapes.desktop.domain.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import org.nanoboot.powerframework.collections.DictionaryKeyIterator;
import org.nanoboot.powerframework.database.Database;
import org.nanoboot.powerframework.database.DatabaseConnection;
import org.nanoboot.powerframework.database.SqlCommandQueue;
import org.nanoboot.powerframework.json.JsonObject;

/**
 * Represents class used to install data, if the game is launched for the first
 * time.
 *
 * @author Robert Vokáč e-mail: robertvokac@nanoboot.org
 */
public class DomainInitializing {

    /**
     * Constructor
     *
     * Not meant to be instantiated.
     */
    private DomainInitializing() {
// Not meant to be instantiated.
    }

    /**
     * Installs basic data.
     *
     * @param databaseConnection
     */
    public static void install(DatabaseConnection databaseConnection) {
        createTables(databaseConnection);
        insertIntoDatabaseTextSourceData();
        initBasicData();

    }

    private static void createTables(DatabaseConnection databaseConnection) {
        String commandsToCreateTables;



        StringBuilder resultBuilder = new StringBuilder("");


        try {

                    InputStream inputStream = DomainInitializing.class.getResourceAsStream("/createtables.sql");
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));



                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    resultBuilder.append(line);
                    resultBuilder.append("\n");
                }


        } catch (IOException e) {
            e.printStackTrace();
        }

        commandsToCreateTables = resultBuilder.toString();
        
        databaseConnection.execute(commandsToCreateTables);
    }

    private static void insertIntoDatabaseTextSourceData() {
        initTextConstantTypeTable();

        DatabaseConnection databaseConnection = Database.createDatabaseConnection("data");

        ArrayList<JsonObject> list;
        list = getJsonObjectsAsArrayList();
        for (JsonObject jsonObject : list) {
            JsonObject language = jsonObject.getObject("language");
            JsonObject values = jsonObject.getObject("values");

            String code = language.getString("code");
            String nameInEnglish = language.getString("name in English");
            String originalName = language.getString("original name");

            StringBuilder stringBuilder = new StringBuilder("INSERT INTO language VALUES(null,'");
            stringBuilder.append(code).append("','");
            stringBuilder.append(originalName).append("','");
            stringBuilder.append(nameInEnglish).append("');");
            int languageId = databaseConnection.execute(stringBuilder.toString());

            DictionaryKeyIterator dictionaryKeyIterator = values.getKeyIterator();
            SqlCommandQueue sqlCommandQueue = new SqlCommandQueue();
            while (dictionaryKeyIterator.hasNext()) {
                String key = dictionaryKeyIterator.getNextKey();
                String value = values.getString(key);
                StringBuilder insertCommand = new StringBuilder();
                insertCommand.append("INSERT INTO text_constant VALUES(null,");
                insertCommand.append(key).append(",");
                insertCommand.append(languageId).append(",'");
                insertCommand.append(value).append("')");
                sqlCommandQueue.add(insertCommand.toString());
            }
            databaseConnection.executeMoreCommands(sqlCommandQueue);
        }
    }

    private static void initTextConstantTypeTable() {
        InputStream input = DomainInitializing.class.getResourceAsStream("/languagePacks/types.json");
        JsonObject types = new JsonObject(new Scanner(input).useDelimiter("/").next());
        DictionaryKeyIterator dictionaryKeyIterator = types.getKeyIterator();

        SqlCommandQueue sqlCommandQueue = new SqlCommandQueue();

        while (dictionaryKeyIterator.hasNext()) {
            String key = dictionaryKeyIterator.getNextKey();
            String description = types.getString(key);

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("INSERT INTO text_constant_type VALUES (");
            stringBuilder.append(key).append(",'");
            stringBuilder.append(description).append("')");
            sqlCommandQueue.add(stringBuilder.toString());
        }
        Database.createDatabaseConnection("data").executeMoreCommands(sqlCommandQueue);
    }

    private static ArrayList<JsonObject> getJsonObjectsAsArrayList() {
        ArrayList<JsonObject> list;
        list = new ArrayList<>();
        String[] codes = {"en", "cz"};
        for (String code : codes) {
            InputStream input = DomainInitializing.class.getResourceAsStream("/languagePacks/" + code + ".json");
            list.add(new JsonObject(new Scanner(input, "utf-8").useDelimiter("/").next()));
        }
        return list;
    }

    private static void initBasicData() {
        initExplodingShapeTypeTable();
        initBallLightingTable();
        initBallMoveEffectTable();

        initApplicationTable();

        initGameCompositionTable();
    }

    private static void initExplodingShapeTypeTable() {
        String[] explodingShapeTypeString = {"LINE", "BLOCK", "RING", "SQUARE", "CUSTOM"};
        for (String value : explodingShapeTypeString) {
            ExplodingShapeTypeTable.saveExplodingShapeType(value);
        }
    }

    private static void initBallLightingTable() {
        String[] ballLightingString = {"none", "above", "ahead"};
        for (String value : ballLightingString) {
            BallLightingTable.saveBallLighting(value);
        }
    }

    private static void initBallMoveEffectTable() {
        String[] ballMoveEffectString = {"no", "arrow", "highlight"};
        for (String value : ballMoveEffectString) {
            BallMoveEffectTable.saveBallMoveEffect(value);
        }
    }

    private static void initApplicationTable() {
        int installationUniqueNumber = org.nanoboot.powerframework.pseudorandom.PseudoRandomGenerator.getInstance().getInt(0, 999999);
        int currentUniversalDateTimeID = org.nanoboot.colorshapes.desktop.domain.UniversalDateTimeTable.saveCurrentUniversalDateTime();

        ApplicationTable.saveApplication(installationUniqueNumber, currentUniversalDateTimeID, 0, 1, 10, 100);
    }

    private static int initGameCompositionTable() {
        ShapeTable.saveEmptyShape();
        int boardShapeId = ShapeTable.saveShape(9, 9, "default");
        int boardId = BoardTable.saveBoard(0, 0, 0, 0, boardShapeId);

        int[] colourFrequency = new int[17];
        int i;
        for (i = 1; i <= 7; i++) {
            colourFrequency[i] = 1;
        }
        for (i = 8; i <= 16; i++) {
            colourFrequency[i] = 0;
        }
        int colourFrequencyId = ColourFrequencyTable.saveColourFrequency(colourFrequency);
        int ballFactoryId = BallFactoryTable.saveBallFactory(colourFrequencyId, 0, 0, 0, 1, 0, 1, 0, 0, 0);

        int ballThrowerId = BallThrowerTable.saveBallThrower(5, 3, 1, 0, 0, false);

        int explodingShapeTypeId = ExplodingShapeTypeTable.getId("LINE");
        int ballDetonatorID = BallDetonatorTable.saveBallDetonator(explodingShapeTypeId, 5, 0, 0);

        int otherId = OtherTable.saveOther(false, false, false);

        return GameCompositionTable.saveGameComposition(boardId, ballFactoryId, ballThrowerId, ballDetonatorID, otherId);
    }

}
