import java.util.Scanner;

public class GameManager {
    Player player1;
    Player player2;

    static Player currentPlayer;
    static boolean isPlaying;
    static boolean isFirstRound;
    static boolean isViewingCaravan;
    static boolean isViewingOpponent;

    static Caravan caravanA;
    static Caravan caravanB;
    static Caravan caravanC;

    static Scanner input;

    public GameManager() {
        newGameSetup();
    }

    private void newGameSetup() {
        input = new Scanner(System.in);
        isPlaying = true;
        isViewingCaravan = false;
        isFirstRound = true;

        player1 = new Player(1);
        player2 = new Player(2);
        currentPlayer = player1;

        caravanA = new Caravan("BONEYARD / DAYGLOW");
        caravanB = new Caravan("REDDING / NEW RENO");
        caravanC = new Caravan("SHADY SANDS/THE HUB");
    }

    public void startGame() {
        showWelcomeMessage();
        showTableView();
        while (isPlaying){
            String choice = input.nextLine().toUpperCase();
            switch (choice) {
                case "O" -> showTableOptions();
                case "V" -> chooseCaravan();
                case "H" -> showHand();
                case "R" -> showRulebook();
                case "T" -> showTableView();
                case "Q" -> quitGame();
                default  -> System.out.print("INVALID INPUT.\n\n>> ");
            }
        }
    }

    private void showWelcomeMessage() {
        System.out.print("""
                --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- ---
                
                              WELCOME TO
                                    __   __          __
                               /\\  /__` /  ` | | __ |__)  /\\  \\  /  /\\  |\\ |
                              /--\\ .__/ \\__, | |    |  \\ /--\\  \\/  /--\\ | \\|
                
                                A TEXT-BASED TAKE ON THE CARD GAME CARAVAN
                
                --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- ---
                
                ENTER ANY KEY TO CONTINUE
                >>\s""");
        input.nextLine();
        System.out.println();
    }

    private void showTableView() {
        System.out.printf("""
                --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- ---
                                             
                                               PLAYER 2 CARAVANS
                                  + --- --- --- + --- --- --- + --- --- --- +
                                  |             |             |             |
                                  |             |             |             |
                                  |      %-4d   |      %-4d   |      %-4d   |
                                  |             |             |             |
                                  |             |             |             |
                                  + --- --- --- + --- --- --- + --- --- --- +
                                  |      A      |      B      |      C      |
                                  + --- --- --- + --- --- --- + --- --- --- +
                                  |             |             |             |
                                  |             |             |             |
                                  |      %-4d   |      %-4d   |      %-4d   |
                                  |             |             |             |
                                  |             |             |             |
                                  + --- --- --- + --- --- --- + --- --- --- +
                                               PLAYER 1 CARAVANS
                                               
                                             IT IS PLAYER %d'S TURN
                                     ENTER 'O' FOR OPTIONS OR TYPE COMMAND
                                    
                >>\s""",
                caravanA.getPlayer2BidTotal(), caravanB.getPlayer2BidTotal(), caravanC.getPlayer2BidTotal(),
                caravanA.getPlayer1BidTotal(), caravanB.getPlayer1BidTotal(), caravanC.getPlayer1BidTotal(), currentPlayer.getRefID());
    }

    private void showTableOptions() {
        System.out.print("""
                
                + --- --- --- --- --- --- +
                |         OPTIONS:        |
                + --- --- --- --- --- --- +
                | V) VIEW CARAVAN ROUTE   |
                | H) CHECK CURRENT HAND   |
                | R) RULEBOOK             |
                | T) RELOAD TABLE         |
                | Q) QUIT PROGRAM         |
                + --- --- --- --- --- --- +
                              
                >>\s""");
    }

    private void chooseCaravan() {

        System.out.print("WHICH CARAVAN ROUTE? A, B, C: ");
        String choice = input.nextLine().toUpperCase();

        switch (choice) {
            case "A" -> showCaravanView(caravanA);
            case "B" -> showCaravanView(caravanB);
            case "C" -> showCaravanView(caravanC);
            default  -> System.out.print("INVALID INPUT.\n\n>> ");
            }
    }

    private void showCaravanView(Caravan caravan) {
        Player otherPlayer;
        showCaravan(caravan, currentPlayer);
        isViewingCaravan = true;

        if (currentPlayer == player1) {
            otherPlayer = player2;
        } else {
            otherPlayer = player1;
        }

        while (isViewingCaravan) {
            String choice = input.nextLine().toUpperCase();
            switch (choice) {
                case "O" -> showCaravanOptions();
                case "P" -> playCard(caravan);
                case "D" -> showDiscardOptions(caravan);
                case "S" -> {
                    showCaravan(caravan, otherPlayer);
                    isViewingOpponent = true;
                }
                case "R" -> showRulebook();
                case "C" -> {
                    showCaravan(caravan, currentPlayer);
                    isViewingOpponent = false;
                }
                case "T" -> {
                    showTableView();
                    isViewingCaravan = false;
                    isViewingOpponent = false;
                }
                default  -> System.out.print("INVALID INPUT.\n\n>> ");
            }
        }
    }

