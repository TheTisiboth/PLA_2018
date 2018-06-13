/*
 * Educational software for a basic game development
 * Copyright (C) 2018  Pr. Olivier Gruber
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
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.ricm3.game.sample;

import java.awt.Dimension;

import edu.ricm3.game.GameUI;

public class GameMain {

  public static void main(String[] args) {

    // construct the game elements: model, controller, and view.
    Model model = new Model();
    Controller controller = new Controller(model);
    View view = new View(model,controller);

    Dimension d = new Dimension(1024, 768);
    new GameUI(model,view,controller,d);
    
    // notice that the main thread will exit here,
    // but not your program... hence the hooking
    // of the window events to System.exit(0) when
    // the window is closed. See class WindowListener.

    /*
     * *** WARNING *** WARNING *** WARNING *** WARNING ***
     * If you do something here, on this "main" thread,
     * you will have parallelism and thus race conditions.
     * 
     *           ONLY FOR ADVANCED DEVELOPERS
     *           
     * *** WARNING *** WARNING *** WARNING *** WARNING ***
     */
    return;
  }
}
