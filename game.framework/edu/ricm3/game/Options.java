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
package edu.ricm3.game;

public class Options {
  /*
   * You want to use double buffering... 
   * Trust us, but you need to understand what that means.
   * Look at the class GameView.
   */
  public static final boolean USE_DOUBLE_BUFFERING = true;
  
  /*
   * We want to target 24 frame per seconds (fps),
   * which is the following period in milliseconds
   *   period = (1000.0 / 24.0)
   */
  static final double FPS = 30.0;
  static final int REPAINT_DELAY = (int) (1000.0 / FPS);

}
