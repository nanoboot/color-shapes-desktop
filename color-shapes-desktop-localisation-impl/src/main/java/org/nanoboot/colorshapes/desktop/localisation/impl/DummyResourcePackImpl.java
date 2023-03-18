
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

package org.nanoboot.colorshapes.desktop.localisation.impl;

import org.nanoboot.colorshapes.engine.localisation.api.ResourcePack;

import java.util.HashMap;
import java.util.Map;
/**
 * @author <a href="mailto:robertvokac@nanoboot.org">Robert Vokac</a>
 * @since 0.0.0
 */
public class DummyResourcePackImpl implements ResourcePack {
    private static final DummyResourcePackImpl instance = new DummyResourcePackImpl();
    private static final Map<String, String> map = new HashMap<>();

    public static DummyResourcePackImpl getInstance() {
        return instance;
    }

    static {
        map.put("1","Message");
        map.put("common.alert","Alert");
        map.put("3","Request");
        map.put("50","Colour");
        map.put("51","Light green");
        map.put("52","Red");
        map.put("53","Dark blue");
        map.put("54","Yellow");
        map.put("55","Light blue");
        map.put("56","Purple");
        map.put("57","Brown");
        map.put("58","Pink");
        map.put("59","Green");
        map.put("60","Gold");
        map.put("61","Orange");
        map.put("62","White");
        map.put("63","Grey");
        map.put("64","Black");
        map.put("65","Blue");
        map.put("66","Army green");
        map.put("loginwindow.login","Login");
        map.put("common.nick","Nick");
        map.put("common.password","Password");
        map.put("loginwindow.keep-me-logged","Keep me logged");
        map.put("loginwindow.sign-in","Sign in");
        map.put("loginwindow.view-help","View help");
        map.put("common.create-new-player","Create new player");
        map.put("loginwindow.invalid-nick-or-password","Invalid nick or password");
        map.put("201","Creating new player");
        map.put("211","Display language");
        map.put("214","Confirm password");
        map.put("221","Password is not mandatory.");
        map.put("222","If you want to no password, let password cell empty.");
        map.put("223","Nick and password can contain only a-z,A-Z and 0-9 chars.");
        map.put("224","Nick and password maximum lenght can be 16 chars");
        map.put("231","Colour skin");
        map.put("241","Too long nick.");
        map.put("242","Too long password.");
        map.put("243","Nick cell is empty.");
        map.put("244","Nick already exists.");
        map.put("245","Password and Confirm password do not match.");
        map.put("1001","Game");
        map.put("1002","Welcome, new player. Press F1 to see Help. Good luck.");
        map.put("1003","Are you sure to log out?");
        map.put("1004","Are you sure to exit?");
        map.put("1011","New with current composition");
        map.put("1012","New with custom composition");
        map.put("1013","Save");
        map.put("1014","Load");
        map.put("1021","Log out");
        map.put("1022","Exit");
        map.put("1101","Options");
        map.put("1111","Results");
        map.put("1112","Information about current game");
        map.put("1113","Show next balls");
        map.put("1114","Hide next balls");
        map.put("1115","Player settings");
        map.put("1121","Switch to Fullscreen mode");
        map.put("1122","Switch to Window mode");
        map.put("1201","Help");
        map.put("1211","About this game");
        map.put("10001","Signing");
        map.put("10013","Update player nick");
        map.put("10021","Old password");
        map.put("10022","New password");
        map.put("10023","Confirm new password");
        map.put("10024","Update password");
        map.put("10101","Look");
        map.put("10102","Zoom");
        map.put("10113","Ball lighting");
        map.put("10114","None");
        map.put("10115","From above");
        map.put("10116","From the side");
        map.put("10121","Show line around ball");
        map.put("10122","Show, where a ball can be moved");
        map.put("10123","Highlight cells after ball exploded");
        map.put("10124","Ball move effect");
        map.put("10125","No extra effect");
        map.put("10126","Arrow");
        map.put("10127","Highlight cells");
        map.put("10140","Load default");
        map.put("10141","Save Look changes");
        map.put("10601","Look changes will take effect after next sign in.");
        map.put("10701","Optional data");
        map.put("10711","Name");
        map.put("10712","Surname");
        map.put("10713","Sex");
        map.put("10714","Man");
        map.put("10715","Woman");
        map.put("10716","Unknown");
        map.put("10717","Date of birth");
        map.put("10718","Year");
        map.put("10719","Month");
        map.put("10720","Day");
        map.put("10791","Save Optional data changes");
        map.put("10801","Other");
        map.put("10811","Time zone");
        map.put("10891","Save Other Changes");
        map.put("10901","Before signing");
        map.put("10991","Save Before signing changes");
        map.put("20000","Custom composition");
        map.put("20001","Yes");
        map.put("20002","No");
        map.put("20003","Begin");
        map.put("20004","Load");
        map.put("20005","Default");
        map.put("20006","Random");
        map.put("20100","Board");
        map.put("20101","Height");
        map.put("20102","Width");
        map.put("20103","Grid probability");
        map.put("20104","Grid count");
        map.put("20105","Wall probability");
        map.put("20106","Wall count");
        map.put("20107","Set holes");
        map.put("20108","Clear all holes");
        map.put("20121","Height must be 3 at least.");
        map.put("20122","Height must be 32 at most.");
        map.put("20123","Width must be 3 at least.");
        map.put("20124","Width must be 32 at most.");
        map.put("20125","For 0 Wall probability cannot be Wall count higher than 0.");
        map.put("20126","For 0 Grid probability cannot be Grid count higher than 0.");
        map.put("20127","Probability cannot be higher than 100 percent.");
        map.put("20128","Probability cannot be lower than 0 percent.");
        map.put("20129","Wall count cannot be higher than 4 * boardHeight * boardWidth.");
        map.put("20130","Grid count cannot be higher than boardHeight * boardWidth.");
        map.put("20131","You have changed boardHeight or boardWidth after you set holes. Clear all holes or set the right dimensions");
        map.put("20200","Balls");
        map.put("20201","Colour frequencies");
        map.put("20202","0 frequency for all colours is forbidden.");
        map.put("20221","Values");
        map.put("20222","Value -2 frequency");
        map.put("20223","Value -1 frequency");
        map.put("20224","Value 0 frequency");
        map.put("20225","Value 1 frequency");
        map.put("20226","Value 2 frequency");
        map.put("20240","Other");
        map.put("20241","Colour ball frequency");
        map.put("20242","Rainbow ball frequency");
        map.put("20243","Unmoveable balls probability");
        map.put("20244","Unbreakable balls probability");
        map.put("20300","Thrower");
        map.put("20301","Count of balls thrown at the start of the game:");
        map.put("20302","Count of balls throwing during the game");
        map.put("20303","Ball frequency");
        map.put("20304","Automatic bomb frequency");
        map.put("20305","Manual bomb frequency");
        map.put("20306","Show the positions of the next balls");
        map.put("20400","Detonator");
        map.put("20401","Shape to explode");
        map.put("20402","Line");
        map.put("20403","Block");
        map.put("20404","Rectangle");
        map.put("20405","Ring");
        map.put("20406","Custom");
        map.put("20411","Minimum lenght");
        map.put("20412","Minimum size");
        map.put("20421","Set custom shape to explode");
        map.put("20431","Ball count must be 3 at least.");
        map.put("20432","Balls must be connected.");
        map.put("20433","You cannot use this shape. This shape the same as line.");
        map.put("20434","You cannot use this shape. This shape the same as ring.");
        map.put("20435","You cannot use this shape. This shape the same as rectangle.");
        map.put("20500","Other");
        map.put("20501","Allow step back");
        map.put("20502","Free mode");
        map.put("20503","Trainer");
        map.put("30001","Game end. Result:");

    }

    @Override
    public String getLanguageCode() {
        return "en";
    }

    @Override
    public String getOriginalLanguageName() {
        return "English";
    }

    @Override
    public String getEnglishLanguageName() {
        return null;
    }

    @Override
    public String getText(String s) {
        if (map.containsKey(s)) {
            return map.get(s);
        }
        return "NOTFOUND";
    }

    @Override
    public String getTextOrDefault(String s, String s1) {
        return null;
    }

    @Override
    public void setText(String s, String s1) {

    }

    @Override
    public void addText(String s, String s1) {

    }

    @Override
    public void removeText(String s) {

    }
}
