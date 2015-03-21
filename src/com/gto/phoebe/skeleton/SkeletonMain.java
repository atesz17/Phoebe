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

                break;
            case 7:

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
}
