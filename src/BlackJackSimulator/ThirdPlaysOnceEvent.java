/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlackJackSimulator;

import FirstSimulator.DequeueEvent;
import abstractsimulator.AbstractEvent;
import abstractsimulator.AbstractSimulator;

/**
 *
 * @author IT
 */
public class ThirdPlaysOnceEvent extends AbstractEvent {

    public ThirdPlaysOnceEvent(double time) {
        super(time);
        this.setTag("Jugador tres juega");
    }

    @Override
    public void execute(AbstractSimulator simulator) {
        boolean[] escenario = ((BlackJackSimulator) simulator).getEscenario();
        String newCard = ((BlackJackSimulator) simulator).getRandomCard();

        String[] newHand = ((BlackJackSimulator) simulator).setsThirdPlayerNewCard(newCard);
        boolean wins = ((BlackJackSimulator) simulator).doesThirdPlayerWin(newHand);
        int winsCount = ((BlackJackSimulator) simulator).getThirdWins();

        if (!wins) {
            simulator.report += "Jugador 3 lost!!!" + " \n";
        } else {
            simulator.report += "Jugador 3 wins!!!" + " \n";
            ((BlackJackSimulator) simulator).setThirdWins(winsCount + 1);

        }
        simulator.report += "Puntos del dealer   >>>> " + ((BlackJackSimulator) simulator).getCardsBestValue(((BlackJackSimulator) simulator).getDealer()) + " \n";
        simulator.report += "Puntos del thirdPlayer   >>>> " + ((BlackJackSimulator) simulator).getCardsBestValue(newHand) + " \n";
        // simulator.report +=((BlackJackSimulator) simulator).printEscenario();
        simulator.setSystemCount(simulator.getSystemCount() + 1);
        simulator.insert(new ThirdPlaysOnceEvent(this.getTime() + 1));

    }
}
