package DesignPattern.state;

/**
 * Permet de contrôler la communication entre les différents objets
 * Permet à un objet de modifier son comportement suite a un changement en son sein
 */
public class StatePatternDemo {
   public static void main(String[] args) {
      Context context = new Context();

      StartState startState = new StartState();
      startState.doAction(context);

      System.out.println(context.getState().toString());

      /**************************/
      System.out.println("\n");
      /**************************/

      StopState stopState = new StopState();
      stopState.doAction(context);

      System.out.println(context.getState().toString());
   }
}