    private void showCaravanOptions() {
        System.out.print("""
                
                + --- --- --- --- --- --- +
                |         OPTIONS:        |
                + --- --- --- --- --- --- +
                | P) PLAY A CARD          |
                | D) DISCARD OPTIONS      |
                | S) SWITCH PLAYER VIEW   |
                | R) RULEBOOK             |
                | C) RELOAD CARAVAN VIEW  |
                | T) BACK TO TABLE VIEW   |
                + --- --- --- --- --- --- +
                              
                >>\s""");
    }

    private void showCaravan(Caravan caravan, Player targetPlayer){
        System.out.printf("""
                --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- ---
                
                                           >> %-20s<<
                         
                  PLAYER %d CARAVAN:
                == == == == == == ==
                
                %s
                
                     TOTAL: %d
                == == == == == == ==
                
                
                PLAYER %d CURRENT HAND:
                == == == == == == ==
                %s
                
                                     ENTER 'O' FOR OPTIONS OR TYPE COMMAND
                
                >>\s""",
                caravan.toString(),
                targetPlayer.getRefID(),
                Node.printTree(caravan.getCardRoot(targetPlayer)),
                caravan.getBidTotal(targetPlayer),
                currentPlayer.getRefID(),
                currentPlayer.hand);
    }

    public void playCard(Caravan caravan) {
        boolean isFirstCard = false;
        boolean isSecondCard = false;
        Player otherPlayer = player2;
        Node bottomNode = caravan.getCardRoot(currentPlayer);
        Card topCard = null;

        System.out.print("ENTER CARD TO PLAY: ");
        String cardName = input.nextLine();

        Card playedCard = Card.findCard(cardName);
        if (playedCard == null || !currentPlayer.hand.contains(playedCard)) {
            System.out.print("INVALID INPUT.\nCARD DOES NOT EXIST OR IS NOT IN HAND.\n\n>> ");
            return;
        }

        // determine if playing first or second card
        if (bottomNode == null) {
            isFirstCard = true;
        } else {
            topCard = Node.getTopCard(bottomNode);
            if (topCard == bottomNode.getData()) {
                isSecondCard = true;
            }
        }

        if (isFirstRound) {
            playCardFirstRound(caravan, playedCard);
            return;
        } else if (isFirstCard) {
            if (playedCard.getIsFaceCard() && !isViewingOpponent) {
                System.out.print("INVALID INPUT.\nFIRST CARD ON A CARAVAN CANNOT BE FACE CARD.\n\n>> ");
                return;
            } else {
                caravan.setCardRoot(currentPlayer, new Node(playedCard));
                caravan.setSuit(currentPlayer, playedCard.getSuit());
            }

        } else if (playedCard.getIsFaceCard()) {
            System.out.print("ENTER CARD TO ATTACH TO: ");
            String targetCardName = input.nextLine();
            Card targetCard = Card.findCard(targetCardName);
            Node targetNode;
            if (isViewingOpponent) {
                if (currentPlayer.getRefID() == 2) {
                    otherPlayer = player1;
                }
                targetNode = Node.findNodeByCard(caravan.getCardRoot(otherPlayer), targetCard);
            } else {
                targetNode = Node.findNodeByCard(caravan.getCardRoot(currentPlayer), targetCard);
            }
            if (targetCard == null || targetNode == null) {
                System.out.print("INVALID INPUT.\nTARGET CARD DOES NOT EXIST OR IS NOT IN CARAVAN.\n\n>> ");
                return;
            } else {
                switch (playedCard.getRank()) {
                    case JACK -> {
                        if (targetCard.getIsFaceCard()) {
                            System.out.print("INVALID INPUT.\nONLY PLACE JACKS ON ACES AND NUMBERS.\n\n>> ");
                            return;
                        }
                        if (targetNode.getParent() == null && targetNode.getLeft() == null) {
                            if (isViewingOpponent && otherPlayer != null) {
                                caravan.setCardRoot(otherPlayer, null);
                            } else {
                                caravan.setCardRoot(currentPlayer, null);
                            }
                        } else {
                            targetNode.removeNode();
                        }
                    }
                    case QUEEN -> {
                        if (topCard != targetNode.getData()) {
                            System.out.print("INVALID INPUT.\nCAN ONLY PLACE QUEENS ON MOST RECENT CARD.\n\n>> ");
                            return;
                        }
                        targetNode.addNode(new Node(playedCard));
                        if (isViewingOpponent) {
                            caravan.setSuit(otherPlayer, playedCard.getSuit());
                            caravan.reverseSequence(otherPlayer);
                        } else {
                            caravan.setSuit(currentPlayer, playedCard.getSuit());
                            caravan.reverseSequence(currentPlayer);
                        }
                    }
                    case KING -> targetNode.addNode(new Node(playedCard));
                }
            }
        } else if (isViewingOpponent) {
            System.out.print("INVALID INPUT.\nCAN ONLY PLAY FACE CARDS ON OPPONENT CARAVANS.\n\n>> ");
            isViewingOpponent = false;
            return;
        } else if (topCard.getValue() == playedCard.getValue()){
            System.out.print("INVALID INPUT.\nCANNOT PLAY SAME NUMBER ON TOP OF EACH OTHER\n\n>> ");
            return;
        } else if ((caravan.getSequence(currentPlayer) ==  1 && topCard.getValue() > playedCard.getValue())
                || (caravan.getSequence(currentPlayer) == -1 && topCard.getValue() < playedCard.getValue())) {
            //Sequence not followed, only allow card if the suit matches caravan suit
            if (caravan.getSuit(currentPlayer) == playedCard.getSuit()) {
                    bottomNode.addNode(new Node(playedCard));
                    caravan.setSuit(currentPlayer, playedCard.getSuit());
                    // set new sequence
                    if (topCard.getValue() > playedCard.getValue()){
                        caravan.setSequence(currentPlayer, -1);
                    } else {
                        caravan.setSequence(currentPlayer, 1);
                    }
            } else {
                System.out.print("INVALID INPUT.\nSEQUENCE MUST CONTINUE UNLESS MATCHING SUIT.\n\n>> ");
                return;
            }
        } else {
            bottomNode.addNode(new Node(playedCard));
            caravan.setSuit(currentPlayer, playedCard.getSuit());
            if (isSecondCard) {
                if (bottomNode.getData().getValue() > playedCard.getValue()){
                    caravan.setSequence(currentPlayer, -1);
                } else {
                    caravan.setSequence(currentPlayer, 1);
                }
            }
        }
        currentPlayer.hand.remove(playedCard);
        currentPlayer.hand.add(currentPlayer.cardDeck.drawCard());
        caravan.setBidTotal(currentPlayer, Node.calculateBid(caravan.getCardRoot(currentPlayer)));
        if (otherPlayer != null) {
            caravan.setBidTotal(otherPlayer, Node.calculateBid(caravan.getCardRoot(otherPlayer)));
        }
        swapTurn();
    }

