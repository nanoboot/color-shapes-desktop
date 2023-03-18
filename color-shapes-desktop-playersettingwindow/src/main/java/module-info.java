
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
/**
 * @author <a href="mailto:robertvokac@nanoboot.org">Robert Vokac</a>
 * @since 0.0.0
 */
module colorshapes.desktop.playersettingswindow {
    requires lombok;
    requires powerframework.view;
    requires javafx.graphics;
    requires javafx.controls;
    requires powerframework.json;
    requires java.logging;
    requires powerframework.collections;
    requires powerframework.utils;
    requires colorshapes.engine.core;
    requires powerframework.time;
    requires powerframework.db;
    requires powerframework.random;
    requires colorshapes.engine.localisation.api;
    requires colorshapes.engine.services;
    requires colorshapes.desktop.window;
}
