package com.gto.phoebe.skeleton;

import com.gto.phoebe.logic.Level;
import com.gto.phoebe.ui.UserInterface;

import java.awt.*;
import java.util.Scanner;

public class SkeletonMain {

    public SkeletonMain() {
        System.out.println("Üdvözöllek a ghost_team_one Phoebe skeletonjában.");
    }

    public void menu() {
        printMenu();

        Scanner in = new Scanner(System.in);
        int input = in.nextInt();

        switch (input) {
            case 1:
                olajfoltElhelyezese();
                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
            case 5:

                break;
            case 6:
                tiltottMezoreLepARobot();
                break;
            case 7:
                robotAthaladAKezdovonalon();
                break;
            case 8:
                palyaLetrehozasa();
                break;
            case 9:
                jatekVege();
                break;
        }

        newTestcase();
    }

    private void newTestcase() {
        Scanner in = new Scanner(System.in);
        System.out.println("Szeretnél még tesztesetet futtatni? (igen/nem): ");
        String input = in.next();
        if ("igen".equals(input.toLowerCase())) {
            menu();
        } else if ("nem".equals(input.toLowerCase())) {
            return;
        }
    }

    private void printMenu() {
        System.out.println("Kérlek válassz tesztesetet a sorszám beírásával.");

        System.out.println("1. Olajfolt elhelyezése");
        System.out.println("2. Ragacsfolt elhelyezése");
        System.out.println("3. Robot olajfoltra lép");
        System.out.println("4. Robot ragacsfoltra lép");
        System.out.println("5. Üres helyre lép a robot");
        System.out.println("6. Tiltott mezőre lép a robot");
        System.out.println("7. Robot áthalad a kezdővonalon");
        System.out.println("8. Pálya létrehozása");
        System.out.println("9. Játék vége");
    }

    private void olajfoltElhelyezese() {
        System.out.println("Teszteset: 1. Olajfolt elhelyezése");
        Skeleton.init(1);
        UserInterface skeletonInterface = new SkeletonInterface();
        Level level = new Level(1, 600, 600, new Point(100, 0), new Point(200, 0), skeletonInterface);
        level.gameCycle();

    }

    private void jatekVege()    {
        System.out.println("Teszteset: 9. Jatek vege");
        Skeleton.init(9);
        UserInterface skeletonInterface = new SkeletonInterface();
        Level level = new Level(1, 50, 50, new Point(2, 1), new Point(2, 2), skeletonInterface);
        level.gameCycle();
        level.getWinner();
    }

    private void palyaLetrehozasa()    {
        System.out.println("Teszteset: 8. Palya letrehozasa");
        Skeleton.init(8);
        UserInterface skeletonInterface = new SkeletonInterface();
        Level level = new Level(1, 10, 10, new Point(2, 1), new Point(2, 2), skeletonInterface);
    }

    private void robotAthaladAKezdovonalon()    {
        System.out.println("Teszteset: 7. Robot athalad a kezdovonalon");
        Skeleton.init(7);
        UserInterface skeletonInterface = new SkeletonInterface();
        Level level = new Level(1, 100, 100, new Point(100, 101), new Point(120, 101), skeletonInterface);
        level.gameCycle();
    }

    private void tiltottMezoreLepARobot()   {
        System.out.println("Teszteset: 6. Tiltott mezore lep a robot");
        Skeleton.init(6);
        UserInterface skeletonInterface = new SkeletonInterface();
        Level level = new Level(1, 200, 200, new Point(0, 0), new Point(0, 0), skeletonInterface);
        level.gameCycle();
    }
}