    private void swapTurn() {

        switch (currentPlayer.getRefID()) {
            case 1 -> currentPlayer = player2;
            case 2 -> currentPlayer = player1;
        }
        if (isFirstRound) {
            checkForFirstRound();
        }
        showTableView();
        checkForWin();
        isViewingOpponent = false;
        isViewingCaravan = false;
    }

    private void showDiscardOptions(Caravan caravan){
        System.out.print("ENTER 'D' TO DISCARD SINGLE CARD FROM HAND.\nENTER 'X' TO DISCARD ENTIRE CARAVAN.\n\n>> ");
        String choice = input.nextLine().toUpperCase();
        switch (choice) {
            case "D" -> {
                System.out.print("ENTER NAME OF CARD TO DISCARD: ");
                String targetCard = input.nextLine();
                if (Card.findCard(targetCard) != null){
                    currentPlayer.discard(Card.findCard(targetCard));
                    System.out.printf("%s DISCARDED.\n", Card.findCard(targetCard));
                    swapTurn();
                } else {
                    System.out.print("INVALID INPUT.\nCARD DOES NOT EXIST OR IS NOT IN HAND.\n\n>> ");
                }
            }
            case "X" -> {
                System.out.print("ARE YOU SURE YOU WANT TO DISCARD ENTIRE CARAVAN? ('Y' TO CONFIRM): ");
                if (input.nextLine().equalsIgnoreCase("Y")) {
                    caravan.setCardRoot(currentPlayer, null);
                    caravan.setBidTotal(currentPlayer, 0);
                    System.out.println("CARAVAN DISCARDED.");
                    swapTurn();
                } else {
                    System.out.print("DID NOT DISCARD.\n>> ");
                }
            }
        }
    }

