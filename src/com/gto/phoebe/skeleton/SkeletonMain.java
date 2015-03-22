package com.gto.phoebe.skeleton;

import com.gto.phoebe.logic.Glue;
import com.gto.phoebe.logic.Level;
import com.gto.phoebe.logic.Oil;
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
                ragacsfoltElhelyezese();
                break;
            case 3:
                robotOlajfoltraLep();
                break;
            case 4:
                robotRagacsfoltraLep();
                break;
            case 5:
                uresHelyreLepARobot();
                break;
            case 6:
                tiltottMezoreLepARobot();
                break;
            case 7:
                robotAthaladAKezdovonalon();
                break;
            case 8:

                break;
            case 9:

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
        Level level = new Level(100, 600, 600, new Point(100, 0), new Point(200, 0), skeletonInterface);
        level.gameCycle();

    }

    private void ragacsfoltElhelyezese() {
        System.out.println("Teszteset: 2. Ragacsfolt elhelyezése");
        Skeleton.init(2);
        UserInterface skeletonInterface = new SkeletonInterface();
        Level level = new Level(100, 600, 600, new Point(100, 0), new Point(200, 0), skeletonInterface);
        level.gameCycle();

    }

    private void robotAthaladAKezdovonalon() {
        System.out.println("Teszteset: 7. Robot athalad a kezdovonalon");
        Skeleton.init(7);
        UserInterface skeletonInterface = new SkeletonInterface();
        Level level = new Level(1, 100, 100, new Point(100, 101), new Point(120, 101), skeletonInterface);
        level.gameCycle();
    }

    private void tiltottMezoreLepARobot() {
        System.out.println("Teszteset: 6. Tiltott mezore lep a robot");
        Skeleton.init(6);
        UserInterface skeletonInterface = new SkeletonInterface();
        Level level = new Level(1, 200, 200, new Point(0, 0), new Point(0, 0), skeletonInterface);
        level.gameCycle();
    }

    private void uresHelyreLepARobot() {
        System.out.println("Teszteset: 5. Ures mezore lep a robot");
        Skeleton.init(5);
        UserInterface skeletonInterface = new SkeletonInterface();
        Level level = new Level(1, 200, 200, new Point(0, 0), new Point(0, 0), skeletonInterface);
        level.gameCycle();
    }

    private void robotOlajfoltraLep() {
        System.out.println("Teszteset: 3. Robot olajfoltra lép");
        Skeleton.init(3);
        UserInterface skeletonInterface = new SkeletonInterface();
        Level level = new Level(100, 600, 600, new Point(100, 0), new Point(200, 0), skeletonInterface);
        level.gameCycle();
        level.addActorToLevel(new Oil(new Point(110, 101), 1));
    }

    private void robotRagacsfoltraLep() {
        System.out.println("Teszteset: 4. Robot ragacsfoltra lép");
        Skeleton.init(3);
        UserInterface skeletonInterface = new SkeletonInterface();
        Level level = new Level(100, 600, 600, new Point(100, 0), new Point(200, 0), skeletonInterface);
        level.gameCycle();
        level.addActorToLevel(new Glue(new Point(110, 101), 1));

    }

}
