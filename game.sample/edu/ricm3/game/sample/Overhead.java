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

/**
 * This class is to simulate an overhead in the model step computation.
 * This should not be used in a real game, as you will have plenty enough
 * overhead with the real game itself, no need to add some fake one.
 * 
 * @author Pr. Olivier Gruber
 */
public class Overhead {
  
  private static final long NOPS = 1000000L;

  private long m_count = 0;
  private long m_sum = 0;

  void inc() {
    m_count+=NOPS;
    System.out.println("Overhead at " + m_count);
  }
  
  void dec() {
    m_count = m_count - NOPS;
    if (m_count<0)
      m_count=0;
    System.out.println("Overhead at " + m_count);
  }
  
  long op(long i) {
    for (int o=0;o<5;o++)
      new String("Waster");
    return i + i * i;
  }

  /*
   * *** WARNING *** WARNING *** WARNING *** WARNING  
   * long callbacks will kill your frame-per-second performance
   * the game will get sluggish...
   * avoid as much as possible.
   */
  long overhead() {
    for (long i = 0; i < m_count; i++)
      m_sum += op(i);
    return m_sum;
  }

}