    private void playCardFirstRound(Caravan caravan, Card playedCard) {
        if (isViewingOpponent) {
            System.out.print("CANNOT PLAY ON OPPONENT'S CARAVAN DURING FIRST ROUND.\n\n>> ");
            isViewingOpponent = false;
            return;

        } else if (playedCard.getIsFaceCard()) {
            System.out.print("INVALID INPUT.\nFIRST CARD ON A CARAVAN CANNOT BE FACE CARD.\n\n>> ");
            return;

        } else if (caravan.getCardRoot(currentPlayer) != null){
            System.out.print("INVALID INPUT.\nFIRST ROUND ACTIVE, INITIALIZE EACH CARAVAN WITH A CARD.\n\n>> ");
            return;

        } else {
            caravan.setCardRoot(currentPlayer, new Node(playedCard));
            caravan.setSuit(currentPlayer, playedCard.getSuit());
        }
        currentPlayer.hand.remove(playedCard);
        currentPlayer.hand.add(currentPlayer.cardDeck.drawCard());
        caravan.setBidTotal(currentPlayer, Node.calculateBid(caravan.getCardRoot(currentPlayer)));
        swapTurn();
    }

    private void showHand() {
        System.out.printf("\nPLAYER %d CURRENT HAND:\n", currentPlayer.getRefID());
        currentPlayer.hand.printCards();
        System.out.print("\n\n>> ");
    }

    private void showRulebook() {
        System.out.print("""
                                          + --- --- --- --- --- --- +
                                          |        RULEBOOK:        |
                                          + --- --- --- --- --- --- +
                   GENERAL PLAY:
                == == == == == == ==
                - GOAL IS TO GET YOUR THREE CARAVANS BETWEEN THE RANGE OF 21 AND 26, INCLUSIVE.
                - ACES ARE WORTH 1 AND 2-10 MATCH FACE VALUE.
                - TWO CARDS OF SAME POINT VALUE CANNOT BE PLAYED ON ONE ANOTHER.
                - CARAVANS HAVE A SUIT, BASED ON THE LAST CARD PLAYED.
                - CARAVANS HAVE A SEQUENCE, ASCENDING OR DESCENDING, BASED ON SECOND CARD PLAYED.
                - IF YOU PLAY A CARD WITH THE SAME SUIT, IT MAY BREAK SEQUENCE AND SET A NEW ONE.
                - FACE CARDS ARE PLAYED ON TOP OF NUMBER CARDS AND HAVE DIFFERING EFFECTS:
                    JACK:
                      - REMOVES CARD IT IS PLAYED ON, ALONG WITH ANY ATTACHED FACE CARDS
                    QUEEN:
                      - PLAYED ON MOST RECENT CARD IN CARAVAN. REVERSES SEQUENCE & CHANGES SUIT.
                    KING:
                      - DOUBLES THE POINT VALUE OF THE CARD IT IS ATTACHED TO.
                - FACE CARDS MAY BE PLAYED ON TOP OF OPPONENTS CARDS.
                - THE GAME IS OVER WHEN ALL THREE ROUTES ARE WITHIN THE WINNING RANGE & NO TIES.
                - THE PLAYER WITH TWO OR THREE CARAVANS IN THE WINNING RANGE IS THE WINNER.
                
                                     ENTER 'O' FOR OPTIONS OR TYPE COMMAND
                
                >>\s""");
    }

    private void checkForFirstRound() {
        Caravan[] caravans = {caravanA, caravanB, caravanC};
        for (Caravan caravan : caravans) {
            // return true if both players have not gone yet for each caravan.
            if (caravan.getPlayer1CardRoot() == null || caravan.getPlayer2CardRoot() == null) {return;}
        }
        // no value came back true, first round over.
        isFirstRound = false;
    }

    private void checkForWin() {
        Player winner = null;
        int player1Tally = 0;
        int player2Tally = 0;
        Caravan[] caravans = {caravanA, caravanB, caravanC};
        for (Caravan caravan : caravans) {
            caravan.checkOwnedConditions();

            if (caravan.getIsPlayer1Owned()) {
                player1Tally++;
            }
            if (caravan.getIsPlayer2Owned()){
                player2Tally++;
            }

            if (!caravan.isInWinState()) {
                return;
            }

            if (player1Tally > player2Tally) {
                winner = player1;
            } else {
                winner = player2;
            }
        }
            System.out.printf("\n\nPLAYER %d WINS\n\nGREAT JOB!", winner.getRefID());
            isPlaying = false;
    }

    private void quitGame() {
        System.out.print("ARE YOU SURE YOU WANT TO EXIT? ('Y' TO CONFIRM): ");
        if (input.nextLine().equalsIgnoreCase("Y")) {
            System.out.print("THANKS FOR PLAYING, GOODBYE.");
            isPlaying = false;
            input.close();
            System.exit(0);
        } else {
            System.out.print("DID NOT EXIT.\n>> ");
        }
    }

}
